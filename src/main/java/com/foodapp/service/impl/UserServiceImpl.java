package com.foodapp.service.impl;

import com.foodapp.dto.request.UpdateNameRequest;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import com.foodapp.exception.APIException;
import com.foodapp.exception.UserNotFoundException;
import com.foodapp.mapper.UserMapper;
import com.foodapp.repository.UserRepository;
import com.foodapp.service.UserService;
import io.imagekit.client.ImageKitClient;
import io.imagekit.models.files.FileUploadParams;
import io.imagekit.models.files.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Objects;

import static com.foodapp.constants.ImageUploadConstants.ALLOWED_IMAGE_EXTENSIONS;
import static com.foodapp.constants.ImageUploadConstants.PROFILE_IMAGE_FOLDER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageKitClient imageKitClient;

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
        validateImage(image);

        User user = getCurrentUserEntity();
        String previousFileId = user.getImageFileId();

        FileUploadResponse uploaded;
        try (InputStream imageStream = image.getInputStream()) {
            uploaded = imageKitClient.files().upload(
                    FileUploadParams.builder()
                            .file(imageStream)
                            .fileName("user-" + user.getId())
                            .folder(PROFILE_IMAGE_FOLDER)
                            .useUniqueFileName(true)
                            .build()
            );
        } catch (IOException e) {
            throw new APIException("Failed to read the uploaded image");
        }

        user.setImageUrl(uploaded.url()
                .orElseThrow(() -> new APIException("ImageKit did not return an image URL")));
        user.setImageFileId(uploaded.fileId().orElse(null));
        User saved = userRepository.save(user);

        deletePreviousImage(previousFileId);

        return userMapper.toResponse(saved);
    }

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new APIException("Image file is required");
        }
        String contentType = image.getContentType();
        if (contentType != null && contentType.startsWith("image/")) {
            return;
        }
        // The part's Content-Type is client supplied and optional: clients that cannot
        // detect it send application/octet-stream, so fall back to the file extension.
        String filename = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(filename);
        if (extension != null && ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase(Locale.ROOT))) {
            return;
        }
        log.warn("Rejected profile image upload: filename={}, contentType={}", filename, contentType);
        throw new APIException("Only image files are allowed, received: " + contentType);
    }

    private void deletePreviousImage(String fileId) {
        if (fileId == null) {
            return;
        }
        try {
            imageKitClient.files().delete(fileId);
        } catch (RuntimeException e) {
            log.warn("Failed to delete replaced profile image {} from ImageKit", fileId, e);
        }
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
