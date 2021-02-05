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
import Network, { isAbort } from '@share/network';
import { useHistory, generatePath, useParams } from 'react-router';
import { useService } from '@share/framework';
import PublicService from '@/services/PublicService';
export const useRemove = props => {
    const service = useService(PublicService);
    const {
        refresh
    } = props;
    const {
        run,
        loading
    } = useRequest(service.deleteUser, {
        manual: true,
        throwOnError: true
    });

    const remove = async row => {

        const id = row['uuid'];
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
    const params = useParams();

    const toEdit = row => {
        const path = generatePath('/updateUser:uuid', Object.assign(params, row));

        history.push(path);
    };

    const toAdd = () => {
        const path = generatePath('/addUser', params);

        history.push(path);
    };

    const toBack = () => history.goBack();

    return {
        toEdit,
        toAdd,
        toBack
    };
};

// 校验权限
export const transition = async () => {
    const value = await Network.formGet('/newOne/transition.do');
    if(value.flag){
        switch (value.role) {
            case "1":
                return ;
            case "2":
                await Dialog.alert('权限不足，请联系管理员！');
                window.location.replace("/");
            default:
                await Dialog.alert('未登录，请登录！');
                window.location.replace("/");
        }
    }else {
        await Dialog.alert('未登录，请登录！');
        window.location.replace("/")
    }
};
