/*
 * @(#) UserLogin
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 12:10:50
 */

package com.sunsharing.newone.demo.entity.data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author 黄祎翔
 * @time 2021/2/2 12:10
 */
@Data
public class UserLoginEntity {
    /**
     * 用户账号
     */
    @NotBlank
    private String userName;

    /**
     * 用户密码
     */
    @Size(min = 32, max = 32)
    private String password;

    /**
     * 用户验证码
     */
    private String verificationCode;
}
