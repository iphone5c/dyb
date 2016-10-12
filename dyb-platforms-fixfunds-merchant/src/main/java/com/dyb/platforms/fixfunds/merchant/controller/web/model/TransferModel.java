package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.transfer.entity.Transfer;

/**
 * Created by lenovo on 2016/10/11.
 */
public class TransferModel {
    //转赠人真实姓名
    private String transferAccountName;
    //转赠人电话
    private String transferAccountPhone;
    //获赠人真实姓名
    private String gainAccountName;
    //获赠人电话
    private String gainAccountPhone;
    //转赠信息
    private Transfer transfer;

    public String getTransferAccountName() {
        return transferAccountName;
    }

    public void setTransferAccountName(String transferAccountName) {
        this.transferAccountName = transferAccountName;
    }

    public String getTransferAccountPhone() {
        return transferAccountPhone;
    }

    public void setTransferAccountPhone(String transferAccountPhone) {
        this.transferAccountPhone = transferAccountPhone;
    }

    public String getGainAccountName() {
        return gainAccountName;
    }

    public void setGainAccountName(String gainAccountName) {
        this.gainAccountName = gainAccountName;
    }

    public String getGainAccountPhone() {
        return gainAccountPhone;
    }

    public void setGainAccountPhone(String gainAccountPhone) {
        this.gainAccountPhone = gainAccountPhone;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}
