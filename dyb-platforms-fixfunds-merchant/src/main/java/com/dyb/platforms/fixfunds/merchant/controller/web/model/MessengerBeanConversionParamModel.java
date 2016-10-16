package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.entity.ConversionInvoiceDetails;

import java.util.List;

/**
 * Created by lenovo on 2016/10/16.
 */
public class MessengerBeanConversionParamModel {
    //转换类型
    private String messengerBeanType;
    //转换数量
    private Double messengerBean;
    //二级密码
    private String tradePassword;
    //发票明细
    private List<ConversionInvoiceDetails> conversionInvoiceDetailses;

    public String getMessengerBeanType() {
        return messengerBeanType;
    }

    public void setMessengerBeanType(String messengerBeanType) {
        this.messengerBeanType = messengerBeanType;
    }

    public Double getMessengerBean() {
        return messengerBean;
    }

    public void setMessengerBean(Double messengerBean) {
        this.messengerBean = messengerBean;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public List<ConversionInvoiceDetails> getConversionInvoiceDetailses() {
        return conversionInvoiceDetailses;
    }

    public void setConversionInvoiceDetailses(List<ConversionInvoiceDetails> conversionInvoiceDetailses) {
        this.conversionInvoiceDetailses = conversionInvoiceDetailses;
    }
}
