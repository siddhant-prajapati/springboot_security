package com.example.apilogin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Contain all test case to check weather all method properly working or not
 * Run it once time check all methods properly working or not
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

  @Autowired
  private MockMvc api;

  @Test
  void anyOneCanPublicEndPoint() throws Exception {
    api.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("Hello World")));
  }

  @Test
  void notLoggedIn_shouldNotSeeSecuredEndPoints() throws Exception {
    api.perform(get("/secured"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void notLoggedIn_shouldNotSeeAdminEndPoints() throws Exception {
    api.perform(get("/admin"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void loggedIn_shouldSeeSecuredEndPoint() throws Exception {
    api.perform(get("/secured"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("And Id =1")));
  }

  @Test
  @WithMockUser
  void simpleUserShouldNotSeeAdminEndPoint() throws Exception {
    api.perform(get("/admin"))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithAdminUser
  void admin_shouldSeeAdminEndPoint() throws Exception {
    api.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("UserId : 1")));
  }
}
