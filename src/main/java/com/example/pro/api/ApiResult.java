package com.example.pro.api;

import java.io.Serializable;

public class ApiResult implements Serializable {
    private ApiResult() {
    };

    public static ApiResult instance() {
        return new ApiResult();
    }

    private String msg;
    private Integer status;
    private Object result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
