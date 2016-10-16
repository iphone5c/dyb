package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Invoice;

import java.util.List;

/**
 * Created by 魏源 on 2016/10/17.
 */
public class WithdrawalParamModel {
    //回购类型
    private String withdrawalType;
    // 回购的信使豆
    private Double withdrawalNum;
    //二级密码
    private String tradePassword;
    //发票明细
    private List<Invoice> invoiceList;

    public String getWithdrawalType() {
        return withdrawalType;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public void setWithdrawalType(String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    public Double getWithdrawalNum() {
        return withdrawalNum;
    }

    public void setWithdrawalNum(Double withdrawalNum) {
        this.withdrawalNum = withdrawalNum;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
