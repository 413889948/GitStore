/*
 * @(#) RegisterUserEntity
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-03 14:24:02
 */

package com.sunsharing.newone.demo.entity.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author 黄祎翔
 * @time 2021/2/3 14:24
 */
@Data
public class RegisterUserEntity {
    /**
     * 用户账号
     */
    @NotBlank
    private String account;

    /**
     * 用户密码
     */
    @Size(min = 32, max = 32)
    private String password;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    @Email
    private String email;

    /**
     * 用户性别（引用表码）
     * 对应表码名EN_SEX
     */
    private String sex;

    /**
     * 用户所属区（引用区表ID）
     * 对应表码名EN_DISTRICT
     */
    private String district;

    /**
     * 注销标识，0：未注销、1：注销
     * 对应表码名EN_ZXBS
     */
    private String zxbs;

    /**
     * 角色信息，common：普通用户、administrate：管理员
     */
    @NotBlank
    private String role;

    /**
     * 管理员秘钥
     */
    private String key;

}
