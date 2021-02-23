/*
 * @(#) ExcelService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-05 09:13:45
 */

package com.sunsharing.newone.demo.service;

import com.sunsharing.newone.demo.entity.data.FlagEntity;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author 黄祎翔
 * @time 2021/2/5 9:13
 */
public interface ExcelService {

    /**
    * 通过exl导入数据
    * @param session 注册信息的管理员信息
    * @param file exl文件流
    * @return 是否成功
    */
    FlagEntity importData(HttpSession session, MultipartFile file);
}
