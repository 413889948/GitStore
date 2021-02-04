package com.sunsharing.newone.demo.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员表
 *
 * @author admin
 * @since 2021-02-02 23:28:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_ec_newone_administrate")
public class AdministrateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "UUID")
    private String  uuid;

    /**
     * 管理员账号
     */
    @TableField(value = "ACCOUNT")
    private String account;

    /**
     * 管理员密码
     */
    @TableField(value = "PASSWORD")
    private String password;

    /**
     * 注销标识，0：未注销、1：注销
     */
    @TableField(value = "ZXBS")
    @TableLogic(value = "0", delval = "1")
    private String zxbs;


}
