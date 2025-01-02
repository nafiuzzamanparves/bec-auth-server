package com.bedatasolutions.authServer.usecase.auth;

import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class AuthenticatorService {
    private static final String SECRET = "1234";

    public boolean check(String key, String code) {
        try {
            return key.equals(code);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public String generateSecret() {
        return SECRET;
    }

    public String generateQrImageUrl(String keyId, String base32Secret) {
        return SECRET;
    }

    public String getCode(String base32Secret) throws GeneralSecurityException {
        return SECRET;
    }
}