package com.dyb.platforms.fixfunds.services.business.withdrawal.entity;

/**
 * 回购发票信息
 * Created by 魏源 on 2016/10/17.
 */
public class Invoice {
    // 发票号
    private String invoiceNum;
    // 发票面额
    private Double invoiceMoney;

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }
}
