package com.emporium.auth.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@Data
public class UserLoginDTO {

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
    private String username;

    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
    private String password;
}
