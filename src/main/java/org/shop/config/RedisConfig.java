package org.shop.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {



    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private Map<String, RedisCacheConfiguration> getCacheConfigurations() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        return configurationMap;
    }
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfiguration = getRedisCacheConfiguration(3600L);
        Map<String, RedisCacheConfiguration> initialCacheConfigurations = getCacheConfigurations();
        return new ApiRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),defaultCacheConfiguration, initialCacheConfigurations);
    }

    private RedisCacheConfiguration getRedisCacheConfiguration(Long seconds) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        return RedisCacheConfiguration
                .defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                .entryTtl(Duration.ofSeconds(seconds))
                .computePrefixWith(cacheName -> cacheName + ":")
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();
    }
}
