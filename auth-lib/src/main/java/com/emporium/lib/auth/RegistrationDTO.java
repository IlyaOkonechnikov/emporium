package com.emporium.lib.auth;

import lombok.Data;
import org.bson.types.ObjectId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationDTO {

    private ObjectId id;

    @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
    private String password;
}
