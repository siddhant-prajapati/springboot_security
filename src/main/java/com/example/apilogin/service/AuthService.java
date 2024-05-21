package com.example.apilogin.service;

import com.example.apilogin.security.JwtIssuer;
import com.example.apilogin.security.UserPrincipal;
import com.example.apilogin.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @implNote Contain all implementation and steps how use authenticate
 */
@Service
public class AuthService {

  @Autowired
  private JwtIssuer jwtIssuer;


  @Autowired
  AuthenticationManager authenticationManager;
  public LoginResponse attemptLogin(String email, String password){
    try{

      // check value of email and password with UserPrincipal's email and encrypted password
      var authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password)
      );

      // set authentication to SecurityContextHolder
      SecurityContextHolder.getContext().setAuthentication(authentication);
      var principal = (UserPrincipal) authentication.getPrincipal();

      var roles = principal.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .toList();
      System.out.println(roles);

      //call issuer() to generate new token
      var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(),roles);
      var userID = principal.getUserId();
      return LoginResponse
          .builder()
          .userId(userID)
          .accessToken(token)
          .build();
    } catch (Exception e){
      System.out.println("Error is \n"+e.getMessage());
      return null;
    }
  }
}
