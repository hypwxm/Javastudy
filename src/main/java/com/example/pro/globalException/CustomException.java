package com.example.pro.globalException;

import com.example.pro.api.ApiResult;
import com.example.pro.api.ResultGenerator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class CustomException {
    /*
     * 默认异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ApiResult runtimeExceptionHandler(Exception e) {
        return ResultGenerator.errorResult(e.getMessage(), 500);
    }


    @ExceptionHandler(MyException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult myExceptionHandler(MyException e) {
        return ResultGenerator.errorResult(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ApiResult authExceptionHandler(AuthException e) {
        return ResultGenerator.errorResult(e.getMessage(), e.getStatus());
    }




}