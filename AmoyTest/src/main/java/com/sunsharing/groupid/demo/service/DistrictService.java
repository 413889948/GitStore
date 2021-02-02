package com.sunsharing.groupid.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.groupid.demo.entity.db.DistrictEntity;
import com.sunsharing.groupid.demo.entity.query.district.DistrictCreate;
import com.sunsharing.groupid.demo.entity.query.district.DistrictDetail;
import com.sunsharing.groupid.demo.entity.query.district.DistrictModify;
import com.sunsharing.share.boot.framework.auth.User;
import java.util.List;

/**
 * 区表 服务类
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
public interface DistrictService extends IService<DistrictEntity> {

    Integer addDistrict(DistrictCreate obj, User user);

    Integer updateDistrict(DistrictModify obj, User user);

    DistrictDetail getDistrict(Integer id);

    boolean deleteDistrict(List<Integer> ids);

}
