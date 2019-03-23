package com.example.pro.banner.service;

import java.util.List;

import com.example.pro.banner.entity.Banner;
import com.example.pro.banner.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "bannerCache")  // 定义这个注解，这个类里面的所有方法的缓存都属于这个缓存组，类方法的注解可以不加value，省事
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Cacheable
    public PageInfo<Banner> findAll(Banner banner) {
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        String orderBy = "id desc";
        PageHelper.startPage(1, 2, orderBy);
        List<Banner> banners = bannerMapper.findAll(banner);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(banners);
        return pageInfo;
    }


    @Cacheable(key = "#id")
    public Banner findById(Integer id) {
        return bannerMapper.findById(id);
    }

    @Cacheable(key = "#p0")
    public Integer create(Banner banner) {
        return bannerMapper.create(banner);
    }

    @CachePut(value = "banneCache", key = "banners")
    public Integer modify(Banner banner) {
        return bannerMapper.modify(banner);
    }

    @CacheEvict(value = "bannerCache", key = "banners")
    public Integer delete(Integer id) {
        return bannerMapper.delete(id);
    }
}