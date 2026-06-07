package com.raizesdonordeste.infraestructure.config;

public class BusinessException
        extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}