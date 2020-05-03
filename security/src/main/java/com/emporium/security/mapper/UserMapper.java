package com.emporium.security.mapper;

import com.emporium.lib.security.User;
import com.emporium.security.model.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDTO toDTO(User user);

  @Mapping(target = "role", ignore = true)
  User toEntity(UserDTO userDTO);
}
