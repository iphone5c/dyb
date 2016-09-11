package com.dyb.platforms.fixfunds.services.business.codebuilder;

import com.dyb.platforms.fixfunds.services.business.serianum.service.ISeriaNumService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
@Transactional
@Service("codeBuilder")
public class CodeBuilder implements ICodeBuilder
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
}
