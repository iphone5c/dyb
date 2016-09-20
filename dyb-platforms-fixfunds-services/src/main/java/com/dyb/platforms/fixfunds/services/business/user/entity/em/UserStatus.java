package com.dyb.platforms.fixfunds.services.business.user.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户状态枚举
 * Created by lenovo on 2016/9/11.
 */
public enum UserStatus {
    /**
     *
     */
    正常,
    /**
     *
     */
    禁用;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (UserStatus userStatus : UserStatus.values()){
            result.add(NameValue.create(userStatus.toString(),userStatus.toString()));
        }
        return result;
    }
}
