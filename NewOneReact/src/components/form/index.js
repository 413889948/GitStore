/*
 * @(#) index.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-05 17:35:16
 */

import { registerComponent, registerRule } from '@share/shareui-form';

const projectComponents = {
    // 项目自定义表单项组件
};

const projectRules = {
    // 项目自定义表单校验规则
};

function register(target, method){
    Object.entries(target)
        .forEach(([name, value]) => {
            method(name, value);
        });
}

register(projectComponents, registerComponent);
register(projectRules, registerRule);
