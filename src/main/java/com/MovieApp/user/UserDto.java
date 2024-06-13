package com.MovieApp.user;

import com.MovieApp.validators.*;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "You must enter your username")
    @Size(min = 3, message = "Username length must be at least 3 symbols")
    @UniqueUsername
    private String username;

    @NotBlank(message = "You must enter your name")
    private String firstName;

    @NotBlank(message = "You must enter your surname")
    private String lastName;

    @NotBlank(message = "You must enter your email")
    @Email(message = "Invalid email")
    @UniqueEmail
    private String email;

    @NotBlank(message = "You must enter your password")
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 symbols")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])^[0-9a-zA-Z!@#$%^&*]{8,20}$",
            message = "Password must contain at least one small and capital Latin letter, number and special character (!, @, #, $, %, ^, &, *)")
    private String password;
}
