package com.dyb.platforms.fixfunds.services.business.merchant.service;

import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IMerchantService {

    /**
     * 添加商家信息
     * @param merchant 商家账户对象
     * @return 商家账户对象
     */
    public Merchant createMerchant(Merchant merchant);

    /**
     * 根据商家code查找指定商家信息
     * @param merchantCode 商家code
     * @return 商家信息
     */
    public Merchant getMerchantByCode(String merchantCode);
}
