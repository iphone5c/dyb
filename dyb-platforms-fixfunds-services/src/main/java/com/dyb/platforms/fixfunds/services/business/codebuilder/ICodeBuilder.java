package com.dyb.platforms.fixfunds.services.business.codebuilder;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
public interface ICodeBuilder {
    /**
     * 获取一个新的用户编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户编码
     */
    String getUserCode();

    /**
     * 获取一个新的系统参数配置编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的系统参数配置编码
     */
    String getSystemParamsCode();

    /**
     * 获取一个新的用户日志编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户日志编码
     */
    String getUserLogCode();

    /**
     * 获取一个新的账户编码（规则：两位随机字母+年月日时分秒+4位序列号）
     * @return 新的账户编码
     */
    String getAccountCode();

    /**
     * 获取一个新的订单编码（规则：年月日时分秒+4位序列号）
     * @return 新的订单编码
     */
    String getOrderCode();

    /**
     * 获取一个新的直捐编码（规则：年月日时分秒+6位序列号）
     * @return 新的直捐编码
     */
    String getDonationCode();

    /**
     * 获取一个新的收据申请编码（规则：年月日时分秒+6位序列号）
     * @return 新的收据申请编码
     */
    String getDonationReceiptApplyCode();

    /**
     * 获取一个新的转换信使豆申请编码（规则：年月日时分秒+6位序列号）
     * @return 新的转换信使豆申请
     */
    String getConversionCode();

    /**
     * 获取一个新的回购申请编码（规则：年月日时分秒+6位序列号）
     * @return 新的回购申请
     */
    String getWithdrawalCode();

}
