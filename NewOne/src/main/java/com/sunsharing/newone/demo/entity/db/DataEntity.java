package com.sunsharing.newone.demo.entity.db;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表码表
 *
 * @author admin
 * @since 2021-02-01 16:54:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_ec_newone_data")
public class DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表码ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "DEFINITION")
    private String definition;

    /**
     * 代号
     */
    @TableField(value = "VALUE")
    private String value;

    /**
     * 类别字段
     */
    @TableField(value = "KIND")
    private String kind;


}
