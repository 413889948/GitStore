/*
 * @(#) service.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-04 11:09:05
 */

import Network from '@/components/Network';

/**
 * 获取所有表码
 */
export function getAllCode(){
    return Network.formGet('/code/all.do');
}

/**
 * 获取单个表码
 * @param alias
 */
export function getCode(alias){
    return Network.formGet('/code/get.do', { alias });
}

/**
 * 获取多个表码
 * @param aliasArray
 */
export function getCodes(...aliasArray){
    return Network.formGet('/code/load.do', { alias: aliasArray.join(',') });
}

/**
 * 获取当前用户
 */
export function getMe() {
    return Network.formGet('/me.do');
}

/**
 * 列表查询
 * @param schemaId 后端方案标识
 * @param request 请求入参 { page:{ currentPage, linesPerPage, orderBys:[{sortField, sortType}] }, data:{} }
 * @return Promise<{ page: { currentPage, linesPerPage, totalNum, totalPage }, list}>
 */
export function getList(schemaId, request){
    return Network.json(`/${schemaId}/list`, request);
}
