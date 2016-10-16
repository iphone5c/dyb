package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;

import java.util.List;

/**
 * Created by lenovo on 2016/10/16.
 */
public class OrderConsumerRegistrationParamModel {
    //信使ID
    private String accountKey;
    //订单详情
    private List<OrderItem> orderItemList;

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
