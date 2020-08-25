package com.seandtrent.messagingdashboard.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminResource {

    /**
   * Functionality for the API only available to authenticated admins.
   * Does not really do anything yet.
   * @return just the admin Principal, which is really just a user, sent as JSON by spring
   */
  @GetMapping("/admin")
  public Principal admin(Principal admin) {
    return admin;
  }

}
