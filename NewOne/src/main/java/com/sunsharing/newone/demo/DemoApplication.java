/*
 * @(#) DemoApplication
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-11 16:50:09
 */

package com.sunsharing.newone.demo;

import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.share.boot.framework.annotation.EnableFrameWork;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = {"com.sunsharing.newone.demo"})
@EnableFrameWork
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.sunsharing.newone.demo.dao.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
