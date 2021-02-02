package com.sunsharing.newone.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunsharing.newone.demo.entity.db.DistrictEntity;
import com.sunsharing.newone.demo.entity.query.district.DistrictSearch;
import com.sunsharing.newone.demo.entity.query.district.DistrictSearchResult;
import com.sunsharing.share.list.anno.ShareList;
import java.util.List;

/**
 * 区表 Mapper 接口
 *
 * @author admin
 * @since 2021-02-02 00:23:07
 */
public interface DistrictMapper extends BaseMapper<DistrictEntity> {

    @ShareList(schemeId = "newOne/district", transer = {
    })
    List<DistrictSearchResult> searchDistrict(DistrictSearch search);

}
