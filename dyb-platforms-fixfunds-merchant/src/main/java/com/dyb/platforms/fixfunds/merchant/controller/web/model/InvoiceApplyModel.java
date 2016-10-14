package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.InvoiceApply;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;

/**
 * Created by lenovo on 2016/10/13.
 */
public class InvoiceApplyModel {
    private Merchant merchant;
    private BankAccount bankAccount;
    private SendAddress sendAddress;
    private InvoiceApply invoiceApply;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public SendAddress getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(SendAddress sendAddress) {
        this.sendAddress = sendAddress;
    }

    public InvoiceApply getInvoiceApply() {
        return invoiceApply;
    }

    public void setInvoiceApply(InvoiceApply invoiceApply) {
        this.invoiceApply = invoiceApply;
    }
}
