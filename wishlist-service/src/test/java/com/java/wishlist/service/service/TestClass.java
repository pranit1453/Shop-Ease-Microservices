package com.java.wishlist.service.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TestClass {
@org.junit.jupiter.api.Test
    void userId(){
    UUID is =  UUID.randomUUID();
    System.out.println(is);
    }
}
