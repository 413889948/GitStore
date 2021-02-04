/*
 * @(#) MyResponseCode
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 16:11:35
 */

package com.sunsharing.newone.demo.constant.enums;

import com.sunsharing.share.common.base.TypeConvUtil;
import com.sunsharing.share.common.base.exception.ResponseCode;
import com.sunsharing.share.common.base.exception.ShareBusinessException;

/**
 * @author 黄祎翔
 * @time 2021/2/2 16:11
 */
public enum MyResponseCode  implements ResponseCode {

    NAME_REPETITION(1900, "用户名已存在"),
    KEY_WRONG(1901, "管理员秘钥错误");
    MyResponseCode(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private int status;
        private String message;


    @Override
        public int getCode() {
            return status;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public boolean eq(String status) {
            return this.status == TypeConvUtil.toInt(status, -1);
        }

        public boolean eq(int status) {
            return this.status == status;
        }

        public ShareBusinessException businessException() {
            return new ShareBusinessException(this);
        }

}
