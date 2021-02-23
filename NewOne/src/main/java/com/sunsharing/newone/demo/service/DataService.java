package com.sunsharing.newone.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.newone.demo.entity.db.DataEntity;
import com.sunsharing.newone.demo.entity.query.data.DataSearch;

import java.util.List;

/**
 * 表码表 服务类
 *
 * @author admin
 * @since 2021-02-01 16:54:17
 */
public interface DataService extends IService<DataEntity> {

    /**
     * 获取性别表码列表
     * @return 列表
     */
    List<DataSearch> getSex();

    /**
     * 获取注销表码列表
     * @return 列表
     */
    List<DataSearch> getZxbs();

}
