package com.sunsharing.newone.demo.web.controller;

import com.sunsharing.newone.demo.entity.query.user.UserCreate;
import com.sunsharing.newone.demo.entity.query.user.UserDetail;
import com.sunsharing.newone.demo.entity.query.user.UserModify;
import com.sunsharing.newone.demo.service.UserService;
import com.sunsharing.share.boot.framework.base.BaseRestController;
import com.sunsharing.share.boot.framework.entity.Id;
import com.sunsharing.share.boot.framework.entity.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户信息表
 *
 * @author admin
 * @since 2021-01-29 17:14:48
 */
@RestController
@RequestMapping
@Slf4j
public class UserController extends BaseRestController {

    @Autowired
    private UserService userService;

    /**
     * 用户信息表-新增
     * @param obj 新增对象
     */
    @PostMapping("/newOne/user/addUser.do")
    public Id<String> addUser(@RequestBody @Valid UserCreate obj, HttpSession session) {
        return Id.build(userService.addUser(obj,session));
    }

    /**
    * 用户信息表-详情获取
    * @param id 用户信息表id
    */
    @GetMapping("/newOne/user/getUser.do")
    public UserDetail getUser(String id) {
        return userService.getUser(id);
    }

    /**
     * 用户信息表-修改
     * @param obj 修改对象
     */
    @PostMapping("/newOne/user/updateUser.do")
    public Id<String> updateUser(@RequestBody @Valid UserModify obj, HttpSession session) {
        return Id.build(userService.updateUser(obj, session));
    }

    /**
     * 用户信息表-删除
     * @param ids id集合
     */
    @PostMapping("/newOne/user/deleteUser.do")
    public Result<Boolean> deleteUser(@RequestBody List<String> ids) {
        return Result.of(userService.deleteUser(ids));
    }



}

