import styles from './Header.scss';
import React from 'react';
import { Popover, Icon } from '@mshare/mshareui';
const {
    Item
} = Popover;

const Header = props => {
    const {
        title,
        actions = []
    } = props;
    const overlay = actions.map((action, idx) => {
        const {
            label,
            icon,
            disabled
        } = action;

        return (<Item key={idx} icon={icon} disabled={disabled}>
            {label}
        </Item>);
    });

    const onSelect = (item, idx) => {
        const {
            handle
        } = actions?.[idx];

        if (typeof handle === 'function') {
            handle();
        }
    };

    return (<header className={styles.header}>
        <div className={styles.leftBtn}>
            <Icon type="left" />
            <span>返回</span>
        </div>
        <h1>{title}</h1>
        {actions.length > 0 && <div className={styles.rightBtn}>
            <Popover
                mask overlayClassName="fortest" overlayStyle={{
                    color: 'currentColor'
                }} overlay={overlay} align={{
                    overflow: {
                        adjustY: 0,
                        adjustX: 0
                    },
                    offset: [-10, 0]
                }} onSelect={onSelect}
            >
                <Icon type="ellipsis" />
            </Popover>
        </div>}
    </header>);
};

export default Header;
