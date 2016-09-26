package com.dyb.platforms.fixfunds.serviceproviders.controller.client.model;

import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.Industry;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;

/**
 * 服务商注册参数接受枚举
 * Created by lenovo on 2016/9/26.
 */
public class ServiceProvidersParamModel {
    private String industry;

    public Industry getIndustry() {
        Industry result=null;
        for (Industry temp:Industry.values()){
            if (temp.name().equals(this.industry)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("行业超出规定范围值");
        return result;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
