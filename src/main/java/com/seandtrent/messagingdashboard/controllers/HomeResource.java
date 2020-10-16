package com.seandtrent.messagingdashboard.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for a "home" API, available to everyone at the root URL "/"
 * @author Sean Trent trentsd
 */
@RestController
public class HomeResource {


  @Autowired
  JdbcTemplate jdbcTemplate;

  /**
   * Functionaility for the API at "/" available to all visitors to the page.
   * TODO: Replace placeholder functionality
   * @return just placeholder html for now
   */
  @GetMapping("home/api")
  public List<Map<String, Object>> home() {
    return jdbcTemplate.queryForList("select username "
                                + "from users");

    // Map<String, Object> model = new HashMap<String, Object>();
    // model.put("id", UUID.randomUUID().toString());
    // model.put("content", "Hello World");
    // return model;
}

}
