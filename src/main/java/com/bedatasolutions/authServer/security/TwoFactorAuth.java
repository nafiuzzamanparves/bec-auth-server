package com.bedatasolutions.authServer.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serial;

public class TwoFactorAuth extends AnonymousAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Authentication primaryAuthentication;

    public TwoFactorAuth(Authentication authentication, String authority) {
        super("anonymous", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS", authority));
        this.primaryAuthentication = authentication;
    }

    public Authentication getPrimaryAuthentication() {
        return this.primaryAuthentication;
    }

    @Override
    public Object getPrincipal() {
        return this.primaryAuthentication.getPrincipal();
    }

}