package com.example.JavaApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaApp.model.User;
import com.example.JavaApp.request.UserData;
import com.example.JavaApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public ResponseEntity<List<User>> test() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<User>> findByUsername(@PathVariable("username") String username) {
    return ResponseEntity.ok(userService.getUserByUsername(username));
  }

  @PostMapping("/")
  public ResponseEntity<User> addUser(@RequestBody @Valid UserData userData) {
    return new ResponseEntity<>(userService.addUser(userData), HttpStatus.CREATED);
  }

  @DeleteMapping("/{user}")
  public ResponseEntity<String> deleteUser(@PathVariable("user") User user) {
    userService.deleteUser(user);
    return new ResponseEntity<>("User Deleted", HttpStatus.OK);
  }

  @DeleteMapping("/all")
  public ResponseEntity<String> deleteAllUser() {
    userService.deleteAllUser();
    return new ResponseEntity<>("All user are Deleted", HttpStatus.OK);
  }

}
