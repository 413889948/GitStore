package com.sunsharing.groupid.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.groupid.demo.entity.db.UserEntity;
import com.sunsharing.groupid.demo.entity.query.user.UserSearch;
import com.sunsharing.groupid.demo.entity.query.user.UserSearchResult;
import com.sunsharing.share.list.anno.ShareList;
import java.util.List;

/**
 * 用户信息表 Mapper 接口
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    @ShareList(schemeId = "user", transer = {
    })
    List<UserSearchResult> searchUser(UserSearch search);

}
