/*
 * @(#) MoreLoad.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author finalcat
 * <br> 2021-01-07 20:44:53
 */
import React, { useEffect, useRef } from 'react';
import useInViewport from 'ahooks/es/useInViewport';
import debounce from 'lodash/debounce';
import styles from './MoreLoad.scss';

const MoreLoad = props => {
    const {
        hasMore,
        more,
        loading
    } = props;
    const loadingDomRef = useRef();
    const inView = useInViewport(loadingDomRef);
    const moreMethod = useRef(debounce(more, 200));

    useEffect(() => {
        if (hasMore && !loading && inView) {
            console.info('load more');
            moreMethod.current();
        }
    }, [hasMore, loading, inView]);

    if (!hasMore) {
        return <p className={styles.noMore}>没有更多数据了</p>;
    }

    return <p className={styles.noMore} ref={loadingDomRef}>{hasMore ? '加载中' : ''}</p>;
};

export default MoreLoad;
