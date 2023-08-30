package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * ResponsePersonAuthenticationDto has been created.
 */

public record ResponsePersonAuthenticationDto(Long id, String username, Role role) {
}
