/*
 * @(#) AdvanceSearch.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2021-01-08 15:22:11
 */
import React, { Fragment } from 'react';
import SearchBar from '@mshare/mshareui-form/builder/SearchBar';
import { useBoolean } from 'ahooks';
import Advance from './components/Advance';

const AdvanceSearch = props => {
    const {
        children,
        query,
        reset,
        ...otherProps
    } = props;
    const [
        advance, {
            setTrue: advanceOpen,
            setFalse: advanceClose
        }
    ] = useBoolean(false);

    return (<Fragment>
        <SearchBar {...otherProps} advance open={advanceOpen} query={query} />
        <Advance visible={advance} close={advanceClose} query={query} reset={reset}>
            {children}
        </Advance>
    </Fragment>);
};

export default AdvanceSearch;
