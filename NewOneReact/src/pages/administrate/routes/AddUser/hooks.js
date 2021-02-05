import { useHistory } from 'react-router';
import useRequest from '@ahooksjs/use-request';
import Dialog from '@/components/Dialog';
import Network, { isAbort } from '@share/network';
import { useService } from '@share/framework';
import PublicService from '@/services/PublicService';
import { StringUtil } from '@share/common'
import { TextValidatorUtil } from '@share/common'
export const useSave = props => {
    const {
        formState
    } = props;
    const history = useHistory();
    const service = useService(PublicService);
    const {
        run,
        loading
    } = useRequest(service.addUser, {
        manual: true,
        throwOnError: true
    });

    const save = async () => {
        if (loading) return;
        const validRs = await formState.valid();

        if (validRs.some(item => item !== true)) return;
        const data = formState.getFormData();
        const emailFlag = TextValidatorUtil.isEmail(data.email);
        const phoneFlag = TextValidatorUtil.isMobileExact(data.phone);
        const passwordFlag = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{8,20}$/.test(data.password);

        if(data.account === "" || data.sex === "" || data.district === "" || data.account === undefined || data.sex === undefined || data.district === undefined) {
            await Dialog.alert('必填项为空');
            return;
        }else if (!passwordFlag){
            await Dialog.alert('密码校验出错，密码要求均在8位以上，且密码必须同时包含小写字母(a-z)、大写字母(A-Z)、数字(0-9)');
            return;
        }else if(!phoneFlag){
            await Dialog.alert('手机号格式有误');
            return;
        }else if(!emailFlag){
            await Dialog.alert('邮箱格式有误');
            return;
        }
        data.account=data.account.trim()
        data.sex=data.sex.trim()
        data.district=data.district.trim()
        if(data.account === "" || data.sex === "" || data.district === "" || data.account === undefined || data.sex === undefined || data.district === undefined) {
            await Dialog.alert('必填项为空');
            return;
        }
        //对输入的密码进行md5加密操作
        data.password=StringUtil.md5(data.password);
        try {
            await run(data);
            await Dialog.alert('保存成功');
            history.goBack();
        } catch (e) {
            if (isAbort(e)) {
                return;
            }
            await Dialog.alert(e.message.split(']')[0].slice(1));
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
