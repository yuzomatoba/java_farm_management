package com.betrybe.agrix.ebytr.staff.util;

import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * SecurityFilter has been created.
 */

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private final TokenService myTokenService;
  private final PersonService myPersonService;

  @Autowired
    public SecurityFilter(TokenService myTokenService, PersonService myPersonService) {
    this.myTokenService = myTokenService;
    this.myPersonService = myPersonService;
  }

  @Override
    protected void doFilterInternal(HttpServletRequest myRequest,
      HttpServletResponse myResponse, FilterChain myFilterChain) throws ServletException,
        IOException {
    String myToken = recoveryToken(myRequest);

    if (myToken != null) {
      String mySubject = myTokenService.validateToken(myToken);
      UserDetails userDetails = myPersonService.loadUserByUsername(mySubject);

      UsernamePasswordAuthenticationToken myAuthentication =
          new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
      SecurityContextHolder.getContext().setAuthentication(myAuthentication);
    }
    myFilterChain.doFilter(myRequest, myResponse);
  }

  private String recoveryToken(HttpServletRequest myRequest) {
    String authHeader = myRequest.getHeader("Authorization");
    if (authHeader == null) {
      return null;
    }
    return authHeader.replace("Bearer ", "");
  }
}
