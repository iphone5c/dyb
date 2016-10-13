package com.dyb.platforms.fixfunds.services.business.bankaccount.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

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

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<BankAccount> getBankAccountList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的银行卡信息
     * @param accountCode 账户code
     * @return 银行卡信息
     */
    public BankAccount getBankAccountByDefaultChecked(String accountCode);

    /**
     * 修改银行卡新
     * @param bankAccount 银行卡对象
     * @return银行卡对象
     */
    public BankAccount updateBankAccount(BankAccount bankAccount);

    /**
     * 根据code查找银行卡信息
     * @param bankAccountCode 银行卡code
     * @return 银行卡对象
     */
    public BankAccount getBankAccountByCode(String bankAccountCode);
}
