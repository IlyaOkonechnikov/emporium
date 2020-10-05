package com.emporium.auth.service;

import com.emporium.lib.auth.RegistrationDTO;

public interface UserService {

    String create(RegistrationDTO registrationDTO);
}
