import { useHistory } from 'react-router';
import useRequest from '@ahooksjs/use-request';
import Dialog from '@/components/Dialog';
import { isAbort } from '@share/network';
import { useService } from '@share/framework';
import TEcNewoneDistrictService from '@/services/TEcNewoneDistrictService';
export const useSave = props => {
    const {
        formState
    } = props;
    const history = useHistory();
    const service = useService(TEcNewoneDistrictService);
    const {
        run,
        loading
    } = useRequest(service.addTEcNewoneDistrict, {
        manual: true,
        throwOnError: true
    });

    const save = async () => {
        if (loading) return;
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
        save,
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
