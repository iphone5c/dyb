package com.dyb.platforms.fixfunds.common.controller.conversion.model;

import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;

/**
 * Created by lenovo on 2016/10/20.
 */
public class ConversionModel {
    //转换申请信息
    private Conversion conversion;
    //申请人姓名
    private String applyAccountName;

    public Conversion getConversion() {
        return conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
    }

    public String getApplyAccountName() {
        return applyAccountName;
    }

    public void setApplyAccountName(String applyAccountName) {
        this.applyAccountName = applyAccountName;
    }
}
