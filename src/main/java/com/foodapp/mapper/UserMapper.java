package com.foodapp.mapper;

import com.foodapp.dto.UserResponse;
import com.foodapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "username", source = "name")
    UserResponse toResponse(User user);
}
