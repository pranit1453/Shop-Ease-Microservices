package com.java.cart.service.exception.custom;

import com.java.cart.service.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateResourceFoundException extends BaseException {
    public DuplicateResourceFoundException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
