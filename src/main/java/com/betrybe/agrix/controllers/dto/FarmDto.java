package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * FarmDto has been created.
 */

public record FarmDto(Double size, String name, Long id) {
  public Farm toFarm() {
    return new Farm(size, name, id);
  }
}