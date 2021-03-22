/*
 * @(#) RegisterService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-03 14:28:08
 */

package com.sunsharing.newone.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.newone.demo.entity.data.RegisterUserEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;

/**
 * @author 黄祎翔
 * @time 2021/2/3 14:28
 */
public interface RegisterService extends IService<UserEntity> {


    /**
     * 注册用户
     * @param registerUserEntity 注册相关信息
     * @return 注册id
     */
    String registerUser(RegisterUserEntity registerUserEntity);
}
