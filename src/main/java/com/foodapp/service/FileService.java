package com.foodapp.service;

import com.foodapp.service.dto.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    UploadedFile uploadImage(MultipartFile image, String folder, String fileNamePrefix);
    void deleteQuietly(String fileId);
}
