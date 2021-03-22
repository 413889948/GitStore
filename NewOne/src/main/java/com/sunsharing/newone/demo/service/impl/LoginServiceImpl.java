/*
 * @(#) LoginServiceImpl
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 14:16:32
 */

package com.sunsharing.newone.demo.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.dao.mapper.UserMapper;
import com.sunsharing.newone.demo.entity.data.FlagEntity;
import com.sunsharing.newone.demo.entity.data.TransitionEntity;
import com.sunsharing.newone.demo.entity.data.UserLoginEntity;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.service.LoginService;
import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.newone.demo.util.RedisUtil;
import com.sunsharing.share.common.base.IdGenerator;
import com.sunsharing.share.common.base.exception.ShareBusinessException;
import com.sunsharing.share.common.base.exception.ShareResponseCode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

/**
 * @author 黄祎翔
 * @time 2021/2/2 14:16
 */
@Service
@Log4j2
public class LoginServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements LoginService {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisUtil redisUtil;

    @Autowired(required = false)
    AdministrateMapper administrateMapper;

    private String sessionVerify = "NEWONE_USER_VERIFY";

    private String sessionUserAccount = "NEWONE_USER_ACCOUNT";

    private String sessionUserKey = "NEWONE_USER_KEY";

    private String sessionUserLogin = "NEWONE_USER_LOGIN_";


    @Override
    public byte[] getVerify(HttpSession session, HttpServletResponse response) {
        String createText = defaultKaptcha.createText();
        BufferedImage bufferedImage = defaultKaptcha.createImage(createText);
        // 将验证码记录于客户端session内
        session.setAttribute(sessionVerify, createText);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", out);
            Cookie cookie = new Cookie("VerificationError", "true");
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return out.toByteArray();
        } catch (IOException e) {
            log.error("图片io异常",e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("out回收错误",e);
            }
        }
        throw new ShareBusinessException(ShareResponseCode.INNER_SYSTEM_ERROR);
    }

    @Override
    public FlagEntity getVerifySession(HttpSession session) {
        String userVerify = (String) session.getAttribute(sessionVerify);
        return new FlagEntity(StringUtils.isNotEmpty(userVerify));
    }

    @Override
    public String userLogin(HttpSession session, UserLoginEntity userLogin) {
        // 获取验证码
        String verify = (String) session.getAttribute(sessionVerify);
        session.setAttribute(sessionUserAccount,"");
        session.setAttribute(sessionUserKey,"");
        session.setAttribute(sessionUserKey,"");
        String verificationCode = userLogin.getVerificationCode();
        // 验证验证码
        if (!StringUtils.equalsIgnoreCase(verify,verificationCode) && verify != null) {
            throw new ShareBusinessException(ShareResponseCode.IMG_VALID_CODE_FAIL);
        }
        // 查询账户密码是否正确
        AdministrateEntity administrateEntity = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>()
            .eq("ACCOUNT", userLogin.getUserName())
            .eq("PASSWORD", PasswordUtil.saltEncryptionUtil(userLogin.getPassword())));
        if (administrateEntity == null) {
            UserEntity userEntity = getOne(new QueryWrapper<UserEntity>()
                .eq("ACCOUNT", userLogin.getUserName())
                .eq("PASSWORD", PasswordUtil.saltEncryptionUtil(userLogin.getPassword())));
            if (userEntity == null) {
                throw new ShareBusinessException(ShareResponseCode.LOGIN_FAIL_ON_ACCOUNT_OR_PASSWORD_ERROR);
            } else {
                // 更新用户登录时间
                userEntity.setLoginTime(LocalDateTime.now());
                updateById(userEntity);
                // 将用户账户与临时key存入session与redis内
                String key = "2_" + IdGenerator.uuid2();
                session.setAttribute(sessionUserAccount,userEntity.getAccount());
                session.setAttribute(sessionUserKey,key);
                session.setAttribute(sessionUserKey,key);
                redisUtil.set(sessionUserLogin + userEntity.getAccount(),key);
                //返回信息
                return userEntity.getUuid();
            }
        }
        // 将用户账户与临时key存入session与redis内
        String key = "1_" + IdGenerator.uuid2();
        session.setAttribute(sessionUserAccount,administrateEntity.getAccount());
        session.setAttribute(sessionUserKey,key);
        session.setAttribute(sessionUserKey,key);
        redisUtil.set(sessionUserLogin + administrateEntity.getAccount(),key);
        //返回信息
        return administrateEntity.getUuid();
    }

    @Override
    public TransitionEntity transition(HttpSession session) {
        String accounnt = (String) session.getAttribute(sessionUserAccount);
        String key = (String) session.getAttribute(sessionUserKey);
        //获取redis信息进行验证
        String keyRedis = redisUtil.get(sessionUserLogin + accounnt);
        if (StringUtils.isNoneBlank(key,keyRedis) && StringUtils.equals(key,keyRedis)) {
            return new TransitionEntity(true,keyRedis.substring(0,1));
        }
        return new TransitionEntity(false,"");
    }

    @Override
    public FlagEntity outLogin(HttpSession session) {
        session.setAttribute(sessionUserAccount,"");
        session.setAttribute(sessionUserKey,"");
        session.setAttribute(sessionUserKey,"");
        return new FlagEntity(true);
    }


}
