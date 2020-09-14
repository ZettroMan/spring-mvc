package com.geekbrains.spring.mvc.exceptions;

public class ProductNotFoundException extends Exception {
    private final Long id;

    public ProductNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
