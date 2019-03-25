package com.example.pro.banner.controller;

import com.example.pro.api.ApiResult;
import com.example.pro.api.ResultGenerator;
import com.example.pro.banner.entity.Banner;
import com.example.pro.banner.service.BannerService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping(value = "list")
    public ApiResult getBanners(@RequestParam(value = "status", required = false) Byte status
    // @RequestParam("pageIndex") Integer pageIndex,
    // @RequestParam("pageSize") Integer pageSize
    ) {
        Banner banner = new Banner();
        // System.out.println(1/0);
        /*
         * if (status == 0) { throw new MyException("aa", 400); }
         */
        if (status == null) {
            status = 0;
        }
        banner.setStatus(status);
        PageInfo<Banner> banners = bannerService.findAll(banner);

        ApiResult res = ResultGenerator.successResult(banners);

        return res;
    }

    @GetMapping(value = "/{id}")
    public ApiResult getBannerById(@PathVariable("id") Integer id) {
        Banner banner = bannerService.findById(id);
        ApiResult res = ResultGenerator.successResult(banner);
        return res;
    }

    @PostMapping(value = "add")
    public ApiResult addBanner(@RequestBody Banner banner) {
        // TODO: process POST request

        bannerService.create(banner);
        ApiResult res = ResultGenerator.successResult(banner);

        return res;
    }

    @PostMapping(value = "modify")
    public ApiResult modify(@RequestBody Banner banner) {
        // TODO: process POST request

        bannerService.modify(banner);
        ApiResult res = ResultGenerator.successResult("");

        return res;
    }

    @PostMapping(value = "delete")
    public ApiResult delete(Integer id) {
        // TODO: process POST request

        bannerService.delete(id);
        ApiResult res = ResultGenerator.successResult("");

        return res;
    }

}