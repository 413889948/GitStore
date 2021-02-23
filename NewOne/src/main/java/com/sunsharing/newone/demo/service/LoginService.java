/*
 * @(#) LoginService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 14:15:08
 */

package com.sunsharing.newone.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.newone.demo.entity.data.FlagEntity;
import com.sunsharing.newone.demo.entity.data.TransitionEntity;
import com.sunsharing.newone.demo.entity.data.UserLoginEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 黄祎翔
 * @time 2021/2/2 14:15
 */
public interface LoginService extends IService<UserEntity> {

    /**
     * 获取验证码信息
     * @param session 用于记录信息于客户端session
     * @return 图片信息
     */
    byte[] getVerify(HttpSession session, HttpServletResponse response);

    /**
     * 进行验证码的session验证，
     * @param session 用于记录信息于客户端session
     * @return 客户端是否需要输入验证码
     */
    FlagEntity getVerifySession(HttpSession session);

    /**
     * 用户登录方法
     * @param session 记录相关信息
     * @param userLogin 登录信息
     * @return 用户id
     */
    String userLogin(HttpSession session, UserLoginEntity userLogin);

    /**
     * 核验用户登录状态权限信息
     * @param session 记录相关信息
     * @return 用户id
     */
    TransitionEntity transition(HttpSession session);

    /**
     * 账户注销
     * @param session 客户端信息
     * @return 是否成功
     */
    FlagEntity outLogin(HttpSession session);

}
