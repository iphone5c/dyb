package com.dyb.platforms.fixfunds.services.business.codebuilder;

import com.dyb.platforms.fixfunds.services.business.serianum.service.ISeriaNumService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
@Transactional
@Service("codeBuilder")
public class CodeBuilder extends BaseService implements ICodeBuilder
{

    @Autowired
    private ISeriaNumService seriaNumService;

    private String fillLeftToString(int value, int len) {
        return fillLeftToString(DybConvert.intToString(value), len);
    }

    private String fillLeftToString(String value, int len) {
        StringBuilder result = new StringBuilder();
        result.append(value);
        while (result.length() < len)
            result.insert(0, '0');
        return result.toString();
    }

    private int getSignBit(StringBuilder builder) {
        int result = 0;
        for (int i = 0; i < builder.length(); i++)
            result += DybConvert.toInt(DybConvert.charToString(builder.charAt(i)));
        return result % 10;
    }

    /**
     * 获取一个新的用户编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户编码
     */
    @Override
    public String getUserCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.USER_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的系统参数配置编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的系统参数配置编码
     */
    @Override
    public String getSystemParamsCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.SYSTEMPARAMS_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的权限编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的权限编码
     */
    @Override
    public String getPermissionsCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.PERMISSIONS_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的角色编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的角色编码
     */
    @Override
    public String getRoleCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.ROLE_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的用户日志编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户日志编码
     */
    @Override
    public String getUserLogCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.USERLOG_CODE.name(),6));
        return builder.toString();
    }

    /**
     * 获取一个新的商户账户编码(规则：两位随机大写字母+4位序列号)
     * @return 新的商户账户编码
     */
    @Override
    public String getMerchantCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybUtils.getRandomLetter(2));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.MERCHANT_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的银行卡编码(规则：年月日时分秒+4位序列号)
     * @return 新的银行卡编码
     */
    @Override
    public String getBankAccountCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.BANKACCOUNT_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的推荐记录编码(规则：年月日时分秒+4位序列号)
     * @return 新的推荐记录编码
     */
    @Override
    public String getRecommendedCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.RECOMMENDED_CODE.name(),8));
        return builder.toString();
    }
}
