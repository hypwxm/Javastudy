package com.example.pro.banner.mapper;


import java.util.List;

import com.example.pro.banner.entity.Banner;

import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper  {
    List<Banner> findAll(Banner banner);
    Banner findById(Integer id);
    Integer create(Banner banner);
    Integer modify(Banner banner);
    Integer delete(Integer id);
}