package com.sunsharing.newone.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.newone.demo.entity.db.UserEntity;
import com.sunsharing.newone.demo.entity.query.user.UserSearch;
import com.sunsharing.newone.demo.entity.query.user.UserSearchResult;
import com.sunsharing.share.list.anno.ShareList;
import com.sunsharing.share.list.anno.Transer;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息表 Mapper 接口
 *
 * @author admin
 * @since 2021-01-29 17:14:48
 */
@Mapper

public interface UserMapper extends BaseMapper<UserEntity> {

    @ShareList(schemeId = "newOne/user", transer = {
        @Transer(name = "code", params = "EN_SEX", fromField = "sex", toField = "_cn_sex"),
        @Transer(name = "code", params = "EN_DISTRICT", fromField = "district", toField = "_cn_district"),
        @Transer(name = "code", params = "EN_ZXBS", fromField = "zxbs", toField = "_cn_zxbs")
    })
    List<UserSearchResult> searchUser(UserSearch search);

}
