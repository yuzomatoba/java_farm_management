package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String jwtSecret;

//    @Value("${jwt.expiration}")
//    private Long jwtExpiration;

    public String generateToken(Person person) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
          .withIssuer("agrix")
          .withSubject(person.getUsername())
          .withExpiresAt(expirationDate())
          .sign(algorithm);
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
          .plusHours(2)
          .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.require(algorithm)
          .withIssuer("agrix")
          .build()
          .verify(token)
          .getSubject();
    }
}
