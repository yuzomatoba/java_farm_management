package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.CropFertilizerId;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * entity CropFertilizerRepository has been created.
 */

@Repository
public interface CropFertilizerRepository extends JpaRepository<CropFertilizer, CropFertilizerId> {
  List<CropFertilizer> findById_Crop(Crop crop);

  List<CropFertilizer> findById_Fertilizer(Fertilizer fertilizer);
}