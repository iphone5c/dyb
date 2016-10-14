package com.dyb.platforms.fixfunds.services.business.donationreceiptapply.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/14.
 */
public enum  ApplyStatus {
    /**
     * 待初审
     */
    待初审,
    /**
     * 待复审
     */
    待复审,
    /**
     * 初审不通过
     */
    初审不通过,
    /**
     * 已开收据
     */
    已开收据,
    /**
     * 复审不通过
     */
    复审不通过;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (ApplyStatus applyStatus : ApplyStatus.values()){
            result.add(NameValue.create(applyStatus.toString(), applyStatus.toString()));
        }
        return result;
    }
}
