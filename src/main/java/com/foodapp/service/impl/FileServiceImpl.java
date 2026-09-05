package com.foodapp.service.impl;

import com.foodapp.exception.APIException;
import com.foodapp.service.FileService;
import com.foodapp.service.dto.UploadedFile;
import io.imagekit.client.ImageKitClient;
import io.imagekit.models.files.FileUploadParams;
import io.imagekit.models.files.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static com.foodapp.constants.ImageUploadConstants.ALLOWED_IMAGE_EXTENSIONS;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ImageKitClient imageKitClient;

    @Override
    public UploadedFile uploadImage(MultipartFile image, String folder, String fileNamePrefix) {
        validateImage(image);

        FileUploadResponse uploaded;
        try (InputStream imageStream = image.getInputStream()) {
            uploaded = imageKitClient.files().upload(
                    FileUploadParams.builder()
                            .file(imageStream)
                            .fileName(fileNamePrefix)
                            .folder(folder)
                            .useUniqueFileName(true)
                            .build()
            );
        } catch (IOException e) {
            throw new APIException("Failed to read the uploaded image");
        }

        String url = uploaded.url()
                .orElseThrow(() -> new APIException("ImageKit did not return an image URL"));
        return new UploadedFile(url, uploaded.fileId().orElse(null));
    }

    @Override
    public void deleteQuietly(String fileId) {
        if (fileId == null) {
            return;
        }
        try {
            imageKitClient.files().delete(fileId);
        } catch (RuntimeException e) {
            log.warn("Failed to delete file {} from ImageKit", fileId, e);
        }
    }

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new APIException("Image file is required");
        }
        String contentType = image.getContentType();
        if (contentType != null && contentType.startsWith("image/")) {
            return;
        }
        String filename = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(filename);
        if (extension != null && ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase(Locale.ROOT))) {
            return;
        }
        log.warn("Rejected image upload: filename={}, contentType={}", filename, contentType);
        throw new APIException("Only image files are allowed, received: " + contentType);
    }
}
