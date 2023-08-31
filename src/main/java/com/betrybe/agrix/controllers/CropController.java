package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropFertilizerService;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.services.FertilizerService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * POST endpoint has been created.
 */
@RestController
@RequestMapping()
public class CropController {
  private final FarmService myFarmService;
  private final CropService myCropService;
  private final FertilizerService myFertilizeService;

  private final CropFertilizerService myCropFertilizerService;

  /**
   * Dependency injection.
   */

  @Autowired
  public CropController(FarmService myFarmService, CropService myCropService,
                        FertilizerService myFertilizeService,
                        CropFertilizerService myCropFertilizerService) {
    this.myFarmService = myFarmService;
    this.myCropService = myCropService;
    this.myFertilizeService = myFertilizeService;
    this.myCropFertilizerService = myCropFertilizerService;
  }

  /**
   * endpoint POST has been created.
   */

  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> creatingMyCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto
  ) {
    Farm farm = myFarmService.findByIdFarm(farmId);

    if (farm == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    Crop newCrop = cropDto.toCrop();
    newCrop.setFarmId(farmId);

    Crop savedCrop = myCropService.creatingMyCrop(newCrop);

    CropDto responseDto = new CropDto(
        savedCrop.getId(),
        savedCrop.getName(),
        savedCrop.getFarmId(),
        savedCrop.getPlantedArea(),
        savedCrop.getPlantedDate(),
        savedCrop.getHarvestDate()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * GET By Id (farm) endpoint has been created.
   */

  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getCropsByFarmId(@PathVariable Long farmId) {
    Farm myFarm = myFarmService.findByIdFarm(farmId);

    if (myFarm == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    List<Crop> allCrops = myCropService.findCropsByFarmId(farmId);
    List<CropDto> cropDtos = allCrops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
        crop.getFarmId(), crop.getPlantedArea(), crop.getPlantedDate(),
        crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * GET By Id (crop) endpoint has been created.
   */

  @GetMapping("crops/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> farmCrop = myCropService.findByIdCrop(id);

    if (farmCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    Crop myCrop = farmCrop.get();
    CropDto cropDto = new CropDto(myCrop.getId(), myCrop.getName(),
        myCrop.getFarmId(), myCrop.getPlantedArea(),
        myCrop.getPlantedDate(), myCrop.getHarvestDate());
    return ResponseEntity.ok(cropDto);
  }

  /**
   * GET All endpoint has been created.
   */

  @GetMapping("/crops")
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity<?> gettingAllCrops() {
    List<Crop> allCrops = myCropService.gettingAllCrops();

    List<CropDto> cropDtos = allCrops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
        crop.getFarmId(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * GET By Search (crop) endpoint has been created.
   */
  @GetMapping("crops/search")
  public List<CropDto> searchingCropsByHarvestDate(
      @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

    List<Crop> crops = myCropService.gettingCropsByHarvestDate(startDate, endDate);
    return CropDto.fromEntities(crops);
  }

  /**
   * POST endpoint has been created.
   */

  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associatingCropWithFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    try {
      myCropFertilizerService.associatingCropWithFertilizer(cropId, fertilizerId);
      return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}