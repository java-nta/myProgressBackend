package com.example.JavaApp.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MissingPathVariableException.class)
  public ResponseEntity<Map<String, Object>> missingPathVariableException(MissingPathVariableException e) {
    Map<String, Object> exceptionReponse = new HashMap<>();
    exceptionReponse.put("message", e.getVariableName() + " not founded");
    exceptionReponse.put("status", HttpStatus.BAD_REQUEST);
    exceptionReponse.put("timestamp", new Date());
    return new ResponseEntity<>(exceptionReponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

    Map<String, Object> fieldError = new HashMap<>();

    e.getBindingResult()
        .getFieldErrors()
        .forEach(error -> {
          fieldError.put(error.getField(), error.getDefaultMessage());
        });

    return new ResponseEntity<>(ExceptionResponse.build(
        "Validation data",
        HttpStatus.BAD_REQUEST,
        fieldError,
        new SimpleDateFormat("dd-MM-yyyy hh:mm:sss").format(new Date())), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<ExceptionResponse> sQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException e) {
    return new ResponseEntity<>(ExceptionResponse.build(
        "Duplicated entry",
        HttpStatus.BAD_REQUEST,
        null,
        new SimpleDateFormat("dd-MM-yyyy hh:mm:sss").format(new Date())), HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(InvalidUserTokenException.class)
  public ResponseEntity<ExceptionResponse> invalidUserTokenException(
      InvalidUserTokenException e) {
    return new ResponseEntity<>(ExceptionResponse.build(
        e.getMessage(),
        HttpStatus.UNAUTHORIZED,
        null,
        new SimpleDateFormat("dd-MM-yyyy hh:mm:sss").format(new Date())), HttpStatus.UNAUTHORIZED);
  }

}
