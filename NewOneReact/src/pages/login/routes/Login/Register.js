import React, { useState ,useEffect} from 'react';
import {Button, Modal, OverlayTrigger, Popover, Tooltip,Spin} from "@share/shareui";
import styles from "./Home.scss";
import Network from "@share/network";
import {TextValidatorUtil} from "@share/common";
import Dialog from "@/components/Dialog/Dialog";
import { StringUtil } from '@share/common';

Network.setExceptionHandle((error,abort) => {
    Spin.hide();
    Dialog.alert(error.message.split(']')[0].slice(1));
    abort();//直接中断后续的Promise

});
export const RegisterRule = async (data) => {
    Spin.show('注册中，请稍等');
    const emailFlag = TextValidatorUtil.isEmail(data.email);
    const phoneFlag = TextValidatorUtil.isMobileExact(data.phone);
    const passwordFlag = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{8,20}$/.test(data.password);

    if (data.role === "common") {

        //普通用户注册
        if (data.account === "" || data.sex === "" || data.district === "" || data.account === undefined || data.sex === undefined
            || data.district === undefined) {
            Spin.hide();
            await Dialog.alert('有信息为空');
            return true;
        } else if (!passwordFlag) {
            Spin.hide();
            await Dialog.alert('密码校验出错，密码要求均在8位以上，且密码必须同时包含小写字母(a-z)、大写字母(A-Z)、数字(0-9)');
            return true;
        } else if (!phoneFlag) {
            Spin.hide();
            await Dialog.alert('手机号格式有误');
            return true;
        } else if (!emailFlag) {
            Spin.hide();
            await Dialog.alert('邮箱格式有误');
            return true;
        }
        data.account=data.account.trim()
        data.sex=data.sex.trim()
        data.district=data.district.trim()
        if (data.account === "" || data.sex === "" || data.district === "" || data.account === undefined || data.sex === undefined
            || data.district === undefined) {
            Spin.hide();
            await Dialog.alert('有信息为空');
            return true;
        }
        //对输入的密码进行md5加密操作
        data.password=StringUtil.md5(data.password);
        await Network.json('/newOne/register.do',data);
        Spin.hide();
        await Dialog.alert('注册成功');
        location.reload();
    } else {
        //管理员注册
        if (data.account === "" || data.account === undefined || data.key === "" || data.key === undefined) {
                Spin.hide();
                await Dialog.alert('有信息为空');
                return true;
            } else if (!passwordFlag) {
                Spin.hide();
                await Dialog.alert('密码校验出错，密码要求均在8位以上，且密码必须同时包含小写字母(a-z)、大写字母(A-Z)、数字(0-9)');
                return true;
            }
        data.account=data.account.trim()
        data.key=data.key.trim()
        if (data.account === "" || data.account === undefined || data.key === "" || data.key === undefined) {
            Spin.hide();
            await Dialog.alert('有信息为空');
            return true;
        }
        //对输入的密码进行md5加密操作
        data.password=StringUtil.md5(data.password);
        await Network.json('/newOne/register.do',data);
        Spin.hide();
        await Dialog.alert('注册成功');
        location.reload();

        }
    Spin.hide();
}

