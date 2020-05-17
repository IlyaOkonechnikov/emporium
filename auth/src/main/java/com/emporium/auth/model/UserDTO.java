package com.emporium.auth.model;

import com.emporium.lib.auth.Status;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate updated;
    private Status status;
}
