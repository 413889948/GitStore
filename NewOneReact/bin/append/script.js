/*
 * @(#) append.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-05 17:43:58
 */

const path = require('path');
const fs = require('fs');

const vfs = require('vinyl-fs');
const map = require('map-stream');

const DEV = process.argv[2] === '--test';
const ROOT = './bin/append';

function replaceFileName(name, config){
    let result = name;

    Object.keys(config)
        .forEach(key => {
            result = result.replace(key, config[key]);
        });
    return result.replace('.tpl', '');
}

function replaceContent(content, config){
    let result = content;

    Object.keys(config)
        .forEach(key => {
            const regex = new RegExp(`<${key}>`, 'g');

            result = result.replace(regex, config[key]);
        });
    return result.replace('.tpl', '');
}

function replaceFileNameAndContent(names, file){
    if (file.isDirectory()){
        return file;
    }
    file.basename = replaceFileName(file.basename, names);
    file.contents = Buffer.from(replaceContent(file.contents.toString(), names));
    return file;
}



function appendComponent(names){
    const templateDir = path.resolve(`${ROOT}/component`);
    const targetDir = path.resolve(`./src/components/${names.COMPONENT_NAME}`);

    fs.mkdirSync(targetDir);

    vfs.src([`${templateDir}/*.tpl`])
        .pipe(map((file, cb) => {
            const nextFile = replaceFileNameAndContent(names, file);

            if (DEV){
                console.debug(nextFile.path);
            }
            cb(null, nextFile);
        }))
        .pipe(vfs.dest(targetDir));

    console.info(`已添加组件/src/components/${names.COMPONENT_NAME}`);
}


function appendPage(names, example){
    const templateDir = path.resolve(`${ROOT}/page`);
    const targetDir = path.resolve(`./src/pages/${names.PAGE_NAME}`);

    fs.mkdirSync(targetDir);
    const fileGlob = [`${templateDir}/**/*`];

    if (!example){
        fileGlob.push(`!${templateDir}/routes/**/*.*`);
    }
    vfs.src(fileGlob)
        .pipe(map((file, cb) => {
            if (!example) {
                if (file.basename === 'Detail' || file.basename === 'List') {
                    cb();
                    return;
                }
                if (file.basename === 'router.js'){
                    let contentString = file.contents.toString();

                    contentString = contentString.replace(/(const.*lazy.*;)/g, '// $1');
                    contentString = contentString.replace(/(<Route.*\/>)/g, '{/** $1 **/}');

                    file.contents = Buffer.from(contentString);
                    cb(null, file);
                    return;
                }
            }
            if (DEV){
                console.debug(file.path);
            }
            cb(null, file);
        }))
        .pipe(vfs.dest(targetDir));

    console.info(`已添加页面目录/src/pages/${names.PAGE_NAME}`);
    if (example){
        console.info(`已在页面文件${names.PAGE_NAME}添加示例路由页面/list,/detail`);
    }
}

function appendRoute(names, route){
    const templateDir = path.resolve(`${ROOT}/route`);
    const targetDir = path.resolve(`./src/pages/${route}/routes/${names.COMPONENT_NAME}`);
    const routerPath = path.resolve(`./src/pages/${route}/router.js`);

    fs.mkdirSync(targetDir);

    vfs.src([`${templateDir}/**/*`])
        .pipe(map((file, cb) => {
            const nextFile = replaceFileNameAndContent(names, file);

            if (DEV){
                console.debug(nextFile.path);
            }
            cb(null, nextFile);
        }))
        .pipe(vfs.dest(targetDir));

    let content = fs.readFileSync(routerPath).toString();

    content = content.replace(/RouterConfig.*/, `RouterConfig({ history }) {

    const ${names.COMPONENT_NAME} = lazy(() => import('./routes/${names.COMPONENT_NAME}'));`);
    content = content.replace(/<Switch>/, `<Switch>
                        <Route path="/${names.PAGE_NAME}" exact component={${names.COMPONENT_NAME}} />`);

    fs.writeFileSync(routerPath, content);

    console.info(`已在页面文件${names.route}添加路由页面/${name.PAGE_NAME}`);
}


module.exports = {
    appendComponent,
    appendPage,
    appendRoute
};
