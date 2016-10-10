package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.Industry;

/**
 * Created by 魏源 on 2016/10/11.
 */
public class RecommendRecordModel {
    //真实姓名
    private String realName;
    //所在地
    private String address;
    //个人邮箱
    private String email;
    //所属行业
    private Industry industry;
    //账户信息
    private Account account;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
