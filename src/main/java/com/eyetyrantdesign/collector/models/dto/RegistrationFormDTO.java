package com.eyetyrantdesign.collector.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO{

//  @NotNull
//  @NotBlank
//  @Size(min = 2, max = 20, message = "Invalid username. Must be 3-20 characters")
  private String firstName;

//  @NotNull
//  @NotBlank
//  @Size(min = 3, max = 20, message = "Invalid username. Must be 3-20 characters")
  private String lastName;


  private String verifyPassword;

  public RegistrationFormDTO() {}

  public String getVerifyPassword() {
    return verifyPassword;
  }

  public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
