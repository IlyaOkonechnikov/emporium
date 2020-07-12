package com.emporium.auth.service;

import com.emporium.lib.auth.UserRegisterDTO;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
public interface UserService {

    String create(UserRegisterDTO userDTO);
}
