package com.example.pro.globalException;

public class MyException extends RuntimeException {

    private Integer statusCode;

    public MyException(String msg, Integer status) {
        super(msg);
        this.statusCode = status;
    }
    
    public void setStatusCode(Integer status) {
        this.statusCode = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}