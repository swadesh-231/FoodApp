package com.foodapp.service;

import com.foodapp.dto.request.CreateUserRequest;
import com.foodapp.dto.response.UserResponse;

public interface AdminService {
    UserResponse createUser(CreateUserRequest createUserRequest);
}
