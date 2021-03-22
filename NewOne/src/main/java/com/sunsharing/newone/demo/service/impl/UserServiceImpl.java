package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.constant.enums.MyResponseCode;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.dao.mapper.UserMapper;
import com.sunsharing.newone.demo.entity.data.RegisterUserEntity;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.entity.query.user.UserCreate;
import com.sunsharing.newone.demo.entity.query.user.UserDetail;
import com.sunsharing.newone.demo.entity.query.user.UserModify;
import com.sunsharing.newone.demo.service.RegisterService;
import com.sunsharing.newone.demo.service.UserService;
import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.share.boot.framework.code.CodeLoader;
import com.sunsharing.share.common.base.IdGenerator;
import com.sunsharing.share.common.base.exception.ShareBusinessException;
import com.sunsharing.share.common.base.exception.ShareResponseCode;
import com.sunsharing.share.common.mapper.BeanMapper;
import com.sunsharing.share.common.text.TextValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

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

    @Autowired(required = false)
    private AdministrateMapper administrateMapper;

    @Autowired
    private RegisterService register;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addUser(UserCreate obj, HttpSession session) {
        UserEntity userEntity = getOne(new QueryWrapper<UserEntity>()
            .eq("ACCOUNT", obj.getAccount()));
        if (userEntity != null) {
            throw new ShareBusinessException(MyResponseCode.NAME_REPETITION);
        }
        AdministrateEntity administrateEntity = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>()
            .eq("ACCOUNT", obj.getAccount()));
        if (administrateEntity != null) {
            throw new ShareBusinessException(MyResponseCode.NAME_REPETITION);
        }
        UserEntity entity = BeanMapper.map(obj, UserEntity.class);
        // 获取用户session数据进行验证
        String account = (String) session.getAttribute("NEWONE_USER_ACCOUNT");
        AdministrateEntity ae = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>().eq("ACCOUNT", account));
        entity.setCreateUserId(ae.getUuid());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateUserId(ae.getUuid());
        entity.setUpdateTime(LocalDateTime.now());
        // 警告原因是因为注解
        entity.setUuid(IdGenerator.uuid2());
        // 二次加密
        entity.setPassword(PasswordUtil.saltEncryptionUtil(entity.getPassword()));
        entity.setZxbs("0");
        save(entity);
        return entity.getUuid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addUserList(List<UserEntity> objList) {
        UserEntity userEntity = objList.get(0);
        RegisterUserEntity registerUserEntity = new RegisterUserEntity();
        RegisterUserEntity map = BeanMapper.map(userEntity, registerUserEntity);
        map.setRole("common");
        String id = register.registerUser(map);
        if (id == null) {
            log.error("注册过程出现异常");
            throw new ShareBusinessException(ShareResponseCode.INNER_SYSTEM_ERROR);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateUser(UserModify obj, HttpSession session) {
        String id = obj.getUuid();
        UserEntity entity = getById(id);
        boolean mobileExact = TextValidator.isMobileExact(entity.getPhone());
        boolean email = TextValidator.isEmail(entity.getEmail());
        if (!email || !mobileExact) {
            log.error("核验信息错误");
            throw new ShareBusinessException(ShareResponseCode.INPUT_ILLEGAL);
        }
        String account = (String) session.getAttribute("NEWONE_USER_ACCOUNT");
        AdministrateEntity ae = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>().eq("ACCOUNT", account));
        entity.setUpdateUserId(ae.getUuid());
        entity.setUpdateTime(LocalDateTime.now());
        updateById(entity);
        return id;
    }

    @Override
    public UserDetail getUser(String id) {
        UserEntity entity = getById(id);
        UserDetail detail = BeanMapper.map(entity, UserDetail.class);
        detail.setCnSex(codeLoader.getCodeStrToZwIfExists(detail.getSex(), "EN_SEX"));
        detail.setCnDistrict(codeLoader.getCodeStrToZwIfExists(detail.getDistrict(), "EN_DISTRICT"));
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(List<String> ids) {
        return removeByIds(ids);
    }

}
