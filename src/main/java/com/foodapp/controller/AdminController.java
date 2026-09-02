package com.foodapp.controller;

import com.foodapp.constants.AppConstants;
import com.foodapp.dto.request.CreateUserRequest;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('" + AppConstants.ADMIN + "')")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.createUser(createUserRequest));
    }
}
