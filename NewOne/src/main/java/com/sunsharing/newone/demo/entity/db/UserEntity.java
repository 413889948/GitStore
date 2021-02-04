package com.sunsharing.newone.demo.entity.db;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 *
 * @author admin
 * @since 2021-01-29 17:14:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_ec_newone_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户UUID
     */
    @TableId("UUID")
    private String uuid;

    /**
     * 用户账号
     */
    @TableField(value = "ACCOUNT")
    private String account;

    /**
     * 用户密码
     */
    @TableField(value = "PASSWORD")
    private String password;

    /**
     * 用户手机号
     */
    @TableField(value = "PHONE")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "EMAIL")
    private String email;

    /**
     * 创建人员
     */
    @TableField(value = "CREATE_USER_ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新人员
     */
    @TableField(value = "UPDATE_USER_ID")
    private String updateUserId;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 用户性别（引用表码）
     */
    @TableField(value = "SEX")
    private String sex;

    /**
     * 用户所属区（引用区表ID）
     */
    @TableField(value = "DISTRICT")
    private String district;

    /**
     * 用户最后登录时间
     */
    @TableField(value = "LOGIN_TIME")
    private LocalDateTime loginTime;

    /**
     * 注销标识，0：未注销、1：注销
     */
    @TableField(value = "ZXBS")
    @TableLogic(value = "0", delval = "1")

    private String zxbs;


}
