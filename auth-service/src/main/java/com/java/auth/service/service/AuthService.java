package com.java.auth.service.service;

import com.java.auth.service.dto.LoginRequest;
import com.java.auth.service.dto.LoginResponse;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponse userLogin(@Valid LoginRequest loginRequest);
}
