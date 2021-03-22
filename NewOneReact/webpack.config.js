/*
 * @(#) webpack.config.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 16:27:52
 */
// const path = require('path');
const {
    getScurdEntrys,
    getScurdHtmls,
    getScurdRules,
    importer,
    jqueryGlobal
} = require('./bin/webpack.config.block');

const {
    port,
    scurd,
    mscurd,
    yapi,
    development,
} = require('./application');

const { apiPrefix } = development;

// 额外配置，会和默认配置合并
// env下的配置会和其他配置合并，对应不同环境下的最终配置
// 其他配置项请查看文档 http://192.168.0.184:20000/share-tools/share-kit

module.exports = {
    port,
    env: {
        development: {
            extraBabelOptions: {
            }
        },
        production: {
            extraBabelOptions: {}
        }
    },
    extraBabelOptions: {
        plugins: [...importer]
    },
    extraProvidePlugin: {
        ...jqueryGlobal
    },
    alias: {
        '@': './src'
    },
    proxy: {
        '/newOne/**':{
            target: `http://${development.server}:${development.port}`,
        },
        '/tEcNewoneDistrict/**':{
            target: `http://${development.server}:${development.port}`,
        },
        '/mockApi': {
            target: `http://${yapi.server}:${yapi.port}/mock/${yapi.projectId}`,
            pathRewrite: { '^/mockApi': '' }
        },
        [apiPrefix]: {
            target: `http://${development.server}:${development.port}`,
            pathRewrite: { [`^${apiPrefix}`]: development.context },
            onProxyRes(proxyRes, req, res) {
                if (development.context === '') return;
                const cookies = proxyRes.headers['set-cookie'];
                const cookieRegex = new RegExp(`Path=\\${development.context}\\/`, 'i');

                // 修改cookie Path
                if (cookies) {
                    const newCookie = cookies.map(function(cookie) {
                        if (cookieRegex.test(cookie)) {
                            return cookie.replace(cookieRegex, 'Path=/');
                        }
                        return cookie;
                    });

                    // 修改cookie path
                    delete proxyRes.headers['set-cookie'];
                    proxyRes.headers['set-cookie'] = newCookie;
                }
            }
        },
        '**/remote.action': {
            target: `http://${development.server}:${development.port}`,
            secure: false,
            changeOrigin: true
        }
    },
    extraEntrys: {
        ...getScurdEntrys(scurd, mscurd)
        // 其他入口
    },
    extraHtmls: [
        ...getScurdHtmls(scurd, mscurd)
        // 其他html页面
    ],
    extraRules: [
        ...getScurdRules(scurd, mscurd)
        // 其他规则
    ],
    define: {
        __API_PREFIX__: process.env.NODE_ENV === 'development' ? apiPrefix : ''
    },
    isDve: scurd || mscurd,
    devtool: 'cheap-module-source-map'
};
