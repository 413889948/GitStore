/*
 * @(#) AdministrateService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-03 18:51:56
 */

package com.sunsharing.newone.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunsharing.newone.demo.dao.mapper.AdministrateMapper;
import com.sunsharing.newone.demo.dao.mapper.DataMapper;
import com.sunsharing.newone.demo.entity.db.AdministrateEntity;
import com.sunsharing.newone.demo.entity.db.DataEntity;
import com.sunsharing.newone.demo.service.AdministrateService;
import com.sunsharing.newone.demo.service.DataService;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

/**
 * @author 黄祎翔
 * @time 2021/2/3 18:51
 */
@Service
@Log4j2
public class AdministrateServiceImpl extends ServiceImpl<AdministrateMapper, AdministrateEntity> implements AdministrateService {
}
