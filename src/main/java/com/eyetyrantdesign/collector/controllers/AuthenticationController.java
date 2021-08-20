package com.eyetyrantdesign.collector.controllers;

import com.eyetyrantdesign.collector.models.User;
import com.eyetyrantdesign.collector.models.data.UserRepository;
import com.eyetyrantdesign.collector.models.dto.LoginFormDTO;
import com.eyetyrantdesign.collector.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
//@CrossOrigin(allowCredentials = "true")
public class AuthenticationController {

  @Autowired
  UserRepository userRepository;

  private static final String userSessionKey = "user";

  public User getUserFromSession(HttpSession session) {
    Integer userId = (Integer) session.getAttribute(userSessionKey);
    if (userId == null) {
      return null;
    }

    Optional<User> user = userRepository.findById(userId);

    if (user.isEmpty()) {
      return null;
    }

    return user.get();
  }

   private static void setUserInSession(HttpSession session, User user) {
    session.setAttribute(userSessionKey, user.getId());
  }

//  @PostMapping("/reg")
//  public User addUser(@RequestBody User newUser) {
//    return userRepository.save(newUser);
//   }

  @GetMapping("reg")
  @ResponseBody
  public Iterable<User>getAllUsers() {return userRepository.findAll();}

  @GetMapping("reg/{id}")
  @ResponseBody
  public Optional<User> getUserById(@PathVariable Integer id) {return userRepository.findById(id);}

  @PostMapping(value= "reg", produces = MediaType.APPLICATION_JSON_VALUE)
  public String processRegistrationForm(@RequestBody @Valid RegistrationFormDTO registrationFormDTO,
                                         Errors errors,
                                         HttpServletRequest request) {

    if (errors.hasErrors()) {
      System.out.println(errors);
      return "reg errors";
    }

    User existingUser = userRepository.findByUserName(registrationFormDTO.getUserName());

    if (existingUser != null) {
      errors.rejectValue("userName", "userName.exists", "Already exists");
      return "exist";
    }

    String password = registrationFormDTO.getPassword();
    String verifyPassword = registrationFormDTO.getVerifyPassword();


    if (!password.equals(verifyPassword)) {
      errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
      return "mismatch";
    }

    User newUser = new User(registrationFormDTO.getFirstName(),
                            registrationFormDTO.getLastName(),
                            registrationFormDTO.getUserName(),
                            registrationFormDTO.getPassword());
    userRepository.save(newUser);
    setUserInSession(request.getSession(), newUser);

    String first = newUser.getFirstName();
    String last = newUser.getLastName();

    return first + " " + last;// PUTTING ANY VALUE HERE WILL CAUSE UNEXPECTED TOKEN IN JSON ERRORS
              // AS RETURNING TEXT NOT JSON IN RESPONSE
  }

  @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
                                 Errors errors,
                                 HttpServletRequest request
                                 ) {

    if (errors.hasErrors()) {
      return "login errors";
    }

    User theUser = userRepository.findByUserName(loginFormDTO.getUserName());

    if (theUser == null) {
      errors.rejectValue("userName", "userName.invalid", "The given username does not exist");
      return "Invalid User Name, Please Register";
    }

    String password = loginFormDTO.getPassword();

    if (!theUser.isMatchingPassword(password)) {
      errors.rejectValue("password", "password.invalid", "Invalid password");
      return "Invalid Password";
    }

    setUserInSession(request.getSession(), theUser);
    System.out.println(theUser.getId());
    return theUser.getId();

//    String first = theUser.getFirstName();
//    String last = theUser.getLastName();
//  return first + " " + last;
//      String username = String.valueOf(theUser);
//  return String.valueOf(theUser.getId());
//    return "Welcome "+ username + " you are now logged in.";


    //    return ""; // can I redirect to users list page here?
                // PUTTING ANY VALUE HERE WILL CAUSE UNEXPECTED TOKEN IN JSON ERRORS
                // AS RETURNING TEXT NOT JSON IN RESPONSE
  }

  @GetMapping(value = "logout")
  public String logout(HttpServletRequest request) {
//    System.out.println("Log Out Clicked");
//    System.out.println(request.getSession());
    request.getSession().invalidate();
    return "redirect:/";
  }
}
