package com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请状态
 * Created by lenovo on 2016/10/10.
 */
public enum ApplyStatus {
    /**
     * 未审核
     */
    未审核,
    /**
     * 审批通过
     */
    审批通过,
    /**
     * 审批不通过
     */
    审批不通过;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (ApplyStatus applyStatus : ApplyStatus.values()){
            result.add(NameValue.create(applyStatus.toString(), applyStatus.toString()));
        }
        return result;
    }
}
