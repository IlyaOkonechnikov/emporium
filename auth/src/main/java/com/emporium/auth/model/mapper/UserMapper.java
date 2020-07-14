package com.emporium.auth.model.mapper;

import com.emporium.auth.model.jpa.User;
import com.emporium.lib.auth.UserBasicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    User toEntity(UserBasicDTO userBasicDTO);
}
