package com.eyetyrantdesign.collector.models;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SameSiteFilter implements javax.servlet.Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    chain.doFilter(request, response);
    addSameSiteAttribute((HttpServletResponse) response); // add SameSite=strict cookie attribute
  }

  private void addSameSiteAttribute(HttpServletResponse response) {
    Collection<String> headers = response.getHeaders("Set-Cookie");
    boolean firstHeader = true;
    for (String header : headers) {
      if (firstHeader) {
        response.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=None"));
        firstHeader = false;
        continue;
      }
      response.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=None"));
    }
  }

  @Override
  public void destroy() {

  }
}
