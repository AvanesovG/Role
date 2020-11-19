package com.example.roles.convertor;

import com.example.roles.dto.UserDTO;
import com.example.roles.model.User;
import com.example.roles.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertor {
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    @Mapping(target = "userRoleSet", ignore = true)
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    default String userRoleToString(UserRole userRole) {
        return userRole.getRole().getName();
    }
}
