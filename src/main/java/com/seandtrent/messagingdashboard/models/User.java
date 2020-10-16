package com.seandtrent.messagingdashboard.models;

public class User {

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
