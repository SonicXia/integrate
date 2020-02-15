package com.atsonic.integrate.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static java.util.Collections.singletonMap;

/**
  * 自定义redisTemplate的序列化规则；改变默认的序列化规则（JdkSerializationRedisSerializer --> Jackson2JsonRedisSerializer）
  *
  * 说明：RedisTemplate默认使用的是JdkSerializationRedisSerializer
  *      JdkSerializationRedisSerializer：JDK自带的序列化方式、存储的字符串内容在序列化的情况下偏长，会占用过多的内存
  *      Jackson2JsonRedisSerializer：json数据格式、序列化时间和序列化之后内容的长度都比较好
  *
  * 注：以下处理方式适用于springboot 2.x 集成的redis
 */
@Configuration
public class MyRedisConfig {

    @Autowired
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 解决查询缓存转换异常的问题
     * @return
     */
    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(){
        ObjectMapper om = new ObjectMapper();
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    /**
     * 没生效！！
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
//                .entryTtl(Duration.ofSeconds(15L))//设置过期时间
                .disableCachingNullValues();// 禁用缓存空值，不缓存null校验

        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration);
        return builder.build();
    }

//    /**
//     * 自定义redisTemplate的序列化规则；改变默认的序列化规则（JdkSerializationRedisSerializer --> Jackson2JsonRedisSerializer）
//     *
//     * 说明：RedisTemplate默认使用的是JdkSerializationRedisSerializer
//     *      JdkSerializationRedisSerializer：JDK自带的序列化方式、存储的字符串内容在序列化的情况下偏长，会占用过多的内存
//     *      Jackson2JsonRedisSerializer：json数据格式、序列化时间和序列化之后内容的长度都比较好
//     *
//     * 注：以下处理方式适用于springboot 2.x 集成的redis
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
//
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        //解决查询缓存转换异常的问题
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//
//        /* 默认配置， 默认超时时间为30s */
//        RedisCacheConfiguration defaultCacheConfig  = RedisCacheConfiguration
//                .defaultCacheConfig()
////                .entryTtl(Duration.ofSeconds(20L))    // 设置过期时间
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
//                .disableCachingNullValues();
//
//        /* 配置test的超时时间为120s*/
//        return RedisCacheManager
//                .builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory))
//                .cacheDefaults(defaultCacheConfig)
//                // 指定某key的缓存策略（如当前"user::1"）
//                .withInitialCacheConfigurations(
//                        singletonMap(
//                                "user::1", // 指定的key
//                                RedisCacheConfiguration.defaultCacheConfig()
//                                                        .entryTtl(Duration.ofSeconds(10L))
//                                                        .disableCachingNullValues()
//                        )
//                )
//                // 把cache装饰成支持事务的cache
//                .transactionAware()
//                .build();
//
//    }

}
