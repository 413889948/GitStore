package com.sunsharing.groupid.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.groupid.demo.entity.db.DistrictEntity;
import com.sunsharing.groupid.demo.entity.query.district.DistrictSearch;
import com.sunsharing.groupid.demo.entity.query.district.DistrictSearchResult;
import com.sunsharing.share.list.anno.ShareList;
import java.util.List;

/**
 * 区表 Mapper 接口
 *
 * @author admin
 * @since 2021-01-22 22:11:15
 */
public interface DistrictMapper extends BaseMapper<DistrictEntity> {

    @ShareList(schemeId = "district", transer = {
    })
    List<DistrictSearchResult> searchDistrict(DistrictSearch search);

}
