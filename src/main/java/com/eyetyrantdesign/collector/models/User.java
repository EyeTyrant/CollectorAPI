package com.eyetyrantdesign.collector.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractEntity{

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private final List<DieCast> diecasts = new ArrayList<>();

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String userName;
  @NotNull
  private String pwdHash;

  public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public User() {}

  public User (String firstName, String lastName, String userName, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.pwdHash = encoder.encode(password);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserName() {
    return userName;
  }

  public boolean isMatchingPassword(String password) {
    return encoder.matches(password, pwdHash);
  }

  public List<DieCast> getDiecasts() {
    return diecasts;
  }

  @Override
  public String toString() {
    return userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return getFirstName().equals(user.getFirstName()) &&
        getLastName().equals(user.getLastName()) &&
        getUserName().equals(user.getUserName()) &&
        pwdHash.equals(user.pwdHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getUserName(), pwdHash);
  }
}
