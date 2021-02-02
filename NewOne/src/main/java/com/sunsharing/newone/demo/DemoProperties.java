/*
 * @(#) DemoProperties
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-11 16:50:09
 */

package com.sunsharing.newone.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Administrator
 */
@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperties {

    //定义各种属性
    //private String prop = "prop";

}
