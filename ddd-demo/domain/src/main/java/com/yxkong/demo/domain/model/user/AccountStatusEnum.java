package com.yxkong.demo.domain.model.user;

import lombok.Getter;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/15 1:59 PM
 * @version: 1.0
 */
@Getter
public enum AccountStatusEnum {
    ON(0,"启用"),
    OFF(-1,"禁用");
    private int status;
    private String desc;

    AccountStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static AccountStatusEnum getDefault(){
        return AccountStatusEnum.ON;
    }
    public static AccountStatusEnum get(Integer status){
        for (AccountStatusEnum statusEnum:AccountStatusEnum.values()){
            if (statusEnum.getStatus() == status){
                return statusEnum;
            }
        }
        return null;
    }
}
