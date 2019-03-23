package com.example.pro.globalException;


public class AuthException extends RuntimeException {
    private final Integer status = 401;
    public AuthException(String msg) {
        super(msg);
    }

    public Integer getStatus() {
        return this.status;
    }
}