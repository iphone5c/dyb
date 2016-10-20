package com.dyb.platforms.fixfunds.common.controller.withdrawal;

import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;

/**
 * Created by lenovo on 2016/10/20.
 */
public class WithdrawalModel {
    //回购申请信息
    private Withdrawal withdrawal;
    //申请人姓名
    private String applyAccountName;

    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getApplyAccountName() {
        return applyAccountName;
    }

    public void setApplyAccountName(String applyAccountName) {
        this.applyAccountName = applyAccountName;
    }
}
