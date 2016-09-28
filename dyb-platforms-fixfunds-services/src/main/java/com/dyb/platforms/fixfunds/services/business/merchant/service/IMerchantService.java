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

    /**
     * 根据商家code更新商家地理位置
     * @param merchantCode 商家code
     * @param address 地址
     * @param longitude 经度
     * @param latitude 纬度
     * @return 商家信息
     */
    public Merchant updateMerchantAddressByCode(String merchantCode,String address,String longitude,String latitude);

    /**
     * 根据商家code 修改商家信息
     * @param merchant 商家对象
     * @return 商家对象
     */
    public Merchant updateMerchantByCode(Merchant merchant);
}
