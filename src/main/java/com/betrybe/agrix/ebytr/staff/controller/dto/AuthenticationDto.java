package com.betrybe.agrix.ebytr.staff.controller.dto;

/**
 * AuthenticationDto has been created.
 */

public record AuthenticationDto(String username, String password) {
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
