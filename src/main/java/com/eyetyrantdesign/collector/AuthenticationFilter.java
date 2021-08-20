package com.eyetyrantdesign.collector;

import com.eyetyrantdesign.collector.controllers.AuthenticationController;
import com.eyetyrantdesign.collector.models.User;
import com.eyetyrantdesign.collector.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthenticationController authenticationController;

  // CREATES A LIST OF PATHS AVAILABLE TO NON LOGGED IN USERS
  private static final List<String> whitelist = Arrays.asList("/login", "/reg", "/logout", "/");

  // CHECKS IF A PATH IS ON THE WHITELIST
  private static boolean isWhitelisted(String path) {
    for (String pathRoot : whitelist) {
      if (path.startsWith(pathRoot)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws IOException {
    // LOG IN NOT REQUIRED FOR WHITELIST PATHS
    if (isWhitelisted((request.getRequestURI()))) {
      // RETURN TRUE ALLOWS THE REQUEST TO PROCEED
    System.out.println("Hello");
      return true;
    }

    // RETRIEVES USER'S SESSION OBJECT CONTAINED IN THE REQUEST
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(2*60);
    response.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
  // RETRIEVES USER OBJECT CORRESPONDING TO THE GIVEN USER (RETURNS NULL IF NOT LOGGED IN)
    User user = authenticationController.getUserFromSession(session);
    // IF USER IS LOGGED IN
    if (user != null) {
      System.out.println("Goodbye");
      return true;
    }
    // IF USER IS NOT LOGGED IN
    response.sendRedirect("/");
    return false;
  }
}
