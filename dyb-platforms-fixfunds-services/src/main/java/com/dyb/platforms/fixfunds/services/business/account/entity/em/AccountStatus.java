package com.dyb.platforms.fixfunds.services.business.account.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户状态
 * Created by lenovo on 2016/9/20.
 */
public enum AccountStatus {
    /**
     * 待提交审核
     */
    待提交审核,
    /**
     * 审核未通过
     */
    审核未通过,
    /**
     * 审核中
     */
    审核中,
    /**
     * 正常
     */
    正常,
    /**
     * 禁用
     */
    禁用;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (AccountStatus accountStatus : AccountStatus.values()){
            result.add(NameValue.create(accountStatus.toString(), accountStatus.toString()));
        }
        return result;
    }
}
