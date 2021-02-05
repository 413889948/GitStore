import React, {Fragment, useState,useEffect} from 'react';
import {Panel, FileUpload, Button, Spin} from '@share/shareui';
import { useListPage } from '@share/hooks';
import { getComponents } from '@share/shareui-form';
import { ShareList, Column, ActionColumn, NumberColumn,CheckColumn } from '@share/list';
import { useRemove, useToRoute } from './hooks';
import { useService } from '@share/framework';
import PublicService from '@/services/PublicService';
import Network from "@share/network";
import {transition} from "@/pages/administrate/routes/AddUser/hooks";
import Dialog from "@/components/Dialog/Dialog";
//校验权限
transition();
const {
    Input,
    Select,
    Form
} = getComponents('search');
let sexArray=[];
const getSex = async () => {
    const sexValue = await Network.formGet('/newOne/data/getSex.do');

    for(const i in sexValue){
        if (sexArray.length<=i){
            sexArray.push({value:sexValue[i].value,label:sexValue[i].definition})
        }
    }
};
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
window.onload = function () {
    getSex();
    getDistrict();
}
const minutes=1000*60;
const hours=minutes*60;
const days=hours*24;
const View = () => {
    const service = useService(PublicService);
    const {
        listState,
        formState,
        search
    } = useListPage({
        initData: {},
        uniqKey: 'uuid',
        searchService: service.list
    });
    let obj = JSON.parse(JSON.stringify(listState));
    let nowDate = new Date();
    nowDate = nowDate.getTime() - days*14;
    for (const i in obj.list){
        const oldDate = new Date(obj.list[i].loginTime);
        if (oldDate < nowDate){
            obj.list[i]['loginTime']=obj.list[i]['loginTime']+"【不活跃】";
        }else {
            obj.list[i]['loginTime']=obj.list[i]['loginTime']+"【活跃】";
        }
    }
    const {
        toEdit,
        toAdd
    } = useToRoute();
    const {
        remove
    } = useRemove({
        refresh: listState.refresh
    });
    async function downExl() {
        window.open('/newOne/示例表.xlsx');
    }
    async function delUser() {
        const flag = await Dialog.confirm("是否确认删除？");
        if (flag) {
            Spin.show('处理数据中，请稍等...');
            const newVar = Network.json('/newOne/user/deleteUser.do', obj.check.value);
            newVar.then(async value => {
                Spin.hide();
                if(value.result){
                    await Dialog.alert('删除成功');
                }else {
                    await Dialog.alert('删除失败');
                }
                window.location.reload();
            })
        }
    }
    return (<Fragment>
        <Panel>
            <Form grid={5} formState={formState} query={search} resetRetry>



                <Input field="account" label="用户账号" />



                <Input field="phone" label="用户手机号" />



                <Select
                    field="sex"
                    label="用户性别"
                    options={sexArray}
                />

                <Select
                    field="district"
                    label="用户所属区"
                    options={districtArray}
                />

                <Button bsStyle="success" onClick={toAdd}>新增</Button>
                <Button onClick={delUser}>删除选中项</Button>
                <Button bsStyle="success" onClick={downExl}>下载excel模板</Button>
                <FileUpload
                    request={{
                        url: '/newOne/excel/dataImport.do'
                    }}
                    onComplete={async ({response, status}) => {
                        Spin.hide();
                        // response ==> 后台返回的数据，一般都有包含文件的路径等重要信息
                        // status ==> 上传状态
                        console.log(response)
                        if (response.data.flag){
                            await Dialog.alert("导入成功");
                        }
                        window.location.reload();
                    }}
                    uploadProps={{
                        //接受文件类型，如果不传默认为接受图片：accept: '.gif,.jpg,.jpeg,.bmp,.png,.GIF,.JPG,.PNG,.BMP'
                        accept: '.xlsx'
                    }}
                    onChange={e => {
                        Spin.show('处理数据中，请稍等...');
                        // 选择或更改文件后触发
                    }}
                >
                    <Button type="button" bsStyle="primary">导入excel文件到表格</Button>
                </FileUpload>
            </Form>

        </Panel>
        <Panel>
            <ShareList listState={listState}>
                <NumberColumn />
                {/* 内置的选中列 */}
                <CheckColumn />

                <Column field="account" label="用户账号" />

                <Column field="phone" label="用户手机号" />

                <Column field="email" label="用户邮箱" />

                <Column field="_cn_sex" label="用户性别" />

                <Column field="_cn_district" label="用户所属区" />

                <Column field="loginTime" label="最后登录时间【活跃度】" />


                <ActionColumn width={100}>
                    <ActionColumn.Edit onClick={toEdit} />
                    <ActionColumn.Remove onClick={remove} />
                </ActionColumn>
            </ShareList>
        </Panel>
    </Fragment>);
};

export default View;
