package com.dyb.platforms.fixfunds.services.business.merchant.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.recommended.entity.em.AccountType;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IMerchantService {

    /**
     * 商户信息链接注册
     * @param merchant 商家账户对象
     * @param bankAccount 银行账号信息
     * @param tjrCode 推荐人的code
     * @param tjrType 推荐人类型
     * @return 商家账户对象
     */
    public Merchant createMerchantRegistered(Merchant merchant,BankAccount bankAccount,String tjrCode,AccountType tjrType);

    /**
     * 判断指定手机号是否已经注册
     * @param phone 注册手机号
     * @return true表示存在 false表示不存在
     */
    public Boolean isExistLoginPhone(String phone);

    /**
     * 根据商家code查找指定商家信息
     * @param merchantCode 商家code
     * @return 商家信息
     */
    public Merchant getMerchantByCode(String merchantCode);
}
