package com.dyb.platforms.fixfunds.services.business.order.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态
 * Created by lenovo on 2016/9/20.
 */
public enum OrderStatus {
    /**
     * 待提交资料
     */
    待提交资料,
    /**
     * 待结算
     */
    待结算,
    /**
     * 已结算
     */
    已结算,
    /**
     * 已撤销
     */
    已撤销;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (OrderStatus orderStatus : OrderStatus.values()){
            result.add(NameValue.create(orderStatus.toString(), orderStatus.toString()));
        }
        return result;
    }
}
