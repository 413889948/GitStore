/*
 * @(#) WebConfig
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-04 12:06:00
 */

package com.sunsharing.newone.demo.config;

import com.sunsharing.newone.demo.web.interceptor.PowerInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 黄祎翔
 * @time 2021/2/4 12:06
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    PowerInterceptor powerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(powerInterceptor);
        registration.addPathPatterns("/newOne/user/**")
                    .addPathPatterns("/newOne/excel/dataImport.do");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/newOne/**").addResourceLocations("classpath:/static/");
    }

}
