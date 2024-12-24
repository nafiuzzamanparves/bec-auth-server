package com.bedatasolutions.authServer.service;

import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class AuthenticatorService {
    private static final String SECRET = "12345678";

    public boolean check(String key, String code) {
        try {
            // return TimeBasedOneTimePasswordUtil.validateCurrentNumber(key, Integer.parseInt(code), 10000);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    // public String generateSecret() {
    //     return TimeBasedOneTimePasswordUtil.generateBase32Secret();
    // }

    public String generateSecret() {
        return SECRET;
    }

    // public String generateQrImageUrl(String keyId, String base32Secret) {
    //     return TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, base32Secret);
    // }

    public String generateQrImageUrl(String keyId, String base32Secret) {
        return SECRET;
    }

    public String getCode(String base32Secret) throws GeneralSecurityException {
        // return TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret);
        return SECRET;
    }
}