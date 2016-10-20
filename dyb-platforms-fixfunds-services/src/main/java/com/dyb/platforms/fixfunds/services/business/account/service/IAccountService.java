package com.dyb.platforms.fixfunds.services.business.account.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.salesman.entity.Salesman;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

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
     * @param referrerCode 推荐人Code
     * @return 账户信息
     */
    public Account registerMember(Account account,Member member ,String referrerCode);

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
     * 注册业务员
     * @param account 账户对象
     * @param salesman 业务员对象
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    public Account registerSalesman(Account account,Salesman salesman ,String referrerCode);

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

    /**
     * 商家资料修改
     * @param account 账户信息
     * @param merchant 商家详情
     * @param bankAccount 默认银行卡信息
     * @return 账户对象
     */
    public Account updateMerchant(Account account,Merchant merchant,BankAccount bankAccount);

    /**
     * 根据账户code或phone查找账户信息
     * @param key 账户code或者phone
     * @param accountType 账户类型
     * @return 账户信息
     */
    public Account getAccountByCodeOrPhone(String key,AccountType accountType);

    /**
     *获取账户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Account> getAccountPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 修改用户登录密码
     * @param accountCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    public boolean modifyPassword(String accountCode,String oldPassword,String newPassword,String confirmPassword);

    /**
     * 禁用指定用户
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableAccount(String accountCode);

    /**
     * 将指定用户解除禁用
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean removeDisableAccount(String accountCode);

    /**
     * 操作指定用户额状态
     * @param accountCode 用户code
     * @param accountStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationAccountStatus(String accountCode,AccountStatus accountStatus);

    /**
     * 将指定用户重置密码
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean resetAccountPassword(String accountCode);

    /**
     * 将指定用户重置密码
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean resetAccountTradePassword(String accountCode);
}
