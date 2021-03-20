package com.emporium.account.data.mapper;

import com.emporium.account.data.jpa.Account;
import com.emporium.lib.auth.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "surname", ignore = true)
  @Mapping(target = "patronymic", ignore = true)
  @Mapping(target = "photoStorageId", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  Account toEntity(UserDTO dto);
}
