package com.seandtrent.messagingdashboard.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

  /**
   * Functinality for the API only available to authenticated users.
   * Does not really do anything yet.
   * @return just the user Principal for now, sent by spring as a JSON
   */
  @GetMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

}
