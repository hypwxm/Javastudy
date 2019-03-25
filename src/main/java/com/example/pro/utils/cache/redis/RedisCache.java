package com.example.pro.utils.cache.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCache extends CachingConfigurerSupport {

    // 自定义key生成策略，在@Cacheable中未指定key时会使用该策略
    @Override
    @Bean
    public KeyGenerator keyGenerator() {

        return new KeyGenerator() {
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    if (obj.toString() == null) {
                        continue;
                    }
                    sb.append(obj.toString());
                }
                System.out.println("调用Redis生成key：" + sb.toString());
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        redisCacheConfiguration = redisCacheConfiguration // 设置缓存的默认超时时间：30分钟
                .disableCachingNullValues() // 如果是空值，不缓存
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer())) // 设置key序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer()))); // 设置value序列化器

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }// 使用Jackson序列化器

    private RedisSerializer<Object> valueSerializer() {
        return new FastJsonRedisSerializer<>(Object.class);
    }
}
