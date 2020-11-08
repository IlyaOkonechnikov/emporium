package com.emporium.auth.service;

import com.emporium.lib.auth.UserDTO;
import org.bson.types.ObjectId;

public interface UserService {

    String create(UserDTO dto);

    void enable(ObjectId id);
}
