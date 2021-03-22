/*
 * @(#) hooks.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2020-11-09 11:25:34
 */
import useRequest from '@ahooksjs/use-request';
import Dialog from '@/components/Dialog';
import { isAbort } from '@share/network';
import { useHistory, generatePath, useParams } from 'react-router';
import { useService } from '@share/framework';
import TEcNewoneDistrictService from '@/services/TEcNewoneDistrictService';
export const useRemove = props => {
    const service = useService(TEcNewoneDistrictService);
    const {
        refresh
    } = props;
    const {
        run,
        loading
    } = useRequest(service.deleteTEcNewoneDistrict, {
        manual: true,
        throwOnError: true
    });

    const remove = async row => {
        const { id } = row;
        const ids = Array.isArray(id) ? id : [id];
        const confirm = await Dialog.confirm('确定删除该记录？');

        if (!confirm) return;

        try {
            await run(ids);
            await Dialog.alert('删除成功');
            await refresh();
        } catch (e) {
            if (isAbort(e)) {
                return;
            }

            await Dialog.alert('删除失败');
        }
    };

    return {
        remove,
        loading
    };
};
export const useToRoute = () => {
    const history = useHistory();

    const toEdit = row => {
        const { id } = row;

        history.push('/update', {
            id
        });
    };

    const toAdd = () => {
        history.push('/add');
    };

    const toBack = () => history.goBack();

    return {
        toEdit,
        toAdd,
        toBack
    };
};
