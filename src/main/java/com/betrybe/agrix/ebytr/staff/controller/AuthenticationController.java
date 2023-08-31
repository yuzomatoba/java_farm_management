package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.LoginResponseDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonAuthenticationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.ResponsePersonAuthenticationDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController has been created.
 */

@RestController
public class AuthenticationController {

  private final AuthenticationManager myAuthenticationManager;
  private final TokenService myTokenService;
  private final PersonService myPersonService;

  /**
   * PersonalAuthenticationController has been created.
   */
  @Autowired
    public AuthenticationController(AuthenticationManager myAuthenticationManager,
      TokenService myTokenService, PersonService myPersonService) {
    this.myAuthenticationManager = myAuthenticationManager;
    this.myTokenService = myTokenService;
    this.myPersonService = myPersonService;
  }

  /**
   * POST has been created.
   */

  @PostMapping("/persons")
    public ResponseEntity<ResponsePersonAuthenticationDto>
      create(@RequestBody PersonAuthenticationDto personDto) {
    Person newPerson = myPersonService.create(personDto.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonAuthenticationDto(
                newPerson.getId(),
                newPerson.getUsername(),
                newPerson.getRole()));
  }

  /**
   * POST has been created.
   */

  @PostMapping("/auth/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto auth) {
    UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(
            auth.username(), auth.password());
    Authentication authenticate = myAuthenticationManager.authenticate(userPassword);
    Person person = (Person) authenticate.getPrincipal();
    String secretToken = myTokenService.generateToken(person);
    LoginResponseDto response = new LoginResponseDto(secretToken);
    return ResponseEntity.ok(response);
  }
}
