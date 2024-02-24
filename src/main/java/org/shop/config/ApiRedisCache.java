package org.shop.config;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.lang.Nullable;

import java.time.Duration;

public class ApiRedisCache extends RedisCache {

    protected ApiRedisCache(RedisCache redisCache) {
        super(redisCache.getName(), redisCache.getNativeCache(), redisCache.getCacheConfiguration());
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        Object cacheValue = preProcessCacheValue(value);
        if (!isAllowNullValues() && cacheValue == null) {
            throw new IllegalArgumentException(String.format(
                    "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                    getName()));
        }
        getNativeCache().put(getName(), serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue), getTtl());
    }

    private Duration getTtl() {
        return Duration.ofSeconds(3600);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        Object cacheValue = preProcessCacheValue(value);
        if (!isAllowNullValues() && cacheValue == null) {
            return get(key);
        }
        byte[] result = getNativeCache().putIfAbsent(getName(), serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue),
                getTtl());
        if (result == null) {
            return null;
        }
        return new SimpleValueWrapper(fromStoreValue(deserializeCacheValue(result)));
    }
}
