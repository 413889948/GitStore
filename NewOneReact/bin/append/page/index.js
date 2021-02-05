/*
 * @(#) index.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-04 14:40:43
 */

// shareui
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';// shareui-font版本迁移完成之后删除该依赖
import '@share/shareui-html';
import '@share/shareui-font';

import '@/form';

import dva from 'dva';
import router from './router';

// 1. Initialize
const app = dva();

// 2. Plugins
// app.use({});

// 3. Model
// app.model();

// 4. Router
app.router(router);

// 5. Start
app.start('#root');


