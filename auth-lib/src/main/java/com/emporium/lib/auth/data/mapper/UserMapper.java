package com.emporium.lib.auth.data.mapper;

import com.emporium.lib.auth.data.dto.UserDTO;
import com.emporium.lib.auth.data.jpa.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  User toEntity(UserDTO dto);
}
