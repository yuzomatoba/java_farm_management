package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonDto has been created.
 */

public class PersonDto {

  private Long id;
  private String username;
  private String password;
  private Role role;

  public PersonDto() {
  }

  /**
   * Constructor, Getters and Setters.
   */
  public PersonDto(Long id, String username, Role role) {
    this.id = id;
    this.username = username;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
