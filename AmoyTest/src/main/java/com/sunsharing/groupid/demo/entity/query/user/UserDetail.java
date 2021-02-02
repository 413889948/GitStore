package com.sunsharing.groupid.demo.entity.query.user;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户信息表 创建对象
 */
@Data
public class UserDetail {


    /**
     * 用户UUID
     */
    private String userUuid;

    /**
     * 用户性别（引用表码）
     */
    private String userSex;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户所属区（引用区表ID）
     */
    private String userDistrict;

    /**
     * 用户最后登录时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime userLoginTime;

    /**
     * 用户级别（引用表码）
     */
    private String userGrade;

    /**
     * 创建人员
     */
    private String userCreateUserId;

    /**
     * 创建时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime userCreateTime;

    /**
     * 更新人员
     */
    private String userUpdateUserId;

    /**
     * 更新时间，时间格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime userUpdateTime;

    /**
     * 注销标识，0：未注销、1：注销
     */
    private String userZxbs;
}
