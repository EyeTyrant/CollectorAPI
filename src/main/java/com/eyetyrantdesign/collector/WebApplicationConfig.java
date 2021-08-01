package com.eyetyrantdesign.collector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

  // @BEAN CREATES SPRING-MANAGED OBJECT OUT OF MY FILTER ALLOWING THE APP TO ACCESS IT
  @Bean
  public AuthenticationFilter authenticationFilter() {
    return new AuthenticationFilter();
  }

  // REGISTERS MY FILTER WITH THE SPRING CONTAINER
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authenticationFilter());
  }

}
