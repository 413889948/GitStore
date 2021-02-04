/*
 * @(#) FlagEntity
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-02 17:57:23
 */

package com.sunsharing.newone.demo.entity.data;

import lombok.Data;

/**
 * @author 黄祎翔
 * @time 2021/2/2 17:57
 */
@Data
public class TransitionEntity {

    Boolean flag;

    String role;

    public TransitionEntity(Boolean flag, String role) {
        this.flag = flag;
        this.role = role;
    }
}
