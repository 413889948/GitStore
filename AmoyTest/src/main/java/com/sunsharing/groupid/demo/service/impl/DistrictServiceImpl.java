package com.sunsharing.groupid.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.groupid.demo.dao.mapper.DistrictMapper;
import com.sunsharing.groupid.demo.entity.db.DistrictEntity;
import com.sunsharing.groupid.demo.entity.query.district.DistrictCreate;
import com.sunsharing.groupid.demo.entity.query.district.DistrictDetail;
import com.sunsharing.groupid.demo.entity.query.district.DistrictModify;
import com.sunsharing.groupid.demo.service.DistrictService;
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
 * @since 2021-01-22 22:11:15
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, DistrictEntity> implements DistrictService {

    @Autowired
    private CodeLoader codeLoader;


    @Transactional
    public Integer addDistrict(DistrictCreate obj, User user) {
        DistrictEntity entity = BeanMapper.map(obj, DistrictEntity.class);
        save(entity);
        return entity.getDistrictId();
    }

    @Transactional
    public Integer updateDistrict(DistrictModify obj, User user) {
        Integer id = obj.getDistrictId();
        DistrictEntity entity = getById(id);
        BeanMapper.map(obj, entity);
        updateById(entity);
        return id;
    }

    public DistrictDetail getDistrict(Integer id) {
        DistrictEntity entity = getById(id);
        DistrictDetail detail = BeanMapper.map(entity, DistrictDetail.class);

        return detail;
    }

    public boolean deleteDistrict(List<Integer> ids) {
        return removeByIds(ids);
    }

}
