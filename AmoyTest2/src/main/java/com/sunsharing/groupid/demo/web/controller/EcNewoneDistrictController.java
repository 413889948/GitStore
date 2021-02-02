package com.sunsharing.groupid.demo.web.controller;

import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictCreate;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictDetail;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictModify;
import com.sunsharing.groupid.demo.service.EcNewoneDistrictService;
import com.sunsharing.share.boot.framework.auth.User;
import com.sunsharing.share.boot.framework.base.BaseRestController;
import com.sunsharing.share.boot.framework.entity.Id;
import com.sunsharing.share.boot.framework.entity.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 区表
 *
 * @author admin
 * @since 2021-01-26 00:11:18
 */
@RestController
@RequestMapping
public class EcNewoneDistrictController extends BaseRestController {

    @Autowired
    private EcNewoneDistrictService ecNewoneDistrictService;

    /**
     * 区表-新增
     * @param obj 新增对象
     */
    @PostMapping("/ecNewoneDistrict/addEcNewoneDistrict.do")
    public Id<Integer> addEcNewoneDistrict(User user, @RequestBody EcNewoneDistrictCreate obj) {
        return Id.build(ecNewoneDistrictService.addEcNewoneDistrict(obj, user));
    }
    /**
    * 区表-详情获取
    * @param id 区表id
    */
    @GetMapping("/ecNewoneDistrict/getEcNewoneDistrict.do")
    public EcNewoneDistrictDetail getEcNewoneDistrict(Integer id) {
        return ecNewoneDistrictService.getEcNewoneDistrict(id);
    }
    /**
     * 区表-修改
     * @param obj 修改对象
     */
    @PostMapping("/ecNewoneDistrict/updateEcNewoneDistrict.do")
    public Id<Integer> updateEcNewoneDistrict(User user, @RequestBody EcNewoneDistrictModify obj) {
        return Id.build(ecNewoneDistrictService.updateEcNewoneDistrict(obj, user));
    }
    /**
     * 区表-删除
     * @param ids id集合
     */
    @PostMapping("/ecNewoneDistrict/deleteEcNewoneDistrict.do")
    public Result<Boolean> deleteEcNewoneDistrict(@RequestBody List<Integer> ids) {
        return Result.of(ecNewoneDistrictService.deleteEcNewoneDistrict(ids));
    }
}

