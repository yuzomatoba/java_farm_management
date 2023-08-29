package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

/**
 * Application main class.
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.betrybe.agrix.ebytr.staff.entity",
  "com.betrybe.agrix.models.entities"})
@EnableJpaRepositories(basePackages = {"com.betrybe.agrix.ebytr.staff.repository",
  "com.betrybe.agrix.models.repository"})
public class AgrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}
