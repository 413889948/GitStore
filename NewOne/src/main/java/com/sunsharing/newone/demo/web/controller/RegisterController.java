/*
 * @(#) RegisterController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-03 14:10:30
 */

package com.sunsharing.newone.demo.web.controller;

import com.sunsharing.newone.demo.entity.data.RegisterUserEntity;
import com.sunsharing.newone.demo.service.impl.RegisterServiceImpl;
import com.sunsharing.share.boot.framework.annotation.ShareRest;
import com.sunsharing.share.boot.framework.entity.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄祎翔
 * @time 2021/2/3 14:10
 */
@RestController
@RequestMapping
@Slf4j
@ShareRest
public class RegisterController {

    @Autowired
    RegisterServiceImpl registerService;

    /**
     * 用户注册
     * @param obj 注册用户对象
     */
    @PostMapping("/newOne/register.do")
    public Id<String> register(@RequestBody @Valid RegisterUserEntity obj) {
        return Id.build(registerService.registerUser(obj));
    }
}
