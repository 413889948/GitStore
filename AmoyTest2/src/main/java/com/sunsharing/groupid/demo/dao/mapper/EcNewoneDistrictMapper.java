package com.sunsharing.groupid.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.groupid.demo.entity.db.EcNewoneDistrictEntity;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictSearch;
import com.sunsharing.groupid.demo.entity.query.ecNewoneDistrict.EcNewoneDistrictSearchResult;
import com.sunsharing.share.list.anno.ShareList;
import java.util.List;

/**
 * 区表 Mapper 接口
 *
 * @author admin
 * @since 2021-01-26 00:11:18
 */
public interface EcNewoneDistrictMapper extends BaseMapper<EcNewoneDistrictEntity> {

    @ShareList(schemeId = "ecNewoneDistrict", transer = {
    })
    List<EcNewoneDistrictSearchResult> searchEcNewoneDistrict(EcNewoneDistrictSearch search);

}
