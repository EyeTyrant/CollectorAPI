package com.eyetyrantdesign.collector.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DieCast extends AbstractEntity{

  @ManyToOne
  private User user;


//  @Id
//  @GeneratedValue
//  private Integer id;

  private Integer year;
  private String name;
  private String brand;
  private String mfr;

  public DieCast(){}

  public DieCast(Integer year, String name, String brand, String mfr) {
    this.year = year;
    this.name = name;
    this.brand = brand;
    this.mfr = mfr;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
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

  public String getMfr() {
    return mfr;
  }

  public void setMfr(String mfr) {
    this.mfr = mfr;
  }

  public void setUser(User user) {
    this.user = user;
  }

  //  public Integer getId() {
//    return id;
//  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DieCast)) return false;
    DieCast dieCast = (DieCast) o;
    return getYear().equals(dieCast.getYear()) &&
        getName().equals(dieCast.getName()) &&
        getBrand().equals(dieCast.getBrand()) &&
        getMfr().equals(dieCast.getMfr());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getYear(), getName(), getBrand(), getMfr());
  }
}
