package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;

/**
 * 订单分页模型
 * Created by lenovo on 2016/9/26.
 */
public class OrderParamModel {
    //订单信息
    private Order order;
    //信使信息
    private Account account;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
