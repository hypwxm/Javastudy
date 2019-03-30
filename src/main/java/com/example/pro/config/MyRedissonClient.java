package com.example.pro.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.pro.utils.redisson.RedissonConfig;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class MyRedissonClient {
    private static class SingleClient {
        private static RedissonClient client = RedissonConfig.ymlConfig("redssion.yml");
    }

    public static RedissonClient getClient() {
        return SingleClient.client;
    }

    /**
     * redisson客户端
     * 
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        Config config = Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream());
        // config.setCodec(new JsonJacksonCodec()); 默认
        return Redisson.create(config);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>(16);
        // create "testMap" cache with ttl = 24 minutes and maxIdleTime = 12 minutes
        // config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

}