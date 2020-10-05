package com.emporium.auth.model.jpa;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Document(value = "users")
public class User {

    @Id
    @Field("_id")
    private ObjectId id;

    @Indexed(name = "account_username", unique = true)
    @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
    private String username;

    @Indexed(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
    private String password;

    private boolean enabled;
}
