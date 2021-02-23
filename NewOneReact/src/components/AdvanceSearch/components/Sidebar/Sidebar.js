import React from 'react';
import cn from 'classnames';
import PropTypes from 'prop-types';
import './Sidebar.scss';

const Sidebar = ({
    visible,
    onClose,
    children
}) => {
    return (<div
        className={cn('c-sidebar', {
            'c-sidebar-visible': visible
        })}
    >
        <div className="c-sidebar-box">
            <div className="c-sidebar-mask" onClick={onClose} />
            <div className="c-sidebar-main">
                {children}
            </div>
        </div>
    </div>);
};

Sidebar.propTypes = {
    visible: PropTypes.bool,
    onClose: PropTypes.func
};
Sidebar.defaultProps = {
    visible: false
};
export default Sidebar;
