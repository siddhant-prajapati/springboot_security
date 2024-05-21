package com.example.apilogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @implNote use your own UserDAO or User Entity here
 */
@Getter
@Setter
public class UserEntity {
  private long id;

  private String email;

  @JsonIgnore
  private String password;

  private String role;

  private String extraInfo;
}
