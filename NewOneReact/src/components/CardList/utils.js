/*
 * @(#) utils.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author finalcat
 * <br> 2021-01-07 20:42:27
 */
import React, { Fragment } from 'react';
import { Column } from '@share/list';

function resolveChild(children) {
    return React.Children.toArray(children).reduce((acc, cur) => {
        if (!React.isValidElement(cur)) return acc;
        const {
            type,
            props
        } = cur;

        if (type === Fragment) {
            acc.push(...resolveChild(props.children));
        }

        if (props._columnType === 'column' || props._columnType === 'checkbox') {
            acc.push(cur.type.getConfig(props));
        }

        return acc;
    }, []);
}

export function calcColumns(columns, children) {
    if (columns) return columns.map(column => Object.assign({}, Column.defaultProps, column));
    return resolveChild(children);
}
export function getMappingRow(columns, row, rowIndex) {
    return columns.reduce((acc, cur) => {
        const {
            mappingField,
            field,
            render,
            children: childrenRender
        } = cur;
        const contentRender = render ?? childrenRender;
        const value = row[field];
        const content = typeof contentRender === 'function' ? contentRender(value, row, rowIndex, cur) : value;

        if (mappingField && field) {
            acc[mappingField] = content;
        }

        return acc;
    }, {});
}
