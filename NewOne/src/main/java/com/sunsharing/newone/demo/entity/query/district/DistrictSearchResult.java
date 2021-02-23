package com.sunsharing.newone.demo.entity.query.district;

import lombok.Data;

/**
 * 区表 创建对象
 */
@Data
public class DistrictSearchResult {


    /**
     * 区表ID
     */
    private Integer id;

    /**
     * 区名
     */
    private String definition;

    /**
     * 区序号
     */
    private String value;
}
