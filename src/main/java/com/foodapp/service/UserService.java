package com.foodapp.service;

import com.foodapp.dto.request.UpdateNameRequest;
import com.foodapp.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponse getCurrentUser();

    UserResponse updateName(UpdateNameRequest updateNameRequest);

    UserResponse uploadProfileImage(MultipartFile image);
}
