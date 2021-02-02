package com.sunsharing.groupid.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunsharing.groupid.demo.entity.db.UserEntity;
import com.sunsharing.groupid.demo.entity.query.user.UserCreate;
import com.sunsharing.groupid.demo.entity.query.user.UserDetail;
import com.sunsharing.groupid.demo.entity.query.user.UserModify;
import com.sunsharing.share.boot.framework.auth.User;
import java.util.List;

/**
 * 用户信息表 服务类
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
public interface UserService extends IService<UserEntity> {

    String addUser(UserCreate obj, User user);

    String updateUser(UserModify obj, User user);

    UserDetail getUser(String id);

    boolean deleteUser(List<String> ids);

}
