package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.dao.mapper.UserMapper;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.entity.query.user.UserCreate;
import com.sunsharing.newone.demo.entity.query.user.UserDetail;
import com.sunsharing.newone.demo.entity.query.user.UserModify;
import com.sunsharing.newone.demo.service.UserService;
import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.share.boot.framework.auth.User;
import com.sunsharing.share.boot.framework.code.CodeLoader;
import com.sunsharing.share.common.base.IdGenerator;
import com.sunsharing.share.common.mapper.BeanMapper;
import com.sunsharing.share.common.text.EncodeUtil;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * 用户信息表 服务实现类
 *
 * @author admin
 * @since 2021-01-29 17:14:48
 */
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private CodeLoader codeLoader;


    @Transactional
    public String addUser(UserCreate obj, User user) {

        UserEntity entity = BeanMapper.map(obj, UserEntity.class);
        entity.setCreateUserId(user.getId());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateUserId(user.getId());
        entity.setUpdateTime(LocalDateTime.now());
        // 警告原因是因为注解
        entity.setUuid(IdGenerator.uuid2());
        // 二次加密
        entity.setPassword(PasswordUtil.saltEncryptionUtil(entity.getPassword()));
        entity.setZxbs("0");
        save(entity);
        return entity.getUuid();
    }

    @Transactional
    public String updateUser(UserModify obj, User user) {
        String id = obj.getUuid();
        UserEntity entity = getById(id);
        entity.setUpdateUserId(user.getId());
        entity.setUpdateTime(LocalDateTime.now());
        updateById(entity);
        return id;
    }

    public UserDetail getUser(String id) {
        UserEntity entity = getById(id);
        UserDetail detail = BeanMapper.map(entity, UserDetail.class);
        detail.setCnSex(codeLoader.getCodeStrToZwIfExists(detail.getSex(), "EN_SEX"));
        detail.setCnDistrict(codeLoader.getCodeStrToZwIfExists(detail.getDistrict(), "EN_DISTRICT"));
        return detail;
    }

    public boolean deleteUser(List<String> ids) {
        return removeByIds(ids);
    }

}
