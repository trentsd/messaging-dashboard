package com.seandtrent.messagingdashboard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity
// @Table(name = "users")
public class User {

  //@Id
  private String username;

  private String email;

  public User() {}

  public User(String username, String email) {
    this.username = username;
    this.email = email;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }
}
