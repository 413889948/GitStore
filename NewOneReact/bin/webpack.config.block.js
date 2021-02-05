/*
 * @(#) webpack.config.block.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-06 17:08:12
 */

function getScurdEntrys(SCURD, SCURD_MOBILE){
    if (!SCURD && !SCURD_MOBILE) return {};

    const result = {
        query: './node_modules/@share/scurd/main.js',
        adminIndex: './node_modules/@share/scurd/admin/index.js',
        mQuery: './node_modules/@share/mscurd/mMain.js',
    };

    if (!SCURD_MOBILE){
        delete result.mQuery;
    }
    return result;
}

function getScurdHtmls(SCURD, SCURD_MOBILE){
    if (!SCURD && !SCURD_MOBILE) return [];
    const result = [
        {
            filename: 'query.html',
            title: '在线表单',
            inject: true,
            template: './node_modules/@share/scurd/index.html',
            chunks: ['query']
        },
        {
            filename: 'admin/index.html',
            title: '在线表单',
            inject: true,
            template: './node_modules/@share/scurd/admin/index.html',
            chunks: ['adminIndex']
        },
        {
            filename: 'mQuery.html',
            title: '手机首页',
            inject: true,
            template: './node_modules/@share/mscurd/index.html',
            chunks: ['mQuery']
        }
    ];

    if (!SCURD_MOBILE){
        result.length = 2;
    }
    return result;
}

function getScurdRules(SCURD, SCURD_MOBILE){
    if (!SCURD && !SCURD_MOBILE) return [];
    return [
        {
            test: /eos3(\.min)?\.js$/,
            use: [
                {
                    loader: 'imports-loader?defined=>false,this=>window'
                },
                {
                    loader: 'exports-loader?eos'
                }
            ],
            exclude: /node_modules/
        },
        {
            test: /Service\.js$/,
            use: 'imports-loader?define=>false,this=>window',
            exclude: /node_modules/
        },
        {
            test: /\.js$/,
            use: 'imports-loader?define=>false,this=>window,template=art-template',
            include: /ulynlist-ext/
        },
        {
            test: /(ulynlist\.js$)|(ulynlist.table\.js$)|(ulynlist.pagebar\.js$)/,
            use: 'imports-loader?define=>false,this=>window,template=art-template'
        },
        {
            test: /template\.js$/,
            use: [
                {
                    loader: 'imports-loader?this=>window,define=>false'
                },
                {
                    loader: 'exports-loader?template=window.template'
                }
            ]
        },
        {
            test: /ext\.js$/,
            use: 'imports-loader?define=>false,this=>window,template=art-template',
            include: /shareTab/
        },
        {
            test: /zeus\.auth/,
            use: [
                {
                    loader: 'imports-loader?define=>false,this=>window'
                }
            ]
        }
    ];
}

const importer = [
    ['import', { libraryName: 'antd', libraryDirectory: 'es', style: 'css' }, 'antd'],
    [
        'import', {
            libraryName: '@mshare/mshareui',
            libraryDirectory: 'es',
            style: stylePath => `${stylePath}/style/index.css`
        }, 'mshareui'
    ],
    ['import', { libraryName: 'ahooks', libraryDirectory: 'es', camel2DashComponentName: false }, 'ahooks']
];


const jqueryGlobal = {
    $: 'jquery',
    jquery: 'jquery',
    jQuery: 'jquery'
};

module.exports = {
    getScurdEntrys,
    getScurdHtmls,
    getScurdRules,
    importer,
    jqueryGlobal
};
