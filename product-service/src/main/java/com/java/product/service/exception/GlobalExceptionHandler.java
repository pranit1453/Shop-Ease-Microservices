package com.java.product.service.exception;

import com.java.product.service.wrapper.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ApiResponse<Object> handleBaseException(BaseException ex) {
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(Instant.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleException(Exception ex) {
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(Instant.now())
                .build();
    }
}
