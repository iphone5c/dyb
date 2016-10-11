package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 行业类别
 * Created by lenovo on 2016/9/20.
 */
public enum IndustryType {
    /**
     * 红木
     */
    红木,
    /**
     * 珠宝
     */
    珠宝,
    /**
     * 装修
     */
    装修,
    /**
     * 汽车
     */
    汽车,
    /**
     * 家具
     */
    家具,
    /**
     * 其它
     */
    其它;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (IndustryType industryType : IndustryType.values()){
            result.add(NameValue.create(industryType.toString(), industryType.toString()));
        }
        return result;
    }
}
