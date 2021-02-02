package com.sunsharing.groupid.demo.web.controller;

import com.sunsharing.groupid.demo.entity.query.district.DistrictCreate;
import com.sunsharing.groupid.demo.entity.query.district.DistrictDetail;
import com.sunsharing.groupid.demo.entity.query.district.DistrictModify;
import com.sunsharing.groupid.demo.service.DistrictService;
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
 * @since 2021-01-22 22:11:15
 */
@RestController
@RequestMapping
public class DistrictController extends BaseRestController {

    @Autowired
    private DistrictService districtService;

    /**
     * 区表-新增
     * @param obj 新增对象
     */
    @PostMapping("/district/addDistrict.do")
    public Id<Integer> addDistrict(User user, @RequestBody DistrictCreate obj) {
        return Id.build(districtService.addDistrict(obj, user));
    }
    /**
    * 区表-详情获取
    * @param id 区表id
    */
    @GetMapping("/district/getDistrict.do")
    public DistrictDetail getDistrict(Integer id) {
        return districtService.getDistrict(id);
    }
    /**
     * 区表-修改
     * @param obj 修改对象
     */
    @PostMapping("/district/updateDistrict.do")
    public Id<Integer> updateDistrict(User user, @RequestBody DistrictModify obj) {
        return Id.build(districtService.updateDistrict(obj, user));
    }
    /**
     * 区表-删除
     * @param ids id集合
     */
    @PostMapping("/district/deleteDistrict.do")
    public Result<Boolean> deleteDistrict(@RequestBody List<Integer> ids) {
        return Result.of(districtService.deleteDistrict(ids));
    }
}

