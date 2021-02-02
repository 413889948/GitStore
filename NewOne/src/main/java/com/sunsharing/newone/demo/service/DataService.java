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

    List<DataSearch> getSex();
    List<DataSearch> getZxbs();

}
