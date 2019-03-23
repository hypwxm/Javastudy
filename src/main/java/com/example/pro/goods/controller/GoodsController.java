package com.example.pro.goods.controller;

import com.example.pro.api.ApiResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/goods")
public class GoodsController {
    @RequestMapping("list")
    public ApiResult list() {
        return null;
    }
}