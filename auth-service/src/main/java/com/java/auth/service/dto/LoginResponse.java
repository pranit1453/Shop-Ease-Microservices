package com.java.auth.service.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginResponse(
        String message
) {
}
