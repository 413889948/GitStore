/*
 * @(#) index.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2020-09-22 11:19:31
 */

// shareui
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';// shareui-font版本迁移完成之后删除该依赖
import '@share/shareui-html';
import '@share/shareui-font';

import { createApplication } from '@share/framework';

createApplication({
    routes: [
        {
            path: '/addUser',
            title: '新增用户',
            component: () => import('@/pages/administrate/routes/AddUser')
        },

        {
            path: '/updateUser:id',
            title: '编辑用户',
            component: () => import('@/pages/administrate/routes/UpdateUser')
        },

        {
            path: '/',
            title: '用户列表',
            component: () => import('@/pages/administrate/routes/List')
        },
    ]
});

