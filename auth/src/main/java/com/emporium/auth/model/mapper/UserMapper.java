package com.emporium.auth.model.mapper;

import com.emporium.auth.model.jpa.User;
import com.emporium.lib.auth.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User toEntity(UserDTO dto);
}
