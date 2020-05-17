package com.emporium.auth.mapper;

import com.emporium.auth.model.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    public abstract UserDTO toDTO(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", qualifiedByName = "password")
    @Mapping(target = "created", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "updated", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "status", constant = "ACTIVE")
    public abstract User createUser(UserDTO userDTO);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", qualifiedByName = "password")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", defaultExpression = "java(LocalDate.now())")
    public abstract void updateUser(UserDTO userDTO, @MappingTarget User user);

    @Named("password")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

//  protected LocalDate getDate(LocalDate date) {
//    return Objects.isNull(date) ? LocalDate.now() : date;
//  }
}
