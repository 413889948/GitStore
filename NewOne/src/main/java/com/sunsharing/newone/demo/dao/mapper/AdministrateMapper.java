package com.sunsharing.newone.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;

import org.apache.ibatis.annotations.Mapper;


/**
 * 管理员表 Mapper 接口
 *
 * @author admin
 * @since 2021-02-02 23:28:05
 */
@Mapper

public interface AdministrateMapper extends BaseMapper<AdministrateEntity> {


}
