package org.shop.config;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import java.util.Map;

public class ApiRedisCacheManager extends RedisCacheManager {

    public ApiRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> configurationMap) {
        super(cacheWriter, defaultCacheConfiguration, configurationMap);
    }

    @Override
    protected RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        RedisCache redisCache = super.createRedisCache(name, cacheConfig);
        return new ApiRedisCache(redisCache);
    }
}
