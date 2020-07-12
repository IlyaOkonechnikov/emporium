package com.emporium.auth.model.mapper;

import com.emporium.auth.model.jpa.User;
import com.emporium.lib.auth.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "enabled", constant = "true")
    User toEntity(UserRegisterDTO userDTO);
}
