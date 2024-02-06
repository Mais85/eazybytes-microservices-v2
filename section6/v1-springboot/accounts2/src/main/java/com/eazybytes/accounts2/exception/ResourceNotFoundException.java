package com.eazybytes.accounts2.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%1$s not found with given input data %2$s: %3$s", resourceName, fieldName,fieldValue));
    }
}
