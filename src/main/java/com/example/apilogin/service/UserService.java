package com.example.apilogin.service;

import com.example.apilogin.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is your own user service (Use in your service in place of userService with required modifications)
 */
@Service
public class UserService {

  private static final String EXISTING_EMAIL = "test@example.com";

  private static final String NEW_EMAIL = "new@example.com";
  public Optional<UserEntity> findByEmail(String email){
    if(EXISTING_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setEmail(email);
      user.setId(1L);
      user.setRole("ROLE_ADMIN");
      user.setPassword("$2a$12$tYALmJt2UV0iZ8x2cvrPcuY.NsUc39bM3pIEg5DQdHeqU0OnM0c82");
      user.setExtraInfo("There is nobody but admin");
      return Optional.of(user);
    } else if (NEW_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setEmail(email);
      user.setId(99L);
      user.setRole("ROLE_USER");
      user.setPassword("$2a$12$tYALmJt2UV0iZ8x2cvrPcuY.NsUc39bM3pIEg5DQdHeqU0OnM0c82");
      user.setExtraInfo("There is nobody but new user");
      return Optional.of(user);
    }


    return Optional.empty();
  }
}
