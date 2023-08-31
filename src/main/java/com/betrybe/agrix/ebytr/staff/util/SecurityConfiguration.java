package com.betrybe.agrix.ebytr.staff.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfiguration has been created.
 */

@Configuration
public class SecurityConfiguration {


  /**
   * SecurityFilterChain has been created.
   */



  @Bean
  public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
