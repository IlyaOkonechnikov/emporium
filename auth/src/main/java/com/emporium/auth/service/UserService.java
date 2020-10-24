package com.emporium.auth.service;

import com.emporium.auth.model.jpa.User;
import com.emporium.lib.auth.RegistrationDTO;
import org.bson.types.ObjectId;

public interface UserService {

    String create(RegistrationDTO registrationDTO);

    User findByEmail(String email);

    User findById(ObjectId id);

    void mailConfirm(ObjectId id);
}
