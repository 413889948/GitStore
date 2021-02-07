/*
 * @(#) Login.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2020-09-22 11:19:31
 */
import  '@share/login/lib/index.css';


import React, { useState ,useEffect} from 'react';
import { LoginPage as LoginComponent } from '@share/login';
import {Button, Modal, OverlayTrigger, Popover, Tooltip,Spin} from "@share/shareui";
import styles from "./Home.scss";
import {
    getComponents,
    FormState,
    createForm
} from '@share/shareui-form';
import Network from "@share/network";
import {RegisterRule} from "./Register";
import {getCookie,setCookie} from "./cookieUtil";
import Dialog from "@/components/Dialog/Dialog";
Network.setExceptionHandle((error,abort) => {
    Spin.hide();
    Dialog.alert(error.message.split(']')[0].slice(1));
    abort();//直接中断后续的Promise

});
/**
 * @return string 正则获取url中的参数
 */
function URL_Request(strName) {
    var strHref = document.location.toString();
    var intPos = strHref.indexOf("?");
    var strRight = strHref.substr(intPos + 1); //==========获取到右边的参数部分
    var arrTmp = strRight.split("&"); //=============以&分割成数组

    for (var i = 0; i < arrTmp.length; i++) //===========循环数组
    {
        var dIntPos = arrTmp[i].indexOf("=");
        var paraName = arrTmp[i].substr(0, dIntPos);
        var paraData = arrTmp[i].substr(dIntPos + 1);
        if (paraName.toUpperCase() === strName.toUpperCase()) {
            return paraData;
        }
    }
    return "";
}
export const transition = async () => {
    const value = await Network.formGet('/newOne/transition.do');
    if(value.flag){
        window.location.replace("/success")
    }

};
let outIn = URL_Request("outIn");
if (outIn){
    Network.formGet('/newOne/outLogin.do');
}
transition();

let districtArray=[];
// 获取区码数据用于select组件
const getDistrict = async () => {
    const districtValue = await Network.formGet('/newOne/district/list.do');
    for(const i in districtValue.list){
        if (districtArray.length <= i){
            districtArray.push({value:districtValue.list[i].value,label:districtValue.list[i].definition})
        }
    }
};

let sexArray=[];
const getSex = async () => {
    const sexValue = await Network.formGet('/newOne/data/getSex.do');

    for(const i in sexValue){
        if (sexArray.length<=i){
            sexArray.push({value:sexValue[i].value,label:sexValue[i].definition})
        }
    }
};
getSex();
getDistrict();

const {
    Input,
    Row,
    Form,
    Select
} = getComponents();

const LoginPage = props => {
    let number = getCookie("VerificationError");
    let errorNumber = 2;
    if (number === "true"){
        errorNumber = 0;
    }
    let dtpropx={

        loginPageProps: {
            titles: ['畅享管理系统平台', 'ShareUI v2.0.0'],
            // 登录成功后跳转路径
            indexUrl:"/success",
            guilds: [
                { text: '了解我们', href: 'http://www.baidu.com' },
                { text: '新手指南' },
                { text: '帮助手册' },
                { text: '更新日志' },
                { text: '快速入门' },
            ],
            copyright: ['Copyright © 2011-2017', '畅享信息技术有限公司'],

        },
        loginProps:{
            // 登录路径
            loginUrl:"/newOne/login.do",
            // 验证码图片地址
            validateUrl:"/newOne/verify.do",
            //显示记住用户名
            showRemember:false,
            //是否使用md5加密
            isMd5Encrypt:true,
            //是否开启输入错误2次自动开启验证码
            verificationCode:true,
            errorNumber:errorNumber,
        },
        changePwdProps: {
            // 修改密码校验等级
            pwdLevel: 3,
            //自定义正则表达式
            //regEx:""
        }

    };
    const [propx, setpropx] = useState(dtpropx);
    // useEffect(() => {
    //     getVerifySession().then(value => {
    //         dtpropx.loginProps.verificationCode=value.flag;
    //         setpropx(dtpropx);
    //     });
    //     //处理异步数据
    // }, [])
    const { form } = props;

    const [show, setShow] = useState(false);
    const [role, setRole] = useState("common");

    const popover = (
        <Popover id="modal-popover" title="popover">
            very popover. such engagement
        </Popover>
    );
    const tooltip = <Tooltip id="modal-tooltip">wow.</Tooltip>;
    // 注册方法
    async function register(data) {
        data.role=role;
        await RegisterRule(data).then(value =>
                                      {
                                          setShow(value)
                                      });

    }

    return<div>
        {/*注册弹窗组件*/}

        <Button  bsStyle="primary"  className={styles.regBut} onClick={() =>  setShow(true)} disabled={show}>注  册</Button>

        <Modal backdrop="static" show={show} onHide={() =>  setShow(false)}>
            <Modal.Header closeButton>
                <Modal.Title>注册</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form formState={form}>
                    <Row>

                        <Select
                            field="role"
                            label="注册角色"
                            onChange={role => {
                                setRole(role.target.value)
                            }}
                            value={role}
                            options={[
                                { value: 'common', label: '普通用户' },
                                { value: 'administrate', label: '管理员' }
                            ]}
                        />

                    </Row>
                    <Row>
                        <Input label="用户名" field="account"/>
                    </Row>
                    <Row>
                        <Input label="密码" type='password' field="password"/>
                    </Row>
                    <Row style={{display:  (role === "common") ? "" : "none"}}>
                        <Input  label="手机号" field="phone"/>
                    </Row>
                    <Row style={{display:  (role === "common") ? "" : "none"}}>
                        <Input label="邮箱" field="email"/>
                    </Row>
                    <Row style={{display:  (role === "common") ? "" : "none"}}>

                        <Select
                            field="sex"
                            label="性别"
                            options={sexArray}
                        />

                    </Row>

                    <Row style={{display:  (role === "common") ? "" : "none"}}>

                        <Select
                            field="district"
                            label="所属区"
                            options={districtArray}
                        />


                    </Row>
                    <Row style={{display:  (role === "common") ? "none" : ""}}>
                        <Input label="管理员注册秘钥" field="key"/>
                    </Row>

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={() =>{register(form.getFormData())}}   bsStyle="primary">注册</Button>
                <Button onClick={() =>  setShow(false)}>取消</Button>
            </Modal.Footer>
        </Modal>
        {/*注册弹窗组件*/}
        <LoginComponent {...propx} />
    </div>
};
export default createForm()(LoginPage);

