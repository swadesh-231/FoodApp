package com.foodapp.mapper;

import com.foodapp.dto.UserResponse;
import com.foodapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}