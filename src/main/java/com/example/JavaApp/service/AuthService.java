package com.example.JavaApp.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.JavaApp.utils.JwtTokenUtil;
import com.example.JavaApp.exception.InvalidUserTokenException;
import com.example.JavaApp.model.User;
import com.example.JavaApp.model.UserDetailsImpl;
import com.example.JavaApp.repository.UserRepo;
import com.example.JavaApp.request.LoginRequest;
import com.example.JavaApp.request.SignUpRequest;
import com.example.JavaApp.response.UserTokenResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
public class AuthService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private AuthenticationManager authenticationManager;

    public UserTokenResponse login(LoginRequest loginRequest){

            
            Authentication authentication = authenticationManager.authenticate
            (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            String userToken = jwtTokenUtil.generateUserToken(authentication);
            return new UserTokenResponse(userToken, user);

    
    }


    public User signUp(@Valid SignUpRequest signUpRequest){
        return userRepository
            .save(User.build(
                null,
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                "ROLE_USER",
                new Date()
            ));
    }

    public String testToken(HttpServletRequest request){
        String token = getTokenFromRequest(request);
        if(!token.isEmpty() && jwtTokenUtil.validateToken(token)){
            return "User token is valide. Happy api fetching";
        }
        throw new InvalidUserTokenException("invalid token");

        
    }

    
    public String getTokenFromRequest(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && StringUtils.startsWithIgnoreCase(headerAuth, "Bearer ")){
            String userToken = headerAuth.substring(7, headerAuth.length());
            return userToken;
        }

        return null;
    }
}   
