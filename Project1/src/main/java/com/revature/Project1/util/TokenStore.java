package com.revature.Project1.util;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    private final Map<String, String> tokenMap = new ConcurrentHashMap<>();

    public void storeToken(String username, String token) {
        tokenMap.put(username, token);
    }

    public String getToken(String username) {
        return tokenMap.get(username);
    }

    public boolean validateToken(String username, String token) {
        return token.equals(tokenMap.get(username));
    }

    public void removeToken(String username) {
        tokenMap.remove(username);
    }
}
