import styles from './Advance.scss';
import Sidebar from '../Sidebar';
import React from 'react';
import { Flex, Button } from '@mshare/mshareui';

const Advance = props => {
    const {
        visible,
        query,
        reset,
        close,
        children
    } = props;

    const queryWrap = () => {
        query();
        close();
    };

    return (<Sidebar visible={visible} onClose={close}>
        <Flex direction="column" align="stretch" className={styles.searchBox}>
            <Flex.Item className={styles.forms}>
                {children}
            </Flex.Item>
            <Flex align="stretch" className={styles.btns}>
                <Flex.Item>
                    <Button type="default" onClick={reset}>重置</Button>
                </Flex.Item>
                <Flex.Item>
                    <Button type="primary" onClick={queryWrap}>确定</Button>
                </Flex.Item>
            </Flex>
        </Flex>
    </Sidebar>);
};

export default Advance;
