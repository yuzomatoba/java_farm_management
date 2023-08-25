package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService has been created.
 */

@Service
public class FarmService {
  private FarmRepository myFarmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.myFarmRepository = farmRepository;
  }

  public List<Farm> gettingAllFarms() {
    List<Farm> allTheFarms = this.myFarmRepository.findAll();
    return allTheFarms;
  }

  public Farm creatingtMyFarm(Farm myFarm) {
    Farm savedFarm = this.myFarmRepository.save(myFarm);
    return savedFarm;
  }

  public Farm findByIdFarm(Long id) {
    return myFarmRepository.findById(id).orElse(null);
  }
}