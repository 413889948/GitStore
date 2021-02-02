package com.sunsharing.groupid.demo.entity.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_EC_NEWONE_ALLUSER")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户UUID
     */
    @TableId("USER_UUID")
    private String userUuid;

    /**
     * 用户性别（引用表码）
     */
    @TableField(value = "USER_SEX")
    private String userSex;

    /**
     * 用户账号
     */
    @TableField(value = "USER_NAME")
    private String userName;

    /**
     * 用户密码
     */
    @TableField(value = "USER_PASSWORD")
    private String userPassword;

    /**
     * 用户手机号
     */
    @TableField(value = "USER_PHONE")
    private String userPhone;

    /**
     * 用户邮箱
     */
    @TableField(value = "USER_EMAIL")
    private String userEmail;

    /**
     * 用户所属区（引用区表ID）
     */
    @TableField(value = "USER_DISTRICT")
    private String userDistrict;

    /**
     * 用户最后登录时间
     */
    @TableField(value = "USER_LOGIN_TIME")
    private LocalDateTime userLoginTime;

    /**
     * 用户级别（引用表码）
     */
    @TableField(value = "USER_GRADE")
    private String userGrade;

    /**
     * 创建人员
     */
    @TableField(value = "USER_CREATE_USER_ID")
    private String userCreateUserId;

    /**
     * 创建时间
     */
    @TableField(value = "USER_CREATE_TIME")
    private LocalDateTime userCreateTime;

    /**
     * 更新人员
     */
    @TableField(value = "USER_UPDATE_USER_ID")
    private String userUpdateUserId;

    /**
     * 更新时间
     */
    @TableField(value = "USER_UPDATE_TIME")
    private LocalDateTime userUpdateTime;

    /**
     * 注销标识，0：未注销、1：注销
     */
    @TableField(value = "USER_ZXBS")
    private String userZxbs;


}
