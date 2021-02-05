# 项目脚手架 application

## 目录结构说明

```
+-- bin 脚本目录，请不要删除，不会出现在编译结果中
+-- public 前端非编译资源目录，该目录下文件不会经过编译，直接拷贝至编译目录
+-- src
    +-- assets 前端资源目录
    +-- components 公共组件
    +-- config 前端静态数据配置
    +-- model dva model目录
    +-- pages html页面目录
    +-- scurd 在线表单扩展目录
    +-- services 后端接口层
    +-- utils 工具
+-- application.js 项目配置
+-- webpack.config.js share-kit编译工具配置
```

## 控制台命令

yarn start 启动前端开发服务器

yarn build 编译

yarn append 添加html页面/路由页面/公共组件，请按提示进行操作

## 主要配置文件

* application.js 
    * port 前端工程开发端口
    * scurd 是否启用pc版在线表单
    * mscurd 是否启用mobile版在线表单
    * apiPrefix 网络请求前缀
    * development 服务端配置，用于代理ajax请求到指定服务端
        * server 服务端ip或域名
        * port 服务端端口
        * context 服务端文根
        * apiPrefix 开发环境下前端服务请求统一前缀，用于代理规则的命中，如果要对接yapi则配置为/mockApi
    
    * yapi yapi服务器配置，用于代理ajax请求到yapi模拟服务，
        * server yapi服务器，不需要修改
        * port yapi服务器端口，不需要修改
        * projectId yapi中的项目ID
        
* webpack.config.js
    * share-kit配置，相关配置项查看对应[文档](http://192.168.0.184:20000/share-tools/share-kit)
    * 如要修改share-kit配置，**请不要修改原有逻辑**

## 主要约定

### 运行时动态文根

不再对前端打包的文根进行配置，在运行时会自动获取

### 技术栈

* 使用css module，防止样式冲突
* 使用hash路由
* 请使用yarn进行包管理，只有yarn才支持覆盖嵌套依赖项版本

### 组件目录结构
* 组件放在components目录
* 组件由组件目录，入口文件，组件文件，组件样式组成，组件的子组件继续创建components目录嵌套
    
    示例，一个Head组件结构
    ```
       +-- components
           +-- Head 组件目录
               +-- index.js  入口文件
               +-- Head.js  组件文件
               +-- Head.scss 组件样式
               +-- components 子组件目录
                   +-- HeadLeft 
                       +-- index.js  入口文件
                       +-- HeadLeft.js  组件文件
                       +-- HeadLeft.scss 组件样式
    ```
  
### 页面目录结构
* 页面放在pages中
* 一个文件夹代表一个html页面，对应的访问地址为 http://{server}:{port}/{目录名}.html
* 一个页面由页面目录，入口文件，路由文件，模板页面，单页目录及n个单页组件组成（单页组件目录参考组件目录）
    
    示例, example页面结构
    ```
       +-- pages 
           +-- example 页面目录编译后为/example.html
               +-- index.js 入口文件
               +-- router.js 路由文件
               +-- routes 单页目录
                   +-- List 列表页单页组件目录
                   +-- Detail 详细页单页组件目录
    ```
