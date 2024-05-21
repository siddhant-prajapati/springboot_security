package com.example.apilogin.controller;

import com.example.apilogin.model.LoginRequest;
import com.example.apilogin.model.LoginResponse;
import com.example.apilogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class AuthController {

  @Autowired
  private AuthService authService;

  /**
   * @implNote  Controller User For Login
   * @return authService.attemptLogin(email, password)
   */
  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request){
    return authService.attemptLogin(request.getEmail(), request.getPassword());
  }
}
