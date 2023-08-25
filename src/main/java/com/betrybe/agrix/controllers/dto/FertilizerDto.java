package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * FertilizerDto has been created.
 */

public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Fertilizer toEntity has been created.
   */

  public Fertilizer toEntity() {
    return new Fertilizer(id, name, brand, composition);
  }

  /**
   * FertilizerDto fromEntity has been created.
   */

  public static FertilizerDto fromEntity(Fertilizer myFertilizer) {
    return new FertilizerDto(
      myFertilizer.getId(),
      myFertilizer.getName(),
      myFertilizer.getBrand(),
      myFertilizer.getComposition()
    );
  }
}