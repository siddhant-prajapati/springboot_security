package com.example.apilogin.controller;
import com.example.apilogin.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @implNote This controller is use just to check api is properly working or note
 *  need not import or use (you can delete this file)
 *
 *  If you get CORS error then change origins url
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*") //change the url from where you want to use this rest api
public class HelloController {

  @GetMapping("/")
  public String getMessage(){
    return "Hello World";
  }

  /**
   * @implNote Use @AuthenticationPrincipal to get the data of authenticated user
   * @param principal
   * @return
   */
  @CrossOrigin
  @GetMapping("/secured")
  public String secured(@AuthenticationPrincipal UserPrincipal principal){
    return "If you see this it means that you succcessfully login with user : "
        + principal.getEmail() + " And Id =" + principal.getUserId();
  }

  @GetMapping("/admin")
  public String adminMessage(@AuthenticationPrincipal UserPrincipal principal) {
    return "If you see this it means you are admin , UserId : "+principal.getUserId();
  }
}
