package com.benyissa.digitalbankback.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

public class JWTUtils {

    Algorithm hmacAlgo = Algorithm.HMAC256("secret");
    public static final long ACCESS_TOKEN_TIME = 60 * 60 * 1000;
    public static final long REFRESH_TOKEN_TIME = 120 * 60 * 1000;

    public String generateAccessToken(String subject, String issuer, List<String> roles) {

        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME))
                .withIssuer("Auth Service at " + issuer)
                .withClaim("roles", roles)
                .sign(hmacAlgo);
    }

    public String generateRefreshToken(String subject, String issuer) {

        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .withIssuer("Auth Service at " + issuer)
                .sign(hmacAlgo);
    }
}
