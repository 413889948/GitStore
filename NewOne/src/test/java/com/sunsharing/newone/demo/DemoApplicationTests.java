/*
 * @(#) DemoApplicationTests
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-11 16:50:09
 */

package com.sunsharing.newone.demo;

import com.sunsharing.newone.demo.util.RedisUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCache;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Resource
    private RedisUtil redisUtils;

    @Test
    void contextLoads() {
        redisUtils.set("test","123456");
        String a = redisUtils.get("test");
        log.info(a);
    }

}
