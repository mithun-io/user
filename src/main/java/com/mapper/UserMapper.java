package com.mapper;

import com.dto.UserRequest;
import com.dto.UserResponse;
import com.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", expression = "java(\"**********\")")
    User toUser(UserRequest userRequest);

    @Mapping(target = "name", source = "username")
    @Mapping(target = "dateOfBirth", expression = "java(user.getDateOfBirth() == null ? 0 : java.time.Period.between(user.getDateOfBirth(), java.time.LocalDate.now()).getYears())")
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponses(List<User> users);
}
