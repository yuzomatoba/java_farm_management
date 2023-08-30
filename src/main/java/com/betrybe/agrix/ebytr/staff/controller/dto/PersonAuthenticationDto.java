package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonalAuthenticationDto has been created.
 */
public record PersonAuthenticationDto(Long id, String username, String password, Role role) {
  public Person toPerson() {
    return new Person(id, username, password, role);
  }
}
