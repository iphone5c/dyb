package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 商圈
 * Created by lenovo on 2016/9/20.
 */
public enum BusinessCircle {
    /**
     * 美食
     */
    美食,
    /**
     * 酒店
     */
    酒店,
    /**
     * 生活旅游
     */
    生活旅游,
    /**
     * 超市
     */
    超市,
    /**
     * 汽车
     */
    汽车,
    /**
     * 手机数码
     */
    手机数码,
    /**
     * 休闲娱乐
     */
    休闲娱乐,
    /**
     * 生活服务
     */
    生活服务,
    /**
     * 电脑办公
     */
    电脑办公,
    /**
     * 家用电器
     */
    家用电器,
    /**
     * 母婴童装
     */
    母婴童装,
    /**
     * 个护化妆
     */
    个护化妆,
    /**
     * 鞋靴箱包
     */
    鞋靴箱包,
    /**
     * 潮流服装
     */
    潮流服装,
    /**
     * 奢品礼品
     */
    奢品礼品,
    /**
     * 钟表珠宝
     */
    钟表珠宝,
    /**
     * 玩具乐器
     */
    玩具乐器,
    /**
     * 内衣配饰
     */
    内衣配饰,
    /**
     * 家居家纺
     */
    家居家纺,
    /**
     * 滋补养生
     */
    滋补养生,
    /**
     * 家装建材
     */
    家装建材,
    /**
     * 教育培训
     */
    教育培训,
    /**
     * 户外运动
     */
    户外运动,
    /**
     * 花鸟文娱
     */
    花鸟文娱;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BusinessCircle businessCircle : BusinessCircle.values()){
            result.add(NameValue.create(businessCircle.toString(), businessCircle.toString()));
        }
        return result;
    }
}
