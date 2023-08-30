package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController has been created.
 */

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService myFarmService;

  @Autowired
  public FarmController(FarmService myFarmService) {
    this.myFarmService = myFarmService;
  }

  /**
   * POST endpoint has been created.
   */

  @PostMapping
  public ResponseEntity<Farm> creatingMyFarm(@RequestBody FarmDto farmDto) {
    Farm newMyFarm = farmDto.toFarm();

    Farm creatingFarm = this.myFarmService.creatingtMyFarm(newMyFarm);

    return ResponseEntity.status(HttpStatus.CREATED).body(creatingFarm);
  }

  /**
   * GET All endpoint has been created.
   */

  @GetMapping()
  @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
  public List<FarmDto> gettingAllFarms() {
    List<Farm> allVegFarms = myFarmService.gettingAllFarms();
    return allVegFarms.stream()
      .map((farm) -> new FarmDto(farm.getSize(), farm.getName(), farm.getId()))
      .collect(Collectors.toList());
  }

  /**
   * GET By Id endpoint has been created.
   */

  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = myFarmService.findByIdFarm(id);

    if (farm != null) {
      FarmDto farmDto = new FarmDto(farm.getSize(), farm.getName(), farm.getId());
      return ResponseEntity.ok(farmDto);
    } else {
      FarmDto emptyFarmDto = new FarmDto(null, "Fazenda não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyFarmDto);
    }
  }

}