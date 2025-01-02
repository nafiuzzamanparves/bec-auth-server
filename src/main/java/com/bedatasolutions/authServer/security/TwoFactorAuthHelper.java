package com.bedatasolutions.authServer.security;

import com.bedatasolutions.authServer.usecase.auth.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.io.IOException;

@Slf4j
public class TwoFactorAuthHelper implements AuthenticationSuccessHandler {

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final AuthenticationSuccessHandler mfaNotEnabled = new SavedRequestAwareAuthenticationSuccessHandler();
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final String authority;

    public TwoFactorAuthHelper(String successUrl, String authority) {
        log.info("[TwoFactorAuthHelper] Initializing the TwoFactorAuthHelper constructor with successUrl: {} and authority: {}", successUrl, authority);
        SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler(successUrl);
        authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authority = authority;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("[TwoFactorAuthHelper] Authentication success triggered for user: {}", authentication.getName());

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("[TwoFactorAuthHelper] User MFA Enabled: {}", userDetails.user().getMfaEnabled());

            if (!userDetails.user().getMfaEnabled()) {
                log.info("[TwoFactorAuthHelper] MFA not enabled for user. Redirecting using mfaNotEnabled handler.");
                mfaNotEnabled.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        }

        log.info("[TwoFactorAuthHelper] Saving MFAAuthentication and redirecting to success URL.");
        saveAuthentication(request, response, new TwoFactorAuth(authentication, authority));
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }

    private void saveAuthentication(HttpServletRequest request,
                                    HttpServletResponse response,
                                    TwoFactorAuth authentication) {
        log.info("[TwoFactorAuthHelper] Saving authentication in SecurityContext.");
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        securityContextRepository.saveContext(securityContext, request, response);
        log.info("[TwoFactorAuthHelper] Authentication saved successfully in SecurityContext.");
    }
}