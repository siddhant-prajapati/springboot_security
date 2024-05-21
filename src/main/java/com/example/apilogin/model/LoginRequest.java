package com.example.apilogin.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @implNote this is DTO for request comming from login page or form
 *    You can also add any role if required
 */
@Getter
@Builder
public class LoginRequest {
  private String email;
  private String password;
}
