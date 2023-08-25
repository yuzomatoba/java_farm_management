package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * entity Farm has been created.
 */

@Entity
@Table(name = "farm")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String name;
  private Double size;


  public Farm() {
  }

  /**
   * * Getters and Setters have been created.
   * */

  public Farm(Double size, String name, Long id) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }
}