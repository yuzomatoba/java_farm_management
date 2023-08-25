package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FertilizerController has been created.
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService myFertilizeService;

  /**
   * Dependency injection has been created.
   */

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.myFertilizeService = fertilizerService;
  }

  /**
   * POST endpoint has been created.
   */

  @PostMapping
  public ResponseEntity<FertilizerDto> creatingFertilizer(
      @RequestBody FertilizerDto myFertilizerDto) {
    Fertilizer newFertilizer = myFertilizerDto.toEntity();
    Fertilizer mySavedFertilizer = myFertilizeService.creatingFertilizer(newFertilizer);

    FertilizerDto myResponseDto = FertilizerDto.fromEntity(mySavedFertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(myResponseDto);
  }

  /**
   * GET all endpoint has been created.
   */

  @GetMapping
  public ResponseEntity<List<FertilizerDto>> gettingAllFertilizers() {
    List<Fertilizer> allTheFertilizers = myFertilizeService.gettingAllFertilizers();
    List<FertilizerDto> fertilizerDtos = allTheFertilizers.stream()
        .map(myFertilizer -> new FertilizerDto(
        myFertilizer.getId(),
        myFertilizer.getName(),
        myFertilizer.getBrand(),
        myFertilizer.getComposition()
      ))
        .collect(Collectors.toList());

    return ResponseEntity.ok((fertilizerDtos));
  }

  /**
   * GET by id endpoint has been created.
   */

  @GetMapping("/{fertilizerId}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer myFertilizer = myFertilizeService.gettingFertilizerById(fertilizerId);

    if (myFertilizer != null) {
      FertilizerDto fertilizerDto = new FertilizerDto(
          myFertilizer.getId(),
          myFertilizer.getName(),
          myFertilizer.getBrand(),
          myFertilizer.getComposition()
      );
      return ResponseEntity.ok(fertilizerDto);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Fertilizante não encontrado!");
    }
  }

}