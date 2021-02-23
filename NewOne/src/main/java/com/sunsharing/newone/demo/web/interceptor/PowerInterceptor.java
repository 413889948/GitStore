/*
 * @(#) PowerInterceptor
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-01-29 15:11:43
 */

package com.sunsharing.newone.demo.web.interceptor;

import com.sunsharing.newone.demo.util.RedisUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;


/**
 * @author 黄祎翔
 * @time 2021/1/29 15:11
 */
@SuppressWarnings("checkstyle:Indentation")
@Slf4j
@Component
public class PowerInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtils;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            // 获取用户session数据进行验证
            String accounnt = (String) request.getSession().getAttribute("NEWONE_USER_ACCOUNT");
            String key = (String) request.getSession().getAttribute("NEWONE_USER_KEY");
            // 管理员身份值
            String admin = "1";

            //获取redis信息进行验证
            String keyRedis = redisUtils.get("NEWONE_USER_LOGIN_" + accounnt);

            return StringUtils.isNoneBlank(key, keyRedis) && StringUtils.equals(key, keyRedis) && admin.equals(keyRedis.substring(0, 1));

        }
}
