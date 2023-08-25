package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController has been created.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService myPersonService;

  @Autowired

    public PersonController(PersonService myPersonService) {

    this.myPersonService = myPersonService;
  }

  /**
   * Endpoint POST has been created.
   */

  @PostMapping
    public ResponseEntity<PersonDto> creatingPerson(@RequestBody PersonDto myPersonDto) {
    Person myPersonToBeCreated = new Person();
    myPersonToBeCreated.setUsername(myPersonDto.getUsername());
    myPersonToBeCreated.setRole(Role.valueOf(myPersonDto.getRole().name()));

    Person myCreatedPerson = myPersonService.create(myPersonToBeCreated);

    PersonDto myResponseDto = new PersonDto();
    myResponseDto.setId(myCreatedPerson.getId());
    myResponseDto.setUsername(myCreatedPerson.getUsername());
    myResponseDto.setRole(myPersonDto.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(myResponseDto);
  }
}
