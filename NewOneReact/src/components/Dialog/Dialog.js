/*
 * @(#) Dialog.js
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author wangzg
 * <br> 2020-09-22 11:19:31
 */
import { ModalTool } from '@share/shareui';
export default {

    /**
   * 消息框
   * @param msg 消息
   * @return {Promise<boolean>}
   */
    alert: msg => {
        return new Promise(resolve => {
            return new ModalTool({
                onOk: () => {
                    resolve(true);
                },
                content: msg,
                backdrop: 'static',
                bsSize: 'sm',
                closeBtn: false,
                cancelText: null
            });
        });
    },

    /**
   * 确认框
   * @param msg
   * @return {Promise<boolean>}
   */
    confirm: msg => {
        return new Promise(resolve => {
            return new ModalTool({
                loseBtn: false,
                bsSize: 'sm',
                bsStyle: 'warning',
                backdrop: 'static',
                closeBtn: false,
                onOk: () => {
                    resolve(true);
                },
                content: msg,
                onCancel: () => {
                    resolve(false);
                }
            });
        });
    },
};
