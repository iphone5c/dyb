package com.dyb.platforms.fixfunds.services.business.account.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IAccountService {

    /**
     * 根据账户code查找账户信息
     * @param mccountCode 账户code
     * @param detail 是否详情
     * @return 账户信息
     */
    public Account getAccountByCode(String mccountCode,boolean detail);

    /**
     * 添加账户信息
     * @param account 账户对象
     * @return 账户信息
     */
    public Account createAccount(Account account);

    /**
     * 注册信使
     * @param account 账户对象
     * @param member 信使对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人Code
     * @return 账户信息
     */
    public Account registerMember(Account account,Member member,BankAccount bankAccount,String referrerCode);

    /**
     * 注册商家
     * @param account 账户对象
     * @param merchant 商家对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    public Account registerMerchant(Account account,Merchant merchant,BankAccount bankAccount,String referrerCode);

    /**
     * 注册服务商
     * @param account 账户对象
     * @param serviceProviders 服务商对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    public Account registerServiceProviders(Account account,ServiceProviders serviceProviders,BankAccount bankAccount,String referrerCode);

    /**
     * 验证绑定手机号是否存在
     * @param phone 手机号
     * @param accountType 账户类型
     * @return true表示存在 false表示不存在
     */
    public boolean isExistPhone(String phone,AccountType accountType);

    /**
     * 移动端登陆
     * @param accountCode 账户Code或者绑定手机
     * @param password 账户密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    public Account loginAccountForClient(String accountCode,String password,AccountType accountType);

}
