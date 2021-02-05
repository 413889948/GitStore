/*
 * @(#) Detail.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-05 15:00:49
 */
import { Panel, ButtonToolBar, Button } from '@share/shareui';
import { getComponents } from '@share/shareui-form';
import React, { Fragment, useCallback } from 'react';
import { useFormState } from '@/utils/hooks';
import { StringUtil } from '@share/common';

const { Form, Row, Input, Text } = getComponents();

const initDataMock = () => new Promise(resolve => {
    setTimeout(() => {
        resolve({
            input1: '',
            input2: '',
            address: '厦门市湖里区莲前街道前埔社区店上西里186号506',
            livingAddress: '直系亲属自购（自建）房屋'
        });
    }, 500);
});

const Detail = props => {
    const { history, location } = props;
    const { xjh } = StringUtil.fromQueryString(location.search);

    console.info('学籍号', xjh);

    const { form } = useFormState(initDataMock);

    const save = useCallback(async () => {
        const validRs = await form.valid();

        if (!validRs.every(item => item === true)){
            return;
        }

        const data = form.getFormData();

        console.info(data);

    }, [form]);

    return (
        <Fragment>
            <Panel>
                <Panel.Head title={`学籍号：${xjh}`} />
                <Form formState={form}>
                    <Row className="item-sm">
                        <Text label="暂(居)住地详址" field="address" />
                        <Text label="居住地址" field="livingAddress" />
                    </Row>
                    <Row>
                        <Input label="输入框" field="input1" rule="required" />
                        <Input label="输入框" field="input2" />
                    </Row>
                </Form>
            </Panel>
            <ButtonToolBar>
                <Button bsStyle="primary" onClick={save}>保存</Button>
                <Button onClick={() => history.push('/list')}>返回</Button>
            </ButtonToolBar>
        </Fragment>
    );
};

export default Detail;
