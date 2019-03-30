package com.example.pro;

import com.example.pro.utils.cache.redis.RedisCache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@MapperScan("com.example.pro.banner.mapper")
@ComponentScan(basePackages = "com.example.pro.*", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = { RedisCache.class }) })
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
