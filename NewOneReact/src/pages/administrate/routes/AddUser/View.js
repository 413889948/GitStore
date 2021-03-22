import React from 'react';
import {Panel, ButtonToolBar, Button, Spin} from '@share/shareui';
import { useForm } from '@share/hooks';
import { useSave, useToRoute, transition } from './hooks';
import { getComponents } from '@share/shareui-form';
import Network from "@share/network";
import Dialog from "@/components/Dialog/Dialog";
Network.setExceptionHandle(  (error,abort) => {
    if ("[报文解析为json异常][status=-1][SyntaxError: Unexpected token '<']"!==error.message) {
        Dialog.alert(error.message);
    }
    abort();//直接中断后续的Promise

});
//校验权限
transition();
const {
    Input,
    Form,
    Row,
    Select
} = getComponents('table');
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
const View = () => {
    const formState = useForm({});
    const {
        save,
        loading
    } = useSave({
        formState
    });
    const {
        toBack
    } = useToRoute();

    return (<div>
        <Panel>
            <Panel.Head>新增</Panel.Head>
            <Form formState={formState}>



                <Row>

                    <Input field="account" label="用户账号" required />

                </Row>



                <Row>

                    <Input field="password" label="用户密码" required />

                </Row>



                <Row>

                    <Input field="phone" label="用户手机号" required />

                </Row>



                <Row>

                    <Input field="email" label="用户邮箱" required />

                </Row>



                <Row>

                    <Select
                        field="sex"
                        label="用户性别"
                        required
                        options={sexArray}
                    />

                </Row>

                <Row>

                    <Select
                        field="district"
                        label="用户所属区"
                        required
                        options={districtArray}
                    />


                </Row>



            </Form>
        </Panel>

        <ButtonToolBar>
            <Button bsStyle="primary" loading={loading} onClick={save}>保存</Button>
            <Button onClick={toBack}>取消</Button>
        </ButtonToolBar>
    </div>);
};

export default View;
