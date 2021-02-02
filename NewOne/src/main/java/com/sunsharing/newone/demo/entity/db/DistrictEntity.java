package com.sunsharing.newone.demo.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区表
 *
 * @author admin
 * @since 2021-02-02 00:23:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_ec_newone_district")
public class DistrictEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区表ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 区名
     */
    @TableField(value = "DEFINITION")
    private String definition;

    /**
     * 区序号
     */
    @TableField(value = "VALUE")
    private String value;


}
