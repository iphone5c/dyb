package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;

/**
 * Created by lenovo on 2016/10/10.
 */
public class RecommendIncentiveModel {
    //订单信息
    private RecommendIncentive recommendIncentive;
    //被推荐人
    private String name;

    public RecommendIncentive getRecommendIncentive() {
        return recommendIncentive;
    }

    public void setRecommendIncentive(RecommendIncentive recommendIncentive) {
        this.recommendIncentive = recommendIncentive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
