/*
 * @(#) index.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author finalcat
 * <br> 2021-01-07 17:34:17
 */

import { createApplication } from '@share/framework';
import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.min.css";
import "@share/shareui-html";
import "@share/shareui-font";

createApplication({
    routes: [
        {
            path: '/',
            title: '登录',
            component: () => import('@/pages/login/routes/Login')
        }

    ]
});
