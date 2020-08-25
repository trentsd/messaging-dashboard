package com.seandtrent.messagingdashboard.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminResourceIntTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @BeforeAll
  public void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  /**
   * No authentication. Everything behind /admin should be 401 Unauthorized.
   */
  @Test
  public void noAuthRequestOn_ADMIN_Service_shouldFailWith401() throws Exception {
    mvc.perform(get("/admin")).andExpect(status().isUnauthorized());
  }

  /**
   * Authorized as a USER. Everything behind /admin should be 403 Forbidden.
   */
  @Test
  @WithMockUser(username = "user", roles = {"USER"})
  public void given_USER_AuthRequestOn_ADMIN_Service_shouldFailWith403() throws Exception {
    mvc.perform(get("/admin")).andExpect(status().isForbidden());
  }

  /**
   * Authorized as a ADMIN. Everything behind /admin should be 200 OK.
   */
  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void given_ADMIN_AuthRequestOn_ADMIN_Service_shouldSucceedWith200() throws Exception {
    mvc.perform(get("/admin")).andExpect(status().isOk());
  }
}

