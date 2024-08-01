package com.revature.Project1.util;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenUtil {

    private static final String SECRET_KEY = "secret";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        String token = username + ":" + (now + EXPIRATION_TIME);
        return Base64.getEncoder().encodeToString((token + ":" + SECRET_KEY).getBytes());
    }

    public boolean validateToken(String token, String username) {
        String decodedToken = new String(Base64.getDecoder().decode(token));
        String[] parts = decodedToken.split(":");
        if (parts.length != 3) {
            return false;
        }
        String tokenUsername = parts[0];
        long expirationTime = Long.parseLong(parts[1]);
        String secret = parts[2];
        return username.equals(tokenUsername) && expirationTime > System.currentTimeMillis() && SECRET_KEY.equals(secret);
    }
}