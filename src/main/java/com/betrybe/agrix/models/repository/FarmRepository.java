package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * entity FarmRepository has been created.
 */

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}