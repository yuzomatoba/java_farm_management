package com.betrybe.agrix.models.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Constructor has been created.
 */

@Entity
@Table(name = "crop_fertilizer")
public class CropFertilizer {

  @EmbeddedId
  private CropFertilizerId id;

  @ManyToOne
  @MapsId("fertilizerId")
  private Fertilizer fertilizer;

  @ManyToOne
  @MapsId("cropId")
  private Crop crop;

  private String name;
  private String brand;
  private String composition;

  /**
   * Constructor has been created.
   */

  public CropFertilizer(String name, String brand, String composition) {
    this.id = new CropFertilizerId();
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  /**
   * Getters and Setters have been created.
   */

  public CropFertilizer() {
    this.id = new CropFertilizerId();
  }

  /**
   * Constructor has been created.
   */
  public CropFertilizer(Crop crop, Fertilizer fertilizer) {
    this.id = new CropFertilizerId();
    this.id.setCrop(crop);
    this.id.setFertilizer(fertilizer);
  }

  public Crop getCrop() {
    return crop;
  }

  public void setCrop(Crop crop) {
    this.crop = crop;
    this.id.setCrop(crop);
  }

  public CropFertilizerId getId() {
    return id;
  }

  public void setId(CropFertilizerId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public Fertilizer getFertilizer() {
    return fertilizer;
  }

  public void setFertilizer(Fertilizer fertilizer) {
    this.fertilizer = fertilizer;
    this.id.setFertilizer(fertilizer);
  }

  public Long getFertilizerId() {
    return id != null ? fertilizer.getId() : null;
  }
}