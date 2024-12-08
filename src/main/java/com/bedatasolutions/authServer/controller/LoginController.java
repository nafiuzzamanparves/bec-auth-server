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
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
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

    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
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

            // Generate token
            Instant now = Instant.now();
            Set<String> roles = AuthorityUtils.authorityListToSet(
                            authentication.getAuthorities()
                    ).stream()
                    .map(c -> c.replaceFirst("^ROLE_", ""))
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
            Set<String> scopes = new HashSet<>();
            scopes.add("openid");
            scopes.add("profile");


            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .audience(List.of("oidc-client"))
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.HOURS))
                    .subject(loginRequest.getUsername())
                    .claim("scope", scopes)
                    .claim("roles", roles)
                    .build();

            String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            // Return token in response
            Map<String, String> response = new HashMap<>();
            response.put("access_token", token);
            response.put("token_type", "Bearer");
            response.put("expires_in", "3600");
            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

