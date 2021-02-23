import styles from './FilterBar.scss';
import React from 'react';
import { Flex } from '@mshare/mshareui';

const FilterBar = props => {
    const {
        children
    } = props;

    return <Flex id="menudom" className={styles.filter} align="center" justify="center">{children}</Flex>;
};

export default FilterBar;
