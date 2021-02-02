package com.sunsharing.groupid.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.groupid.demo.dao.mapper.EcNewoneDistrictMapper;
import com.sunsharing.groupid.demo.entity.db.EcNewoneDistrictEntity;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictCreate;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictDetail;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictModify;
import com.sunsharing.groupid.demo.service.EcNewoneDistrictService;
import com.sunsharing.share.boot.framework.auth.User;
import com.sunsharing.share.boot.framework.code.CodeLoader;
import com.sunsharing.share.common.mapper.BeanMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区表 服务实现类
 *
 * @author admin
 * @since 2021-01-26 00:11:18
 */
@Service
public class EcNewoneDistrictServiceImpl extends ServiceImpl<EcNewoneDistrictMapper, EcNewoneDistrictEntity> implements EcNewoneDistrictService {

    @Autowired
    private CodeLoader codeLoader;


    @Transactional
    public Integer addEcNewoneDistrict(EcNewoneDistrictCreate obj, User user) {
        EcNewoneDistrictEntity entity = BeanMapper.map(obj, EcNewoneDistrictEntity.class);
        save(entity);
        return entity.getDistrictId();
    }

    @Transactional
    public Integer updateEcNewoneDistrict(EcNewoneDistrictModify obj, User user) {
        Integer id = obj.getDistrictId();
        EcNewoneDistrictEntity entity = getById(id);
        BeanMapper.map(obj, entity);
        updateById(entity);
        return id;
    }

    public EcNewoneDistrictDetail getEcNewoneDistrict(Integer id) {
        EcNewoneDistrictEntity entity = getById(id);
        EcNewoneDistrictDetail detail = BeanMapper.map(entity, EcNewoneDistrictDetail.class);

        return detail;
    }

    public boolean deleteEcNewoneDistrict(List<Integer> ids) {
        return removeByIds(ids);
    }

}
