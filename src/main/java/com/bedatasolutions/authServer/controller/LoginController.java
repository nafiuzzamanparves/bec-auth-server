package com.bedatasolutions.authServer.controller;

import com.bedatasolutions.authServer.dto.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            log.info("Login method called");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Generate Access Token
            Instant now = Instant.now();
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                    .stream()
                    .map(c -> c.replaceFirst("^ROLE_", ""))
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

            JwtClaimsSet accessTokenClaims = JwtClaimsSet.builder()
                    .issuer("self")
                    .audience(List.of("oidc-client"))
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.HOURS))
                    .subject(loginRequest.getUsername())
                    .claim("roles", roles)
                    .claim("scope", Set.of("openid", "profile"))
                    .build();

            String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();

            // Generate Refresh Token
            JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                    .issuer("self")
                    .audience(List.of("oidc-client"))
                    .issuedAt(now)
                    .expiresAt(now.plus(7, ChronoUnit.DAYS)) // Refresh token valid for 7 days
                    .subject(loginRequest.getUsername())
                    .claim("scope", "refresh_token")
                    .build();

            String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();

            // Return tokens in response
            Map<String, String> response = new HashMap<>();
            response.put("access_token", accessToken);
            response.put("refresh_token", refreshToken);
            response.put("token_type", "Bearer");
            response.put("expires_in", "3600");
            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refresh_token");
        try {
            // Decode and validate the refresh token using the JwtDecoder bean
            Jwt decodedToken = jwtDecoder.decode(refreshToken);

            // Generate a new Access Token
            Instant now = Instant.now();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self") // Use issuer from decoded refresh token
                    .audience(decodedToken.getAudience()) // Use audience from decoded refresh token
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Access token valid for 1 hour
                    .subject(decodedToken.getSubject()) // Use subject from decoded refresh token
                    .claim("roles", decodedToken.getClaims().get("roles")) // Use roles claim
                    .claim("scope", Set.of("openid", "profile"))
                    .build();

            String newAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            // Return the new Access Token
            Map<String, String> response = new HashMap<>();
            response.put("access_token", newAccessToken);
            response.put("token_type", "Bearer");
            response.put("expires_in", "3600");
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            log.error("Failed to decode or validate refresh token", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }
    }
}

