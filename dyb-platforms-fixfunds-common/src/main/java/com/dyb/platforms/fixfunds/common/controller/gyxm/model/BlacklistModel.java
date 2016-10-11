package com.dyb.platforms.fixfunds.common.controller.gyxm.model;

import com.dyb.platforms.fixfunds.services.business.blacklist.entity.Blacklist;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;

/**
 * Created by lenovo on 2016/10/11.
 */
public class BlacklistModel {
    //商家信息
    private Merchant merchant;
    //黑名单信息
    private Blacklist blacklist;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Blacklist getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Blacklist blacklist) {
        this.blacklist = blacklist;
    }
}
