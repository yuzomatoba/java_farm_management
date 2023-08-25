package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * entity CropRepository has been created.
 */

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarmId(Long farmId);

  @Query("SELECT c FROM Crop c WHERE c.harvestDate BETWEEN :start AND :end")
  List<Crop> findByHarvestDate(@Param("start") LocalDate start, @Param("end") LocalDate end);


  Optional<Crop> findById(Long id);
}