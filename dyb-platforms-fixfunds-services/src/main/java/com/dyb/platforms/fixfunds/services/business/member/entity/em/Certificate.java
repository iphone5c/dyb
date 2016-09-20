package com.dyb.platforms.fixfunds.services.business.member.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 证件类型
 * Created by lenovo on 2016/9/20.
 */
public enum Certificate {
    /**
     * 大陆身份证
     */
    大陆身份证,
    /**
     * 境外身份证
     */
    境外身份证,
    /**
     * 境外护照
     */
    境外护照,
    /**
     * 境外回乡证
     */
    境外回乡证;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Certificate certificate : Certificate.values()){
            result.add(NameValue.create(certificate.toString(), certificate.toString()));
        }
        return result;
    }
}
