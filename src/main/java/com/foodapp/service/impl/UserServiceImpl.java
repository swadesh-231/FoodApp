package com.foodapp.service.impl;

import com.foodapp.dto.request.UpdateNameRequest;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import com.foodapp.exception.UserNotFoundException;
import com.foodapp.mapper.UserMapper;
import com.foodapp.repository.UserRepository;
import com.foodapp.service.FileService;
import com.foodapp.service.UserService;
import com.foodapp.service.dto.UploadedFile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.foodapp.constants.ImageUploadConstants.PROFILE_IMAGE_FOLDER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileService fileService;

    @Override
    public UserResponse getCurrentUser() {
        return userMapper.toResponse(getCurrentUserEntity());
    }

    @Override
    @Transactional
    public UserResponse updateName(UpdateNameRequest updateNameRequest) {
        User user = getCurrentUserEntity();
        user.setName(updateNameRequest.name());
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse uploadProfileImage(MultipartFile image) {
        User user = getCurrentUserEntity();
        String previousFileId = user.getImageFileId();

        UploadedFile uploaded = fileService.uploadImage(
                image, PROFILE_IMAGE_FOLDER, "user-" + user.getId());

        user.setImageUrl(uploaded.url());
        user.setImageFileId(uploaded.fileId());
        User saved = userRepository.save(user);

        fileService.deleteQuietly(previousFileId);

        return userMapper.toResponse(saved);
    }

    private User getCurrentUserEntity() {
        String email = Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
