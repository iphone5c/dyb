package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业规模
 * Created by lenovo on 2016/9/20.
 */
public enum Sex {
    /**
     * 男人
     */
    男,
    /**
     * 女人
     */
    女,
    /**
     * 保密
     */
    保密;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Sex sex : Sex.values()){
            result.add(NameValue.create(sex.toString(), sex.toString()));
        }
        return result;
    }
}
