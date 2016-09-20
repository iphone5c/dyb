package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家类别
 * Created by lenovo on 2016/9/20.
 */
public enum MerchantType {
    /**
     * 商家
     */
    商家;

    public static List<NameValue> getAllConvertName(){
        java.util.List<NameValue> result = new ArrayList<>();
        for (MerchantType merchantType : MerchantType.values()){
            result.add(NameValue.create(merchantType.toString(), merchantType.toString()));
        }
        return result;
    }
}
