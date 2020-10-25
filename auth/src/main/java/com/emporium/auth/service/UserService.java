package com.emporium.auth.service;

import com.emporium.lib.auth.RegistrationDTO;
import org.bson.types.ObjectId;

public interface UserService {

    String create(RegistrationDTO dto);

    void enable(ObjectId id);
}
