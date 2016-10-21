package com.dyb.platforms.fixfunds.services.business.account.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户类型
 * Created by lenovo on 2016/9/20.
 */
public enum AccountType {
    /**
     * 商家
     */
    商家,
    /**
     * 信使
     */
    信使,
    /**
     * 服务商
     */
    服务商,
    /**
     * 业务员
     */
    业务员;

    public static AccountType getAccountTypeByName(String name){
        AccountType result = null;
        for (AccountType accountType : AccountType.values()){
            if (name.equals(accountType.name())){
                result=accountType;
            }
        }
        if (result==null)
            throw new DybRuntimeException("账户类型超出规定范围值");
        return result;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (AccountType accountType : AccountType.values()){
            result.add(NameValue.create(accountType.toString(), accountType.toString()));
        }
        return result;
    }
}
