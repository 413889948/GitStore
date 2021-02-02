package com.sunsharing.groupid.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.groupid.demo.entity.db.EcNewoneDistrictEntity;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictCreate;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictDetail;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictModify;
import com.sunsharing.share.boot.framework.auth.User;
import java.util.List;

/**
 * 区表 服务类
 *
 * @author admin
 * @since 2021-01-26 00:11:18
 */
public interface EcNewoneDistrictService extends IService<EcNewoneDistrictEntity> {

    Integer addEcNewoneDistrict(EcNewoneDistrictCreate obj, User user);

    Integer updateEcNewoneDistrict(EcNewoneDistrictModify obj, User user);

    EcNewoneDistrictDetail getEcNewoneDistrict(Integer id);

    boolean deleteEcNewoneDistrict(List<Integer> ids);

}
