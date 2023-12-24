package com.example.JavaApp.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.JavaApp.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    ExceptionResponse exceptionResponse;
    exceptionResponse = ExceptionResponse.build(
        "Username or password incorrect",
        HttpStatus.UNAUTHORIZED,
        null,
        new SimpleDateFormat("dd-MM-yyyy hh:mm:sss").format(new Date()));
    response.setStatus(401);
    response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
    response.getWriter().flush();

  }

}
