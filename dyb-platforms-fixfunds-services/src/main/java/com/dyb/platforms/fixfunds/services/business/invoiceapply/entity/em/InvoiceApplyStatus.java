package com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请状态
 * Created by lenovo on 2016/10/10.
 */
public enum InvoiceApplyStatus {
    /**
     * 未申请
     */
    未申请,
    /**
     * 待审核
     */
    待审核,
    /**
     * 待开发票
     */
    待开发票,
    /**
     * 已开发票
     */
    已开发票,
    /**
     * 审批不通过
     */
    审批不通过;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (InvoiceApplyStatus invoiceApplyStatus : InvoiceApplyStatus.values()){
            result.add(NameValue.create(invoiceApplyStatus.toString(), invoiceApplyStatus.toString()));
        }
        return result;
    }
}
