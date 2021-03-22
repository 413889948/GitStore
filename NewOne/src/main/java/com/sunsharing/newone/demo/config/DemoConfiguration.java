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

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.sunsharing.newone.demo.DemoProperties;
import com.sunsharing.share.boot.framework.web.standard.core.ValidHandlerManager;
import com.sunsharing.share.boot.framework.web.standard.core.ValidHandlerRegistry;
import com.sunsharing.share.boot.framework.web.validator.DateStringValid;
import com.sunsharing.share.boot.framework.web.validator.MobileTelValid;
import com.sunsharing.share.common.base.exception.ShareResponseCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Administrator
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@Slf4j
public class DemoConfiguration implements WebMvcConfigurer {

    @Autowired
    ValidHandlerManager validHandlerManager;

    @PostConstruct
    public void setValidConfig() {
        // 添加Valid关联
        ValidHandlerRegistry validHandlerRegistry = new ValidHandlerRegistry();
        validHandlerRegistry.registerValidTrans(NotBlank.class, ShareResponseCode.VALID_FIELD_NOT_EMPTY);
        validHandlerRegistry.registerValidTrans(EnumValue.class, ShareResponseCode.VALID_FIELD_NUMERIC_RANGE_ILLEGAL);
        validHandlerRegistry.registerValidTrans(PositiveOrZero.class, ShareResponseCode.VALID_FIELD_NUMERIC_RANGE_ILLEGAL);
        validHandlerRegistry.registerValidTrans(MobileTelValid.class, ShareResponseCode.VALID_FIELD_TEL_ILLEGAL);
        validHandlerRegistry.registerValidTrans(DateStringValid.class, ShareResponseCode.VALID_FIELD_DATE_FORMAT_ILLEGAL);
        validHandlerRegistry.registerValidTrans(PastOrPresent.class, ShareResponseCode.VALID_FIELD_DATE_RANGE_ILLEGAL);
        validHandlerRegistry.registerValidTrans(Pattern.class, ShareResponseCode.VALID_FIELD_ILLEGAL);
        validHandlerRegistry.registerValidTrans(Email.class, ShareResponseCode.VALID_FIELD_ILLEGAL);
        validHandlerRegistry.registerValidTrans(Size.class, ShareResponseCode.VALID_FIELD_ILLEGAL);
        validHandlerManager.init(validHandlerRegistry);
    }

}
