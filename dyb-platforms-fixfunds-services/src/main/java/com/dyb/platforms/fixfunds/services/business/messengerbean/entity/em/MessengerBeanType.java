package com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 信使豆类型
 * Created by lenovo on 2016/10/10.
 */
public enum  MessengerBeanType {
    /**
     * 普通信使豆
     */
    普通信使豆,
    /**
     * 待提供发票
     */
    待提供发票,
    /**
     * 转换中
     */
    转换中,
    /**
     * 注册奖励
     */
    注册奖励,
    /**
     * 欠税
     */
    欠税,
    /**
     * 待缴税
     */
    待缴税,
    /**
     * 待转赠
     */
    待转赠;

    public static List<NameValue> getAllConvertName(){
        java.util.List<NameValue> result = new ArrayList<>();
        for (MessengerBeanType messengerBeanType : MessengerBeanType.values()){
            result.add(NameValue.create(messengerBeanType.toString(), messengerBeanType.toString()));
        }
        return result;
    }
}
