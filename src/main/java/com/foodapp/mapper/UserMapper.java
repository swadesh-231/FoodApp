package com.foodapp.mapper;

import com.foodapp.dto.response.RegisterResponse;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "username", source = "name")
    @Mapping(target = "createdAt", source = "createdDate")
    UserResponse toResponse(User user);

    @Mapping(target = "username", source = "name")
    @Mapping(target = "createdAt", source = "createdDate")
    RegisterResponse toRegisterResponse(User user);
}
