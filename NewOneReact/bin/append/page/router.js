/*
 * @(#) router.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 14:41:23
 */

import React, { lazy, Suspense } from 'react';
import { Router, Switch, Route } from 'dva/router';

function RouterConfig({ history }) {

    const List = lazy(() => import('./routes/List'));
    const Detail  = lazy(() => import('./routes/Detail'));

    return (
        <Router history={history}>
            <div className="ui-box">
                <Suspense fallback={<div>加载中...</div>}>
                    <Switch>
                        <Route path="/list" exact component={List} />
                        <Route path="/detail" exact component={Detail} />
                    </Switch>
                </Suspense>
            </div>
        </Router>
    );
}

export default RouterConfig;
