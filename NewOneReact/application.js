/*
 * @(#) application.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 17:29:54
 */

module.exports = {
    port: 4000,
    scurd: false,
    mscurd: false,
    development: {
        server: 'localhost',
        port: 8080,
        context: '',
        apiPrefix: '',
    },
    yapi: {
        server: '192.168.0.62',
        port: 3000,
        projectId: 323
    }
};
