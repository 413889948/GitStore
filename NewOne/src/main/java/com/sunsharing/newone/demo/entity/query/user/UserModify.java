package com.sunsharing.newone.demo.entity.query.user;

import lombok.Data;

/**
 * 用户信息表 创建对象
 */
@Data
public class UserModify {


    /**
     * 用户UUID
     */
    private String uuid;


    /**
     * 用户账号
     */
    private String account;


    /**
     * 用户密码
     */
    private String password;


    /**
     * 用户手机号
     */
    private String phone;


    /**
     * 用户邮箱
     */
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

}
