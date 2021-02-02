package com.sunsharing.newone.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.entity.query.user.UserCreate;
import com.sunsharing.newone.demo.entity.query.user.UserDetail;
import com.sunsharing.newone.demo.entity.query.user.UserModify;
import com.sunsharing.share.boot.framework.auth.User;
import java.util.List;

/**
 * 用户信息表 服务类
 *
 * @author admin
 * @since 2021-01-29 17:14:48
 */
public interface UserService extends IService<UserEntity> {

    String addUser(UserCreate obj, User user);

    String updateUser(UserModify obj, User user);

    UserDetail getUser(String id);

    boolean deleteUser(List<String> ids);

}
