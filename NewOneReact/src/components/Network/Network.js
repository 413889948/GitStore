/*
 * @(#) Network.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 17:58:45
 */

import Network from '@share/network';
import { getContextPath } from '@/utils';

const contextPath = getContextPath();

// eslint-disable-next-line no-undef
const prefix = __API_PREFIX__;

Network.setContextPath(`${prefix}${contextPath}`);

Network.setExceptionHandle((error, abort) => {
    const { status } = error;

    switch (status){
    case '1401': {
        alert('授权已过期，禁止访问');
        abort();
        break;
    }
    case '1500': {
        alert('系统内部异常');
        abort();
        break;
    }
    default: {
        throw error;
    }
    }
});


export default Network;
