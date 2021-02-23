import styles from './CardList.scss';
import React from 'react';
import { Flex } from '@mshare/mshareui';
import ShareList from '@share/list';
import MoreLoad from './components/MoreLoad';
import { calcColumns, getMappingRow } from './utils';
const {
    Column
} = ShareList;

const CardList = props => {
    const {
        children,
        columns,
        CardItem,
        listState
    } = props;
    const columnsResult = calcColumns(columns, children);
    const {
        uniqKey
    } = listState;

    return (<Flex.Item className={styles.list}>
        {listState.list.map((row, rowIdx) => {
            const key = uniqKey ? row[uniqKey] : rowIdx;

            return <CardItem key={key} row={row} columns={columnsResult} rowIdx={rowIdx} mappingRow={getMappingRow(columnsResult, row, rowIdx)} />;
        })}
        <MoreLoad length={listState.list.length} more={listState.more} hasMore={listState.hasMore} loading={listState.loading} />
    </Flex.Item>);
};

CardList.Column = Column;
export default CardList;
