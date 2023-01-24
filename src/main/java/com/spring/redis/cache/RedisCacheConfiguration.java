package com.spring.redis.cache;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RedisCacheConfiguration {

    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return  builder -> builder
                    .withCacheConfiguration("user",
                            org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).disableCachingNullValues())
                .withCacheConfiguration("adgroups", org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).disableCachingNullValues())
        ;
    }

}
