package com.dyb.platforms.fixfunds.common.controller.commodity.model;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;

/**
 * Created by lenovo on 2016/10/19.
 */
public class CommodityModel {
    //商品信息
    private Commodity commodity;
    //商家信息
    private Account account;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
