package com.example.JavaApp.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.JavaApp.model.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
  private final String KEY = "freePalistine";

  public String generateUserToken(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(user.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 800000))
        .signWith(SignatureAlgorithm.HS512, KEY)
        .compact();
  }

  public String getUsernameFromUserToken(String token) {
    return Jwts.parser()
        .setSigningKey(KEY)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
