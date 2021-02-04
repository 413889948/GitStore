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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunsharing.newone.demo.constant.enums.RoleEnums;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.entity.data.RegisterUserEntity;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.service.AdministrateService;
import com.sunsharing.newone.demo.service.impl.AdministrateServiceImpl;
import com.sunsharing.newone.demo.util.RedisUtil;
import com.sunsharing.share.common.mapper.BeanMapper;
import com.sunsharing.share.common.text.TextValidator;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class DemoApplicationTests {

    @Autowired
    private AdministrateService service;

    @Test
    void contextLoads() {

        boolean mobileExact = TextValidator.isMobileExact("15960646046");
        boolean noneBlank = StringUtils.isNoneBlank("10", "60");
        System.out.println(noneBlank);
        System.out.println(mobileExact);
    }

}
