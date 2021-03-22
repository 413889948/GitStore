import React, { Fragment } from 'react';
import { Button, Flex, WhiteSpace, WingBlank } from '@mshare/mshareui';
import { useForm } from '@share/hooks';
import { useSave, useToRoute } from './hooks';
import Form from '@mshare/mshareui-form/builder/Form';
import Input from '@mshare/mshareui-form/builder/Input';

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

    return (<Fragment>
        <Form formState={formState}>


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
                    <Button type="primary" disabled={loading} loading={loading} onClick={save}>保存</Button>
                </Flex.Item>
            </Flex>
        </WingBlank>
    </Fragment>);
};

export default View;
