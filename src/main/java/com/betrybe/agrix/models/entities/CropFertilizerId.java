package com.betrybe.agrix.models.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Getters and Setters have been created.
 */

@Embeddable
public class CropFertilizerId {

  @ManyToOne
  @JoinColumn(name = "crop_id")
  private Crop crop;

  @ManyToOne
  @JoinColumn(name = "fertilizer_id")
  private Fertilizer fertilizer;

  public Crop getCrop() {
    return crop;
  }

  public void setCrop(Crop crop) {
    this.crop = crop;
  }

  public Fertilizer getFertilizer() {
    return fertilizer;
  }

  public void setFertilizer(Fertilizer fertilizer) {
    this.fertilizer = fertilizer;
  }
}