package com.sunsharing.newone.demo.entity.query.user;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户信息表 创建对象
 */
@Data
public class UserSearch {


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
     * 创建人员
     */
    private String createUserId;

    /**
     * 创建时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime createTime;

    /**
     * 更新人员
     */
    private String updateUserId;

    /**
     * 更新时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime updateTime;

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
     * 用户最后登录时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime loginTime;

    /**
     * 注销标识，0：未注销、1：注销
         * 对应表码名EN_ZXBS
     */
    private String zxbs;
}
