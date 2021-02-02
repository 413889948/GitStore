/*
 * @(#) PasswordUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-29 11:33:53
 */

package com.sunsharing.newone.demo.util;

import com.sunsharing.share.common.text.EncodeUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 黄祎翔
 * @time 2021/1/29 11:33
 */
@Component
public class PasswordUtil {


    private static String salt;

    @Value("${password-util.salt}")
    public void setSalt(String saltyml) {
        salt = saltyml;
    }

    /**
     * 为密码进行salt+hash二次加密
     * @param password 原密码值
     * @return 加密后密码值
     */
    public static String saltEncryptionUtil(String password) {
        return EncodeUtil.md5(salt + password);
    }
}
