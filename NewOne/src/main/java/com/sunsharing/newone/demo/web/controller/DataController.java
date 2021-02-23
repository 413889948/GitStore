package com.sunsharing.newone.demo.web.controller;


import com.sunsharing.newone.demo.entity.query.data.DataSearch;
import com.sunsharing.newone.demo.service.DataService;
import com.sunsharing.share.boot.framework.base.BaseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 表码表
 *
 * @author admin
 * @since 2021-02-01 16:54:17
 */
@RestController
@RequestMapping
public class DataController extends BaseRestController {

    @Autowired
    private DataService dataService;

    /**
     * 性别列表获取
     * @return 性别列表
     */
    @GetMapping("/newOne/data/getSex.do")
    public List<DataSearch> getSex() {
        return dataService.getSex();
    }

    /**
     * 注销标识列表获取
     * @return 注销标识列表
     */
    @GetMapping("/newOne/data/getZxbs.do")
    public List<DataSearch> getZxbs() {
        return dataService.getZxbs();
    }
}

