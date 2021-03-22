import React, { Fragment } from 'react';
import { Button, Flex, WhiteSpace, WingBlank } from '@mshare/mshareui';
import { useForm } from '@share/hooks';
import { useUpdate, useInitData, useToRoute } from './hooks';
import Form from '@mshare/mshareui-form/builder/Form';
import Input from '@mshare/mshareui-form/builder/Input';

const View = () => {
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

    return (<Fragment>
        <Form formState={formState}>


            <Input field="id" label="区表ID" />

            <Input field="definition" label="区名" />

            <Input field="value" label="区序号" />


        </Form>
        <WhiteSpace />
        <WingBlank>
            <Flex>
                <Flex.Item>
                    <Button type="normal" onClick={toBack}>返回</Button>
                </Flex.Item>
                <Flex.Item>
                    <Button type="primary" disabled={loading} loading={loading} onClick={update}>保存</Button>
                </Flex.Item>
            </Flex>
        </WingBlank>
    </Fragment>);
};

export default View;
