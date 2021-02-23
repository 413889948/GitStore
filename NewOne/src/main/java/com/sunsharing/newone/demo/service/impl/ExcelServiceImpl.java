/*
 * @(#) ExcelService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-05 09:13:45
 */

package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunsharing.newone.demo.constant.enums.MyResponseCode;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.entity.data.FlagEntity;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.service.ExcelService;
import com.sunsharing.newone.demo.service.UserService;
import com.sunsharing.newone.demo.util.PasswordUtil;
import com.sunsharing.share.common.base.IdGenerator;
import com.sunsharing.share.common.base.exception.ShareBusinessException;
import com.sunsharing.share.common.base.exception.ShareResponseCode;
import com.sunsharing.share.common.text.EncodeUtil;
import com.sunsharing.share.common.text.StringUtil;
import com.sunsharing.share.common.text.TextValidator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄祎翔
 * @time 2021/2/5 9:13
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    UserService userService;

    @Autowired(required = false)
    AdministrateMapper administrateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlagEntity importData(HttpSession session, MultipartFile file)  {

        if (file == null) {
            throw new ShareBusinessException(MyResponseCode.FILE_WRONG);
        }
        XSSFWorkbook workbook;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            log.error("导入文件格式出错",e);
            throw new ShareBusinessException(MyResponseCode.FILE_WRONG);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("清理文件流出错",e);
                throw new ShareBusinessException(MyResponseCode.FILE_WRONG);
            }
        }
        //读取第一个sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 获取用户session数据
        String accountAdmin = (String) session.getAttribute("NEWONE_USER_ACCOUNT");
        AdministrateEntity ae = administrateMapper.selectOne(new QueryWrapper<AdministrateEntity>().eq("ACCOUNT", accountAdmin));
        List<UserEntity> userEntities = new ArrayList<>();
        Set<String> acStrings = new HashSet<>();
        //从第1行读取到最后一行
        for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            // XSSFRow 代表一行数据
            XSSFRow row = sheet.getRow(rowIndex);
            //获取单元格,设置单元格类型
            XSSFCell account = row.getCell(0);
            account.setCellType(CellType.STRING);
            XSSFCell password = row.getCell(1);
            password.setCellType(CellType.STRING);
            XSSFCell phone = row.getCell(2);
            phone.setCellType(CellType.STRING);
            XSSFCell email = row.getCell(3);
            email.setCellType(CellType.STRING);
            XSSFCell sex = row.getCell(4);
            sex.setCellType(CellType.STRING);
            XSSFCell district = row.getCell(5);
            district.setCellType(CellType.STRING);
            //获取单元格数据
            String accountValue = account.getStringCellValue();
            String passwordValue = password.getStringCellValue();
            String phoneValue = phone.getStringCellValue();
            String emailValue = email.getStringCellValue();
            String sexValue = sex.getStringCellValue();
            String districtValue = district.getStringCellValue();
            if (StringUtil.isAllBlank(accountValue,passwordValue,phoneValue,emailValue,sexValue,districtValue)) {
                continue;
            }
            //非空校验
            if (!StringUtils.isNoneBlank(accountValue,passwordValue,phoneValue,emailValue,sexValue,districtValue)) {
                throw new ShareBusinessException(ShareResponseCode.VALID_FIELD_NOT_EMPTY);
            }
            // 检测用户属性是否符合规范
            if (!TextValidator.isMobileExact(phoneValue)
                || !TextValidator.isEmail(emailValue)
                || !Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{8,20}$", passwordValue)) {
                throw new ShareBusinessException(ShareResponseCode.VALID_FIELD_ILLEGAL);
            }
            UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>()
                .eq("ACCOUNT",accountValue));
            if (userEntity != null) {
                throw new ShareBusinessException(MyResponseCode.NAME_REPETITION);
            }
            String uuid = IdGenerator.uuid2();
            UserEntity entity =
                new UserEntity(uuid,
                accountValue,
                PasswordUtil.saltEncryptionUtil(EncodeUtil.md5(passwordValue)),
                phoneValue,
                emailValue,
                ae.getUuid(),
                ae.getUuid(),
                sexValue,
                districtValue);
            if (acStrings.contains(entity.getAccount())) {
                throw new ShareBusinessException(MyResponseCode.REPEAT_WRONG);
            }
            acStrings.add(entity.getAccount());
            userEntities.add(entity);
        }
        // 将打开的 XSSFWorkbook 关闭
        try {
            workbook.close();
        } catch (IOException e) {
            log.error("清理XSSFWorkbook出错",e);
            throw new ShareBusinessException(MyResponseCode.FILE_WRONG);
        }
        return new FlagEntity(userService.addUserList(userEntities));
    }
}
