package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.services.CropFertilizerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GET endpoint has been created.
 */

@RestController
@RequestMapping("/crops/{cropId}/fertilizers")
public class CropFertilizerController {
  private final CropFertilizerService myCropFertilizerService;

  @Autowired
  public CropFertilizerController(CropFertilizerService myCropFertilizerService) {
    this.myCropFertilizerService = myCropFertilizerService;
  }

  /**
   * GET endpoint has been created.
   */
  @GetMapping
  public ResponseEntity<?> gettingFertilizersForCrop(@PathVariable Long cropId) {
    try {
      List<FertilizerDto> fertilizerDtos =
        myCropFertilizerService.gettingFertilizersForCrop(cropId);
      return ResponseEntity.ok(fertilizerDtos);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}