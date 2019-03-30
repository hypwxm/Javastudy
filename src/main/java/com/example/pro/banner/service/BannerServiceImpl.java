package com.example.pro.banner.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.pro.banner.entity.Banner;
import com.example.pro.banner.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "bannerCache") //
// 定义这个注解，这个类里面的所有方法的缓存都属于这个缓存组，类方法的注解可以不加value，省事
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    // @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(key = "targetClass.getName() + '.' + methodName")
    public PageInfo<Banner> findAll(Banner banner) {
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        String orderBy = "id desc";
        PageHelper.startPage(1, 2, orderBy);
        List<Banner> banners = bannerMapper.findAll(banner);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(banners);
        return pageInfo;
    }

    // @Cacheable(key = "#id")
    public Banner findById(Integer id) {

        String bannerStr = (String) redisTemplate.opsForValue().get("bannerCache::" + id);
        System.out.println("adadad" + bannerStr);
        if (bannerStr != null) {
            Banner cacheBanner = new Banner();
            cacheBanner = JSON.parseObject(bannerStr, Banner.class);
            if (cacheBanner.getCreateTime() != null) {
                System.out.println("数据来自缓存");
                return cacheBanner;
            }
        }

        Banner banner = bannerMapper.findById(id);

        redisTemplate.opsForValue().set("bannerCache::" + banner.getId(), JSON.toJSONString(banner));

        return bannerMapper.findById(id);
    }

    // @Cacheable(key = "#p0")
    public Banner create(Banner banner) {
        bannerMapper.create(banner);
        System.out.println(JSON.toJSONString(banner));

        // MyRedissonClient.getClient().

        // redisTemplate.opsForValue().set("bannerCache::" + banner.getId(),
        // JSON.toJSONString(banner));
        return banner;
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