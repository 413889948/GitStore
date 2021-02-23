/*
 * @(#) RedisUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-29 15:33:00
 */

package com.sunsharing.newone.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author 黄祎翔
 * @Time 2021/2/22 9:27
 * @ClassName RedisUtil.java
 * @Description Redis工具类，使用时使用bean注入
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * @auther: 黄祎翔
     * @create: 2021/2/22 9:10
     * @description: 获取redis内对应key的值
     * @param key 对应key值
     * @return key对应value值
     * 使用示例：
     * String value = redisUtil.get("key")
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @auther: 黄祎翔
     * @create: 2021/2/22 9:12
     * @description: 设置redis内对应的key:value 属性，默认过期时间为1小事
     * @param key 设置该值的key属性
     * @param value 设置该值的value属性
     * @return 是否设置成功
     * 使用示例：
     * boolean flag = redisUtil.set("key","value")
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,3600, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @auther: 黄祎翔
     * @create: 2021/2/22 9:14
     * @description: 更新缓存，可更新过期时间，操作为get后set
     * @param key 对应key
     * @param value 对应value
     * @return 操作是否成功
     * 使用示例：
     * boolean flag = redisUtil.getAndSet("key","set");
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
