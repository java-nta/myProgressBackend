package com.example.JavaApp.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JavaApp.model.User;
import com.example.JavaApp.repository.UserRepo;
import com.example.JavaApp.request.UserData;

@Service
public class UserService {
  @Autowired
  private UserRepo userRepo;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public String maskPassword(String password) {
    int length = password.length();
    String maskedPassword = "";
    String hintWord = password.substring(length - 2);
    for (int i = 0; i < length - 2; i++) {
      maskedPassword = maskedPassword.concat("*");
    }

    return maskedPassword + hintWord;
  }

  public List<User> getAllUsers() {

    return userRepo.findAll();
  };

  public User addUser(UserData userData) {
    return userRepo
        .save(User.build(
            null,
            userData.getUsername(),
            userData.getEmail(),
            passwordEncoder.encode(userData.getPassword()),
            "ROLE_USER",
            new Date()));
  }

  public void deleteUser(User user) {
    userRepo.delete(user);
  }

  public void deleteAllUser() {
    userRepo.deleteAll();
  }

  public List<User> getUserByUsername(String username) {
    List<User> users = userRepo.findAll();
    if (users != null) {
      return users.stream()
          .filter(user -> user.getUsername().toLowerCase().contains(username.toLowerCase().trim()))
          .collect(Collectors.toList());
    }
    return null;
  }
}