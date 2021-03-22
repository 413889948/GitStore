/*
 * @(#) CardItem.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author finalcat
 * <br> 2021-01-07 20:47:51
 */
import styles from './CardItem.scss';
import React, { Fragment } from 'react';
import { WhiteSpace, Flex } from '@mshare/mshareui';

const CardItem = props => {
    const {
        columns,
        row,
        rowIdx,
        mappingRow
    } = props;

    return (<Fragment>
        <div className={styles.card}>
            <Flex>
                <Flex.Item>
                    <div className={styles.name}> {mappingRow.title}</div>
                    <div className={styles.info}>{mappingRow.info}</div>
                    <div className={styles.location}>{mappingRow.sub}</div>
                    <div className={styles.action}>{mappingRow.action}</div>
                </Flex.Item>
            </Flex>
        </div>
    </Fragment>);
};

export default CardItem;
