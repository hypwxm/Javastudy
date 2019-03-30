package com.example.pro.down.controller;

import com.example.pro.api.ApiResult;
import com.example.pro.api.ResultGenerator;
import com.example.pro.down.entity.Down;
import com.example.pro.down.service.DownService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/down")
public class DownController {
    @Autowired
    private DownService downService;

    @GetMapping("list")
    public ApiResult getList(@RequestParam("status") Integer status) {
        Down query = new Down();
        query.setStatus(status);
        PageInfo<Down> data = downService.findAll(query);
        ApiResult res = ResultGenerator.successResult(data);
        return res;
    }
}