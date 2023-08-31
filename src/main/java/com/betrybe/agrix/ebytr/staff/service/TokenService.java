package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService has been created.
 */

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
    private String jwtSecret;

  /**
   * generateToken has been created.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
    return JWT.create()
            .withSubject(person.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
          .plusHours(2)
          .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * validateToken has been created.
   */

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
    return JWT.require(algorithm)
          .build()
          .verify(token)
          .getSubject();
  }
}
