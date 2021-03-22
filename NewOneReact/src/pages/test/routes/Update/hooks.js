import { useHistory, useLocation, useParams } from 'react-router';
import useRequest from '@ahooksjs/use-request';
import { useEffect, useMemo } from 'react';
import { Spin } from '@share/shareui';
import Dialog from '@/components/Dialog';
import { isAbort } from '@share/network';
import { fromQueryString } from '@share/common/dist/es/StringUtil';
import { useService } from '@share/framework';
import TEcNewoneDistrictService from '@/services/TEcNewoneDistrictService';

const useSpin = ({
    loading
}) => {
    useEffect(() => {
        if (loading) {
            Spin.show();
        } else {
            Spin.hide();
        }
    }, [loading]);
};

const useErrorDialog = ({
    error
}) => {
    useEffect(() => {
        if (error) {
            Dialog.alert(`数据获取失败:${error.message}`);
        }
    }, [error]);
};

export const useQuery = () => {
    const {
        search
    } = useLocation();

    return useMemo(() => fromQueryString(search), [search]);
};
export const useInitData = props => {
    const {
        formState
    } = props;
    const {
        id
    } = useQuery();
    const service = useService(TEcNewoneDistrictService);
    const {
        loading,
        error
    } = useRequest(service.getTEcNewoneDistrict, {
        ready: !!id,
        defaultParams: [
            {
                id
            }
        ],
        throwOnError: true,

        onSuccess(result) {
            formState.setFormData(result);
        }

    });

    useSpin({
        loading
    });
    useErrorDialog({
        error
    });
};
export const useUpdate = props => {
    const {
        formState
    } = props;
    const history = useHistory();
    const service = useService(TEcNewoneDistrictService);
    const {
        run,
        loading
    } = useRequest(service.updateTEcNewoneDistrict, {
        manual: true,
        throwOnError: true
    });

    const update = async () => {
        const validRs = await formState.valid();

        if (validRs.some(item => item !== true)) return;
        const data = formState.getFormData();

        try {
            await run(data);
            await Dialog.alert('保存成功');
            history.goBack();
        } catch (e) {
            // isAbort 为true 说明已经执行了公共异常处理
            if (isAbort(e)) {
                return;
            }

            await Dialog.alert('保存失败');
        }
    };

    return {
        update,
        loading
    };
};
export const useToRoute = () => {
    const history = useHistory();

    const toBack = () => history.goBack();

    return {
        toBack
    };
};
