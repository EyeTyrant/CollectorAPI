package com.eyetyrantdesign.collector.controllers;

import com.eyetyrantdesign.collector.models.DieCast;
import com.eyetyrantdesign.collector.models.User;
import com.eyetyrantdesign.collector.models.data.DieCastRepository;
import com.eyetyrantdesign.collector.models.data.UserRepository;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
//@CrossOrigin(allowCredentials = "true")
public class CollectorController {

  @Autowired
  private AuthenticationController authenticationController;

  @Autowired
  private DieCastRepository dieCastRepository;

  @Autowired
  private UserRepository userRepository;
  private DieCast diecast;

  @RequestMapping("diecast")
  @ResponseBody
  public String index(){
    return "index";
  }

  @GetMapping("diecast/list")
  @ResponseBody
  public Iterable<DieCast> listAllItems() {
    return dieCastRepository.findAll();
  }

  @GetMapping("diecast/list/{user_id}")
  @ResponseBody
  public Iterable<DieCast> listAllUserItems(@PathVariable Integer user_id){
    Optional<User> aUser = userRepository.findById(user_id);
//    if(aUser.isPresent()){
//      System.out.println(aUser.get().getId());
//    }
      return dieCastRepository.findAllById(user_id);
  }

// TO GET A SINGLE DIECAST BY ITS ID (NOT NEEDED? CONFLICTS WITH listAllUserItems())
//  @GetMapping("diecast/list/{id}")
//  @ResponseBody
//  public Optional<DieCast> getItemById(@PathVariable Integer id){
//    return dieCastRepository.findById(id);
//  }

  @PostMapping("diecast/list")
  public DieCast addItem(@RequestBody DieCast newDieCast, HttpServletRequest request){
    HttpSession session = request.getSession();
    User user = authenticationController.getUserFromSession(session);
    newDieCast.setUser(user);
    return dieCastRepository.save(newDieCast);
  }

  @DeleteMapping("diecast/list/{id}")
  public void deleteItemById(@PathVariable("id") Integer id) {
   dieCastRepository.deleteById(id);
  }

  @PatchMapping("diecast/list/{id}")
  public DieCast updateItem(@RequestBody DieCast editDieCast, HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = authenticationController.getUserFromSession(session);
    editDieCast.setUser(user);
    return dieCastRepository.save(editDieCast);
  }
}