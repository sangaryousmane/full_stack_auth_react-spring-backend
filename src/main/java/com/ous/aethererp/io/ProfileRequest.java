package com.ous.aethererp.io;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileRequest {

    @NotBlank(message = "Name mustn't be empty.")
    private String name;

    @NotNull(message = "Email shouldn't be empty")
    @Email(message = "Enter Valid email address.")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
