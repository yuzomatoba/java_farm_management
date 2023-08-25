package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FarmDto has been created.
 */

public record CropDto(Long id, String name, Long farmId, Double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate) {
  public Crop toCrop(Long farmId) {
    return new Crop(id, farmId, name, plantedArea, plantedDate, harvestDate);
  }

  public Crop toCrop() {
    return new Crop(id, farmId, name, plantedArea, plantedDate, harvestDate);
  }

  /**
   * fromEntities has been created.
   */

  public static List<CropDto> fromEntities(List<Crop> crops) {
    return crops.stream()
      .map(CropDto::fromEntity)
      .collect(Collectors.toList());
  }

  /**
   * fromEntity has been created.
   */

  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getFarmId(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate()
    );
  }
}