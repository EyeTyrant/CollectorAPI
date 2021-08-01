package com.eyetyrantdesign.collector.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {

//  @NotNull
//  @NotBlank
//  @Size(min = 2, max = 20, message = "Invalid username. Must be 3-20 characters")
//  private String firstName;
//
//  @NotNull
//  @NotBlank
//  @Size(min = 3, max = 20, message = "Invalid username. Must be 3-20 characters")
//  private String lastName;

//  @NotNull
//  @NotBlank
//  @Size(min = 3, max = 20, message = "Invalid username. Must be 3-20 characters")
  private String userName;


//  @NotNull
//  @NotBlank
//  @Size(min = 5, max = 20, message = "Invalid password. Must be 5-20 characters")
  private String password;

  public LoginFormDTO() {
  }

//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
