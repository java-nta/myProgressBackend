package com.example.JavaApp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserData {
    @NotBlank
    @Size(max = 15, min = 4, message = "Username should be between 4 and 15")
    private String username;


    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 8, max = 15)
    @Size(max = 15, min = 4, message = "Username should be between 8 and 15")
    private String password;
}
