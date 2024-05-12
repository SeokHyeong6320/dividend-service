package com.project.security;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface TokenProvider {

    String generateToken(String username, List<String> roles);

    boolean validateToken(String token);

    Authentication getAuthentication(String token);
}
