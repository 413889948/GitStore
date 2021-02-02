package com.sunsharing.groupid.demo.entity.query.district;

import lombok.Data;

/**
 * 区表 创建对象
 */
@Data
public class DistrictSearchResult {


    /**
     * 区名
     */
    private String districtName;

    /**
     * 区表ID
     */
    private Integer districtId;
}
