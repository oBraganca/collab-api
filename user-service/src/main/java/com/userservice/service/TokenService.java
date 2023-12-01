package com.userservice.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.userservice.model.User;

@Service
public class TokenService {

    public String tokenGenerate(User user){
        return JWT.create()
                .withIssuer("auth")
                .withSubject(user.getEmail())
                .withClaim("id", true)
                .withExpiresAt(LocalDateTime.now()
                                .plusMinutes(120)
                                .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secret"));
    }

    public Object getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("auth")
                .build().verify(token).getSubject();
    }
}
