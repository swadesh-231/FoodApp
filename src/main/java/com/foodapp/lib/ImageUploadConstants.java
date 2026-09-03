package com.foodapp.lib;

import java.util.Set;

public final class ImageUploadConstants {
    private ImageUploadConstants() {
    }

    public static final String PROFILE_IMAGE_FOLDER = "/users/profile-images";

    public static final Set<String> ALLOWED_IMAGE_EXTENSIONS =
            Set.of("jpg", "jpeg", "png", "gif", "webp", "avif", "heic");
}
