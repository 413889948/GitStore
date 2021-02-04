/*
 * @(#) RoleEnums
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2021
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2021-02-04 09:28:24
 */

package com.sunsharing.newone.demo.constant.enums;

/**
 * @author 黄祎翔
 * @time 2021/2/4 9:28
 */
public enum RoleEnums {
    ADMINISTRATE("administrate"),
    COMMON("common");

    String role;

    RoleEnums(String role) {
        this.role=role;
    }

    public String GetRole()
    {
        return role;
    }
    /**
     * 提前判断，用于解决
     * Case中出现的Constant expression required
     * @param value
     * @return
     */
    public static RoleEnums getByValue(String value){
        for(RoleEnums x:values()){
            if(x.GetRole().equals(value)){
                return x;
            }
        }
        return null;
    }
}
