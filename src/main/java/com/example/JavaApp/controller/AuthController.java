package com.example.JavaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaApp.model.User;
import com.example.JavaApp.request.LoginRequest;
import com.example.JavaApp.request.SignUpRequest;
import com.example.JavaApp.response.UserTokenResponse;
import com.example.JavaApp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody @Valid SignUpRequest signUpRequest){
        return new ResponseEntity<User>(authService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @GetMapping("/testToken")
    @ResponseStatus(code = HttpStatus.OK)
    public String testToken(HttpServletRequest request){
        
        return authService.testToken(request);
    }
}
