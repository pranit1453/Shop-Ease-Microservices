package com.java.auth.service.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String username,
        String password
) {
}
