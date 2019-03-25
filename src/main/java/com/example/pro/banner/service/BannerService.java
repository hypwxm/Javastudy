package com.example.pro.banner.service;

import com.example.pro.banner.entity.Banner;
import com.github.pagehelper.PageInfo;

public interface BannerService {
    PageInfo<Banner> findAll(Banner banner);

    Banner findById(Integer id);

    Banner create(Banner banner);

    Integer modify(Banner banner);

    Integer delete(Integer id);
}