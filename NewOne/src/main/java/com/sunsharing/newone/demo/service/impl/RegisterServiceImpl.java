/*
 * @(#) RegisterService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-03 14:28:08
 */

package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.constant.enums.MyResponseCode;
import com.sunsharing.newone.demo.constant.enums.RoleEnums;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.dao.mapper.UserMapper;
import com.sunsharing.newone.demo.entity.data.RegisterUserEntity;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.service.RegisterService;
import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.share.common.base.IdGenerator;
import com.sunsharing.share.common.base.exception.ShareBusinessException;
import com.sunsharing.share.common.base.exception.ShareResponseCode;
import com.sunsharing.share.common.mapper.BeanMapper;
import com.sunsharing.share.common.text.TextValidator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.extern.log4j.Log4j2;

/**
 * @author 黄祎翔
 * @time 2021/2/3 14:28
 */
@Service
@Log4j2
public class RegisterServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements RegisterService {

    @Autowired(required = false)
    private AdministrateMapper administrateMapper;

    @Value("${administrate.key}")
    private String key;

    @Override
    @Transactional
    public String registerUser(RegisterUserEntity registerUserEntity) {
        // 用户是否已注册
        UserEntity userEntity = getOne(new QueryWrapper<UserEntity>()
            .eq("ACCOUNT", registerUserEntity.getAccount()));
        if (userEntity != null) {
            throw new ShareBusinessException(MyResponseCode.NAME_REPETITION);
        }
        AdministrateEntity administrateEntity = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>()
            .eq("ACCOUNT", registerUserEntity.getAccount()));
        if (administrateEntity != null) {
            throw new ShareBusinessException(MyResponseCode.NAME_REPETITION);
        }
        // 对密码进行加密
        registerUserEntity.setPassword(PasswordUtil.saltEncryptionUtil(registerUserEntity.getPassword()));
        switch (Objects.requireNonNull(RoleEnums.getByValue(registerUserEntity.getRole()))) {
            case ADMINISTRATE :
                //核验管理员秘钥
                if (!key.equals(registerUserEntity.getKey())) {
                    throw new ShareBusinessException(MyResponseCode.KEY_WRONG);
                }
                //储存用户信息
                AdministrateEntity entity = BeanMapper.map(registerUserEntity, AdministrateEntity.class);
                String acUuid = IdGenerator.uuid2();
                entity.setUuid(acUuid);
                administrateMapper.insert(entity);
                //返回信息
                return acUuid;
            case COMMON :
                //核验字段格式
                if (!TextValidator.isMobileExact(registerUserEntity.getPhone())
                    || !StringUtils.isNoneBlank(registerUserEntity.getSex(),registerUserEntity.getDistrict())) {
                    throw new ShareBusinessException(ShareResponseCode.VALID_FIELD_ILLEGAL);
                }
                //储存用户信息
                UserEntity entityUser = BeanMapper.map(registerUserEntity,UserEntity.class);
                String userUuid = IdGenerator.uuid2();
                entityUser.setUuid(userUuid);
                entityUser.setCreateUserId(userUuid);
                entityUser.setCreateTime(LocalDateTime.now());
                entityUser.setUpdateUserId(userUuid);
                entityUser.setUpdateTime(LocalDateTime.now());
                save(entityUser);
                // 返回信息
                return userUuid;
            default:
                throw new ShareBusinessException(ShareResponseCode.INPUT_ILLEGAL);
        }
    }
}
