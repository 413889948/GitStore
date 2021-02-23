package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.dao.mapper.DataMapper;
import com.sunsharing.newone.demo.entity.db.DataEntity;
import com.sunsharing.newone.demo.entity.query.data.DataSearch;
import com.sunsharing.newone.demo.service.DataService;
import com.sunsharing.share.common.mapper.BeanMapper;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.log4j.Log4j2;

/**
 * 表码表 服务实现类
 *
 * @author admin
 * @since 2021-02-01 16:54:17
 */
@Service
@Log4j2
public class DataServiceImpl extends ServiceImpl<DataMapper, DataEntity> implements DataService {



    @Override
    public List<DataSearch> getSex() {

        // 查询性别列表
        List<DataEntity> dataEntities = list(new QueryWrapper<DataEntity>().eq("KIND", "性别"));
        // 转换格式返回
        return BeanMapper.mapList(dataEntities,DataEntity.class,DataSearch.class);
    }

    @Override
    public List<DataSearch> getZxbs() {
        // 查询列表
        List<DataEntity> dataEntities = list(new QueryWrapper<DataEntity>().eq("KIND", "注销状态"));
        // 转换格式返回
        return BeanMapper.mapList(dataEntities,DataEntity.class,DataSearch.class);
    }

}
