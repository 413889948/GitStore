package com.sunsharing.newone.demo.entity.query.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 用户信息表 创建对象
 */
@Data
public class UserModify {


    /**
     * 用户UUID
     */
    @NotBlank
    private String uuid;


    /**
     * 用户账号
     */
    @NotBlank
    private String account;


    /**
     * 用户密码
     */
    @Size(min=32, max=32)
    private String password;


    /**
     * 用户手机号
     */
    @NotBlank
    @Pattern(regexp="^1[345678]\\d{9}$")
    private String phone;


    /**
     * 用户邮箱
     */
    @NotBlank
    @Email
    private String email;


    /**
     * 用户性别（引用表码）
         * 对应表码名EN_SEX
     */
    @NotBlank
    private String sex;


    /**
     * 用户所属区（引用区表ID）
         * 对应表码名EN_DISTRICT
     */
    @NotBlank
    private String district;


    /**
     * 注销标识，0：未注销、1：注销
         * 对应表码名EN_ZXBS
     */
    private String zxbs;

}
