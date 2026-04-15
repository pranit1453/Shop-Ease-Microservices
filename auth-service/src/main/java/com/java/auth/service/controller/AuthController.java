package com.java.auth.service.controller;

import com.java.auth.service.dto.LoginRequest;
import com.java.auth.service.dto.LoginResponse;
import com.java.auth.service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>  login(
            @Valid
                    @RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.userLogin(loginRequest));
    }
}
