package com.dyb.platforms.fixfunds.services.business.bankaccount.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IBankAccountService {

    /**
     * 添加一张银行卡
     * @param bankAccount 银行卡对象信息
     * @return 银行卡对象信息
     */
    public BankAccount createBankAccount(BankAccount bankAccount);
}
