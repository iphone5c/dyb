package com.dyb.platforms.fixfunds.services.business.turnover.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 让利状态
 * Created by lenovo on 2016/10/8.
 */
public enum BenefitPriceStatus {
    /**
     * 未让利
     */
    未让利,
    /**
     * 未缴清
     */
    未缴清,
    /**
     * 已缴清
     */
    已缴清,
    /**
     * 不予登记
     */
    不予登记,
    /**
     * 金额不符
     */
    金额不符,
    /**
     * 无效
     */
    无效;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BenefitPriceStatus benefitPriceStatus : BenefitPriceStatus.values()){
            result.add(NameValue.create(benefitPriceStatus.toString(), benefitPriceStatus.toString()));
        }
        return result;
    }
}
