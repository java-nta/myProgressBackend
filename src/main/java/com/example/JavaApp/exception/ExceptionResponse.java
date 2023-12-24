package com.example.JavaApp.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ExceptionResponse {

  private String message;

  private HttpStatus status;
  private Map<String, Object> errors;
  private String timestamp;

  // this.timestamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:sss").format(new
  // Date());
}
