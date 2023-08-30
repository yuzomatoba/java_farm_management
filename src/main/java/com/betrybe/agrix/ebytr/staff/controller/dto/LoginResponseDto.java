package com.betrybe.agrix.ebytr.staff.controller.dto;

/**
 * LoginResponseDto has been created.
 */

public class LoginResponseDto {
  private String token;

  public LoginResponseDto() {
  }

  public LoginResponseDto(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
