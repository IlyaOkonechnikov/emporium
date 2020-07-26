package com.emporium.area.model.mapper;

import com.emporium.area.model.User;
import com.emporium.lib.auth.UserBasicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "patronymic", ignore = true)
    @Mapping(target = "photoStorageId", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    User toEntity(UserBasicDTO userBasicDTO);
}
