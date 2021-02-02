package com.sunsharing.groupid.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.groupid.demo.dao.mapper.UserMapper;
import com.sunsharing.groupid.demo.entity.db.UserEntity;
import com.sunsharing.groupid.demo.entity.query.user.UserCreate;
import com.sunsharing.groupid.demo.entity.query.user.UserDetail;
import com.sunsharing.groupid.demo.entity.query.user.UserModify;
import com.sunsharing.groupid.demo.service.UserService;
import com.sunsharing.share.boot.framework.auth.User;
import com.sunsharing.share.boot.framework.code.CodeLoader;
import com.sunsharing.share.common.mapper.BeanMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息表 服务实现类
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private CodeLoader codeLoader;


    @Transactional
    public String addUser(UserCreate obj, User user) {
        UserEntity entity = BeanMapper.map(obj, UserEntity.class);
        save(entity);
        return entity.getUserUuid();
    }

    @Transactional
    public String updateUser(UserModify obj, User user) {
        String id = obj.getUserUuid();
        UserEntity entity = getById(id);
        BeanMapper.map(obj, entity);
        updateById(entity);
        return id;
    }

    public UserDetail getUser(String id) {
        UserEntity entity = getById(id);
        UserDetail detail = BeanMapper.map(entity, UserDetail.class);

        return detail;
    }

    public boolean deleteUser(List<String> ids) {
        return removeByIds(ids);
    }

}
