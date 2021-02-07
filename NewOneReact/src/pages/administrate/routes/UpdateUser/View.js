import React, {useEffect, useState} from 'react';
import {Panel, ButtonToolBar, Button, Spin} from '@share/shareui';
import { useForm } from '@share/hooks';
import { useUpdate, useInitData, useToRoute} from './hooks';
import { getComponents } from '@share/shareui-form';
import Network from "@share/network";
import {transition} from "@/pages/administrate/routes/AddUser/hooks";
import Dialog from "@/components/Dialog/Dialog";
// import {getCookie,setCookie} from "./cookieUtil";

// const num = getCookie("UpdateTab");
// if (num === '0' || num === ''){
//     setCookie("UpdateTab",'1');
//     location.reload();
// }else {
//     setCookie("UpdateTab",'0');
// }
Network.setExceptionHandle((error,abort) => {
    abort();//直接中断后续的Promise
});
//校验权限
transition();
const {
    Input,
    Select,
    Form,
    Row
} = getComponents('table');
// let districtArray=[];
// // 获取区码数据用于select组件
// const getDistrict = async () => {
//     const districtValue = await Network.formGet('/newOne/district/list.do');
//     for(const i in districtValue.list){
//         if (districtArray.length <= i){
//             districtArray.push({value:districtValue.list[i].value,label:districtValue.list[i].definition})
//         }
//     }
// };
// let sexArray=[];
// const getSex = async () => {
//     const sexValue = await Network.formGet('/newOne/data/getSex.do');
//
//     for(const i in sexValue){
//         if (sexArray.length<=i){
//             sexArray.push({value:sexValue[i].value,label:sexValue[i].definition})
//         }
//     }
// };
//     getSex();
//     getDistrict();
const View = () => {
    const [sexArray, setSexArray] = useState([]);
    const [districtArray, setDistrictArray] = useState([]);
    let sexArrayT = []
    let districtArrayT = []
    useEffect(() => {
        const sexValue = Network.formGet('/newOne/data/getSex.do');
        const districtValue = Network.formGet('/newOne/district/list.do');
        sexValue.then(value => {
            for (const i in value) {
                if (sexArray.length <= i) {
                    sexArrayT.push({value: value[i].value, label: value[i].definition})
                }
            }
            setSexArray(sexArrayT)
        })

        districtValue.then(districtValue => {
            for (const i in districtValue.list) {
                if (districtArray.length <= i) {
                    districtArrayT.push({value: districtValue.list[i].value, label: districtValue.list[i].definition})
                }
            }
            setDistrictArray(districtArrayT)

        })
        //处理异步数据
    }, [])

    const formState = useForm({});
    useInitData({
                    formState
                });

    const {
        update,
        loading
    } = useUpdate({
                      formState
                  });
    const {
        toBack
    } = useToRoute();

    return (<div>
        <Panel>
            <Panel.Head>编辑</Panel.Head>
            <Form formState={formState}>


                <Row>

                    <Input field="account" label="用户账号" required/>

                </Row>


                {/*<Row>*/}

                {/*    <Input field="password" label="用户密码" required />*/}

                {/*</Row>*/}


                <Row>

                    <Input field="phone" label="用户手机号" required/>

                </Row>


                <Row>

                    <Input field="email" label="用户邮箱" required/>

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
            <Button bsStyle="primary" loading={loading} onClick={update}>保存</Button>
            <Button onClick={toBack}>取消</Button>
        </ButtonToolBar>
    </div>);

};

export default View;
