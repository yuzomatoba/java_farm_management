package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropService has been created.
 */

@Service
public class CropService {
  private final CropRepository myCropRepository;

  @Autowired
  public CropService(CropRepository myCropRepository) {
    this.myCropRepository = myCropRepository;
  }

  public Crop creatingMyCrop(Crop myCrop) {
    Crop savedCrop = myCropRepository.save(myCrop);
    return savedCrop;
  }


  public List<Crop> gettingAllCrops() {
    List<Crop> allTheFarms = this.myCropRepository.findAll();
    return allTheFarms;
  }

  public Optional<Crop> findByIdCrop(Long id) {
    return myCropRepository.findById(id);
  }

  public List<Crop> findCropsByFarmId(Long farmId) {
    return myCropRepository.findByFarmId(farmId);
  }

  public List<Crop> gettingCropsByHarvestDate(LocalDate start, LocalDate end) {
    return myCropRepository.findByHarvestDate(start, end);
  }

}