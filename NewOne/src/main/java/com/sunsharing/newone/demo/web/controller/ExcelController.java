/*
 * @(#) ExcelController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-05 09:12:51
 */

package com.sunsharing.newone.demo.web.controller;

import com.sunsharing.newone.demo.entity.data.FlagEntity;
import com.sunsharing.newone.demo.service.ExcelService;
import com.sunsharing.share.boot.framework.annotation.ShareRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄祎翔
 * @time 2021/2/5 9:12
 */
@RestController
@RequestMapping
@Slf4j
@ShareRest
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping(value = "/newOne/excel/dataImport.do")
    @ResponseBody
    public FlagEntity dataImport(HttpSession session, @RequestParam("file") MultipartFile file) {
        return excelService.importData(session,file);
    }
}
