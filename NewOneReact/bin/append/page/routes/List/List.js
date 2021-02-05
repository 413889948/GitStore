/*
 * @(#) Home.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-04 16:47:25
 */
import '@share/shareui-portal-html';

import React, { Fragment, useCallback, useRef } from 'react';
import { Panel, Button } from '@share/shareui';
import { getComponents } from '@share/shareui-form';
import { useFormState } from '@/utils/hooks';
import ShareList from '@share/list';
import { getList } from '@/services';

const { Column, NumberColumn } = ShareList;
const {
    Input,
    RadioGroup,
    CheckboxGroup,
    Calendar,
    CalendarRange,
    Select,
    Form
} = getComponents('search');

const options = [
    { label: '选项一', value: '1' },
    { label: '选项二', value: '2' },
    { label: '选项三', value: '3' },
];

const dataSource = [
    {
        xjh: 'wervgh',
        xm: '张三',
        sfzh: '42347',
        sqsj: '333',
        gznx: '3年'
    }, {
        xjh: 'werewy',
        xm: 'liaoyf',
        sfzh: '222',
        sqsj: '346',
        gznx: '5年'
    }
];

const remoteDataSource = request => getList('schemaId', request);

const List = props => {

    const { history } = props;

    const { form } = useFormState({
        input: '',
        raido: '',
        checkbox: [],
        calendar: '',
        calendarRange: {
            start: '',
            end: ''
        },
        select: ''
    });

    const listApiRef = useRef();

    const query = useCallback(queryData => {
        console.info(queryData);
        listApiRef.current.query(queryData);
    }, [listApiRef]);

    return (
        <Fragment>
            <Panel>
                <Form formState={form} grid={4} query={query}>
                    <Input label="文本" field="input" col={12} tip="测试" />
                    <Input label="文本" field="input" col={12} />
                    <Input label="文本" field="input" col={12} />
                    <Input label="文本" field="input" col={12} />
                    <RadioGroup label="单选" field="radio" options={options} />
                    <CheckboxGroup label="多选" field="checkbox" options={options} />
                    <Select label="下拉" field="select" options={options} />
                    <Calendar label="日期" field="calendar" />
                    <CalendarRange label="日期范围" field="calendarRange" col={6} />
                    <Button bsStyle="info">其他操作</Button>
                </Form>
            </Panel>

            <Panel>
                <Panel.Head title="查询条件" />
                <Panel.Body full>
                    <ShareList
                        dataSource={dataSource}
                        useFullRow={false}
                        apiRef={
                            apiRef => {
                                listApiRef.current = apiRef;
                            }
                        }
                    >
                        <NumberColumn width={70} />
                        <Column field="xjh" label="学籍号" />
                        <Column field="xm" label="姓名" />
                        <Column field="sfzh" label="身份证号" />
                        <Column field="info" label="富文本" >
                            {
                                (value, row) => {
                                    const { gznx, xm } = row;

                                    return (
                                        <div>
                                            <p>工作年限：{gznx}</p>
                                            <p>
                                                教师职称：
                                                {xm === 'liaoyf' ? '高级' : '初级'}
                                            </p>
                                        </div>
                                    );
                                }
                            }
                        </Column>
                        <Column field="action" label="操作" width={100}>
                            {
                                (value, row) => <a onClick={() => history.push(`/detail?xjh=${row.xjh}`)}>详细</a>
                            }
                        </Column>
                    </ShareList>
                </Panel.Body>
            </Panel>
        </Fragment>
    );

};

export default List;
