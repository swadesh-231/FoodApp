package com.foodapp.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken
) {}