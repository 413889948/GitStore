/*
 * @(#) LoginController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 11:09:36
 */

package com.sunsharing.newone.demo.web.controller;

import com.sunsharing.newone.demo.entity.data.FlagEntity;
import com.sunsharing.newone.demo.entity.data.TransitionEntity;
import com.sunsharing.newone.demo.entity.data.UserLoginEntity;
import com.sunsharing.newone.demo.service.LoginService;
import com.sunsharing.share.boot.framework.annotation.ShareRest;
import com.sunsharing.share.boot.framework.entity.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄祎翔
 * @time 2021/2/2 11:09
 */
@RestController
@RequestMapping
@Slf4j
@ShareRest
public class LoginController {


    @Autowired
    LoginService loginService;

    /**
     * 获取验证码图片资源的api
     * @param session 用于记录客户端验证码信息
     * @return 图片信息
     */
    @GetMapping(value = "/newOne/verify.do",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] verify(HttpSession session) {
        return loginService.getVerify(session);
    }

    /**
     * 验证当前客户端输入密码错误次数是否超过2次
     * @param session 用于记录客户端验证码信息
     * @return 用户是否需要输入验证码
     */
    @GetMapping(value = "/newOne/verifySession.do")
    public FlagEntity verifySession(HttpSession session) {
        return loginService.getVerifySession(session);
    }

    /**
     * 用户登录
     * @param session 客户端session信息
     * @param obj 登录信息
     * @return 登录账户id
     */
    @PostMapping(value = "/newOne/login.do")
    public Id<String> login(HttpSession session,@RequestBody @Valid UserLoginEntity obj ) {
        return Id.build(loginService.userLogin(session,obj));
    }

    /**
     * 验证用户权限信息
     * @param session 客户端session信息
     * @return 登录账户id
     */
    @GetMapping(value = "/newOne/transition.do")
    public TransitionEntity transitionUser(HttpSession session) {
        return loginService.transition(session);
    }


}
