package com.sparktechcode.ff.core.common.config;

import com.sparktechcode.ff.core.common.exceptions.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class Auth {

    public static String getUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return auth.getName();
        } else {
            throw new AuthenticationException();
        }
    }

    public static String getUserEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            if (auth instanceof JwtAuthenticationToken) {
                var email = ((JwtAuthenticationToken) auth).getTokenAttributes().get("email");
                if (email != null) {
                    return email.toString();
                }
            }
        }
        return null;
    }
}
