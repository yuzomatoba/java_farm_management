package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repository.CropFertilizerRepository;
import com.betrybe.agrix.models.repository.CropRepository;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropFertilizerService has been created.
 */

@Service
public class CropFertilizerService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;
  private final CropFertilizerRepository cropFertilizerRepository;
  private final FertilizerService fertilizerService;


  private final CropService cropService;

  /**
   * Dependency injection has been created.
   */

  @Autowired
  public CropFertilizerService(CropRepository cropRepository,
                               FertilizerRepository fertilizerRepository,
                               CropFertilizerRepository cropFertilizerRepository,
                               CropService cropService, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
    this.cropFertilizerRepository = cropFertilizerRepository;
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Associating Crop with Fertilizer.
   */
  public void associatingCropWithFertilizer(Long cropId, Long fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(() -> new EntityNotFoundException("Plantação não encontrada!"));

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(() -> new EntityNotFoundException("Fertilizante não encontrado!"));

    CropFertilizer cropFertilizer = new CropFertilizer(crop, fertilizer);
    cropFertilizerRepository.save(cropFertilizer);
  }

  /**
   * Method has been created.
   */

  public List<FertilizerDto> gettingFertilizersForCrop(Long cropId) {
    Optional<Crop> cropOptional = cropService.findByIdCrop(cropId);

    if (cropOptional.isEmpty()) {
      throw new EntityNotFoundException("Plantação não encontrada!");
    }

    Crop crop = cropOptional.get();

    List<FertilizerDto> fertilizerDtos = new ArrayList<>();
    List<CropFertilizer> cropFertilizers = cropFertilizerRepository.findById_Crop(crop);

    for (CropFertilizer cropFertilizer : cropFertilizers) {
      Long fertilizerId = cropFertilizer.getFertilizer().getId();
      Fertilizer fertilizer = fertilizerService.gettingFertilizerById(fertilizerId);
      FertilizerDto fertilizerDto = new FertilizerDto(
          fertilizer.getId(),
          fertilizer.getName(),
          fertilizer.getBrand(),
          fertilizer.getComposition()
      );
      fertilizerDtos.add(fertilizerDto);
    }

    return fertilizerDtos;
  }
}