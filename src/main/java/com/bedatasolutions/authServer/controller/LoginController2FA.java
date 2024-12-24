package com.bedatasolutions.authServer.controller;

import com.bedatasolutions.authServer.dao.UserDao;
import com.bedatasolutions.authServer.security.MFAAuthentication;
import com.bedatasolutions.authServer.security.MFAHandler;
import com.bedatasolutions.authServer.service.AuthenticatorService;
import com.bedatasolutions.authServer.service.CustomUserDetails;
import com.bedatasolutions.authServer.service.CustomUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/v1/custom/auth")
public class LoginController2FA {

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final AuthenticationFailureHandler authenticatorFailureHandler = new SimpleUrlAuthenticationFailureHandler("/authenticator?error");
    private final AuthenticationFailureHandler securityQuestionFailureHandler = new SimpleUrlAuthenticationFailureHandler("/security-question?error");
    private final AuthenticationSuccessHandler securityQuestionSuccessHandler = new MFAHandler("/security-question", "ROLE_SECURITY_QUESTION_REQUIRED");

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticatorService authenticatorService;
    private final CustomUserDetailsService customUserDetailsService;
    private String generatedCode = "";
    private String base32Secret = "";
    private String keyId = "";

    public LoginController2FA(AuthenticationSuccessHandler authenticationSuccessHandler,
                              AuthenticatorService authenticatorService,
                              CustomUserDetailsService customUserDetailsService) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticatorService = authenticatorService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, @CurrentSecurityContext SecurityContext context) {
        base32Secret = authenticatorService.generateSecret();
        keyId = getUser(context).getMfaKeyId();
        try {
            generatedCode = authenticatorService.getCode(base32Secret);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        System.err.println(generatedCode);
        model.addAttribute("qrImage", authenticatorService.generateQrImageUrl(keyId, base32Secret));
        return "registration";
    }

    @PostMapping("/registration")
    public void validateRegistration(@RequestParam("code") String code,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     @CurrentSecurityContext SecurityContext context) throws ServletException, IOException {
        if (code.equals(generatedCode)) {
            customUserDetailsService.saveUserInfoMfaRegistered(base32Secret, getUser(context).getFullName());

            this.securityQuestionSuccessHandler.onAuthenticationSuccess(request, response, getAuthentication(request, response));
            return;
        }
        this.authenticatorFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("bad credentials"));
    }

    @GetMapping("/authenticator")
    public String authenticator(@CurrentSecurityContext SecurityContext context) throws GeneralSecurityException {
        if (!getUser(context).getMfaRegistered()) {
            return "redirect:registration";
        }
        return "authenticator";
    }

    @PostMapping("/authenticator")
    public void validateCode(@RequestParam("code") String code,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             @CurrentSecurityContext SecurityContext context) throws ServletException, IOException {
        if (this.authenticatorService.check(getUser(context).getMfaSecret(), code)) {
            this.securityQuestionSuccessHandler.onAuthenticationSuccess(request, response, getAuthentication(request, response));
            return;
        }
        this.authenticatorFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("bad credentials"));
    }

    /* @GetMapping("/security-question")
    public String securityQuestion(@CurrentSecurityContext SecurityContext context,Model model) {
        model.addAttribute("question", getUser(context).securityQuestion());
        return "security-question";
    } */

    /* @PostMapping("/security-question")
    public void validateSecurityQuestion(
            @RequestParam("answer") String answer,
            HttpServletRequest request,
            HttpServletResponse response,
            @CurrentSecurityContext SecurityContext context) throws ServletException, IOException {
        if (answer.equals(getUser(context).answer())) {
            this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, getAuthentication(request, response));
            return;
        }
        this.securityQuestionFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("bad credentials"));
    } */

    private Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        MFAAuthentication mfaAuthentication = (MFAAuthentication) securityContext.getAuthentication();
        securityContext.setAuthentication(mfaAuthentication.getPrimaryAuthentication());
        SecurityContextHolder.setContext(securityContext);
        securityContextRepository.saveContext(securityContext, request, response);
        return mfaAuthentication.getPrimaryAuthentication();
    }

    private UserDao getUser(SecurityContext context) {
        MFAAuthentication mfaAuthentication = (MFAAuthentication) context.getAuthentication();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) mfaAuthentication.getPrimaryAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) usernamePasswordAuthenticationToken.getPrincipal();
        return userDetails.getUser();
    }
}