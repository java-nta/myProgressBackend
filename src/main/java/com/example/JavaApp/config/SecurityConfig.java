package com.example.JavaApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.JavaApp.service.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
  @Autowired
  private AuthenticationEntryPointImpl authenticationEntryPointImpl;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPointImpl))
        .userDetailsService(userDetailsServiceImpl)
        .csrf(c -> c.disable())
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
