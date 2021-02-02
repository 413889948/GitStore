package com.sunsharing.groupid.demo.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区表
 *
 * @author admin
 * @since 2021-01-26 00:11:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_EC_NEWONE_DISTRICT")
public class EcNewoneDistrictEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区名
     */
    @TableField(value = "DISTRICT_NAME")
    private String districtName;

    /**
     * 区表ID
     */
    @TableId(value = "DISTRICT_ID", type = IdType.AUTO)
    @TableLogic(value = "2", delval = "1")

    private Integer districtId;


}
