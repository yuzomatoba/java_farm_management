package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizerService has been created.
 */

@Service
public class FertilizerService {
  private final FertilizerRepository myFertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.myFertilizerRepository = fertilizerRepository;
  }

  public Fertilizer creatingFertilizer(Fertilizer myFertilizer) {
    return myFertilizerRepository.save(myFertilizer);
  }

  public List<Fertilizer> gettingAllFertilizers() {
    return myFertilizerRepository.findAll();
  }

  public Fertilizer gettingFertilizerById(Long id) {
    return myFertilizerRepository.findById(id).orElse(null);
  }
}