/*
 * @(#) hooks.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2019-11-05 14:53:52
 */

import { useEffect, useReducer, useRef, useState } from 'react';
import { FormState } from '@share/shareui-form';

export const useFormState = initData => {
    const [formState, setFormState] = useState(null);
    // eslint-disable-next-line no-unused-vars
    const [ignored, forceUpdate] = useReducer(x => x + 1, 0);
    const isInit = useRef(false);

    useEffect(() => {
        async function initFormState(){
            const promise = typeof initData === 'function' ? initData() : initData;
            const response = await promise;
            const formStateInstance = new FormState(response, nextState => {
                setFormState(nextState);
                forceUpdate();
            });

            setFormState(formStateInstance);
        }
        if (!initData) return;

        if (isInit.current){
            return;
        } else {
            isInit.current = true;
        }

        initFormState();

    }, [initData]);

    return {
        form: formState
    };
};
