package com.sunsharing.newone.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.newone.demo.entity.db.DataEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 表码表 Mapper 接口
 *
 * @author admin
 * @since 2021-02-01 16:54:17
 */
@Mapper
public interface DataMapper extends BaseMapper<DataEntity> {

}
