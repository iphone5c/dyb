package com.dyb.platforms.fixfunds.services.business.member.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 信使类别
 * Created by lenovo on 2016/9/20.
 */
public enum MemberType {
    /**
     * 信使
     */
    信使;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (MemberType memberType : MemberType.values()){
            result.add(NameValue.create(memberType.toString(), memberType.toString()));
        }
        return result;
    }
}
