package com.dyb.platforms.fixfunds.common.controller.messengerbean.model;

import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;

/**
 * Created by lenovo on 2016/10/20.
 */
public class MessengerBeanModel {
    //绑定手机号
    private String accountPhone;
    //账户昵称
    private String accountName;
    //真实姓名
    private String realName;
    //信使豆信息
    private MessengerBean messengerBean;

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public MessengerBean getMessengerBean() {
        return messengerBean;
    }

    public void setMessengerBean(MessengerBean messengerBean) {
        this.messengerBean = messengerBean;
    }
}
