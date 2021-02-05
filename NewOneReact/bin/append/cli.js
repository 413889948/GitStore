/*
 * @(#) cli.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 15:49:41
 */
const path = require('path');
const glob = require('glob');
const inquirer = require('inquirer');
const { StringUtil } = require('@share/common');
const { appendComponent, appendRoute, appendPage } = require('./script');

function getPages(){
    return glob.sync('./src/pages/*').map(pagePath => path.basename(pagePath));
}

const questions = [
    {
        type: 'list',
        name: 'appendType',
        message: '请选择要增加的类型',
        choices: [
            {
                name: '公共组件',
                value: 'component'
            }, {
                name: '页面模块(html页面)',
                value: 'page'
            }, {
                name: '路由(html增加路由页面)',
                value: 'route'
            }
        ]
    },
    {
        type: 'list',
        name: 'routerPage',
        message: '请选择要增加路由的html页面模块',
        choices: getPages(),
        when(answers){
            return answers.appendType === 'route';
        }
    },
    {
        type: 'confirm',
        name: 'example',
        message: '是否添加示例页面',
        when(answers){
            return answers.appendType === 'page';
        }
    },
    {
        type: 'input',
        name: 'name',
        message(answers){
            const labelMap = {
                component: '组件名称(将转换为首字大写的驼峰名称)',
                page: 'html页面名称(将转换为纯小写)',
                route: '路由页面名称(组件为首字大写的驼峰名称，并默认添加纯小写路由path)'
            };
            const label = labelMap[answers.appendType];

            return `请输入${label}`;
        }
    }
];

inquirer.prompt(questions)
    .then(answers => {
        const { appendType, routerPage, name, example } = answers;

        const names = {
            PAGE_NAME: name.toLowerCase(),
            COMPONENT_NAME: StringUtil.capitalize(StringUtil.camelCase(name)),
            CAMEL_CASE_COMPONENT_NAME: StringUtil.camelCase(name)
        };

        if (appendType === 'component'){
            appendComponent(names);
        }
        if (appendType === 'page'){
            appendPage(names, example);
        }

        if (appendType === 'route'){
            appendRoute(names, routerPage);
        }
    });
