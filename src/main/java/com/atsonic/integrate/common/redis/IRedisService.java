package com.atsonic.integrate.common.redis;


import java.util.List;
import java.util.Set;

/**
 * 2.0版本完善
 */
public interface IRedisService {

    boolean set(String key, Object value);

    boolean set(byte[] key, byte[] value);

    boolean set(String key, Object value, long time);//设置缓存失效时间

    String get(String key);

    String getByStringRedisTemplate(String key);

    <T> List<T> getList(String key, Class<T> clz);

    byte[] getByte(byte[] key);

    <T> boolean setObject(String key, T t);

    <T> T getObject(String key);

    boolean expire(String key, long expire);

    boolean expire(byte[] key, long expire);

    long getExpire(String key);

    boolean hasKey(String key);

    void del(String... key);

    void del(byte[] key);

    long flushDB();

    Long dbSize();

    Set<byte[]> keys(String pattern);

    Set<String> keysByStringRedisTemplate(String pattern);

    Set<byte[]> keys(byte[] pattern);
}