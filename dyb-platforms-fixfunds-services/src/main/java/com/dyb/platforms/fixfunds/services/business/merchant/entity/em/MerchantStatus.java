package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家账户状态
 * Created by lenovo on 2016/9/20.
 */
public enum MerchantStatus {
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
        for (MerchantStatus merchantStatus : MerchantStatus.values()){
            result.add(NameValue.create(merchantStatus.toString(),merchantStatus.toString()));
        }
        return result;
    }
}
