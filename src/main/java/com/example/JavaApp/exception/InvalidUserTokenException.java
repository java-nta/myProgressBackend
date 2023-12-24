package com.example.JavaApp.exception;

public class InvalidUserTokenException extends RuntimeException {
    public InvalidUserTokenException(String message){
        super(message);
    }
}