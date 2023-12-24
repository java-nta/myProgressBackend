package com.example.JavaApp.response;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.JavaApp.model.UserDetailsImpl;

import lombok.Getter;


public class UserTokenResponse {
    @Getter
    private String message = "ENJOY WITH OUR API ^_^";
    @Getter
    private String username;
    @Getter
    private String user_token;
    @Getter
    private String email;
    @Getter
    private List<String> roles;

    private Date authenticated_at;
    public String getCreatedAt() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return df.format(authenticated_at) ;
    }

    public UserTokenResponse() {
    }

    public UserTokenResponse(String userToken, UserDetailsImpl user) {
        this.user_token = userToken;
        this.username = user.getUsername();
        this.roles = Arrays.asList(user.getRoles().split(","));
        this.authenticated_at = new Date(System.currentTimeMillis());
        this.email = user.getEmail();
    }


}
