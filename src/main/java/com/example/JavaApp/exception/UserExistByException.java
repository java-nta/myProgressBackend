package com.example.JavaApp.exception;

import lombok.Getter;
import lombok.Setter;

public class UserExistByException extends RuntimeException {
  
  @Getter @Setter
  private String attr;
  
  public UserExistByException(String message, String attr) {
    super(message);
    this.attr = attr;
  }
}
