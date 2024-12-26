package com.sparktechcode.ff.core.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sparktechcode.ff.core.auth.payloads.LoginRequestDto;
import com.sparktechcode.ff.core.common.config.AuthConfig;
import com.sparktechcode.ff.core.common.exceptions.AuthenticationException;
import com.sparktechcode.ff.core.common.exceptions.FFError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthConfig authConfig;

    public String generateToken(LoginRequestDto request) {
        if (!request.getEmail().equals(authConfig.getEmail()) || !request.getPassword().equals(authConfig.getPassword())) {
            throw new AuthenticationException(FFError.WRONG_CREDENTIALS, "Invalid credentials");
        }
        return generateToken();
    }

    public String generateToken() {
        return JWT.create()
                .withSubject(authConfig.getEmail())
                .withIssuer(authConfig.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*30))
                .sign(Algorithm.HMAC256(authConfig.getSecret()));
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(authConfig.getSecret())).build().verify(token);
    }
}
