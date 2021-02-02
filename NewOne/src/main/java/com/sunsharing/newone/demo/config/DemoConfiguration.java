/*
 * @(#) DemoConfiguration
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-11 16:50:09
 */

package com.sunsharing.newone.demo.config;

import com.sunsharing.newone.demo.DemoProperties;
import com.sunsharing.newone.demo.web.interceptor.PowerInterceptor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@Slf4j
public class DemoConfiguration implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //注册TestInterceptor拦截器
            InterceptorRegistration registration = registry.addInterceptor(new PowerInterceptor());
            //所有路径都被拦截
            registration.addPathPatterns("/newOne/user/**");
            log.info("拦截到了");
        }



}
