package com.sunsharing.newone.demo.entity.query.data;

import lombok.Data;

/**
 * 表码表 创建对象
 */
@Data
public class DataSearch {


    /**
     * 名称
     */
    private String definition;

    /**
     * 代号
     */
    private String value;

    /**
     * 类别字段
     */
    private String kind;
}
