package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.*;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;

/**
 * 商户注册参数接受枚举
 * Created by lenovo on 2016/9/26.
 */
public class MerchantParamModel {
    private String merchantType;
    private String industryType;
    private String industry;
    private String scale;
    private String principalSex;

    public MerchantType getMerchantType() {
        MerchantType result=null;
        for (MerchantType temp:MerchantType.values()){
            if (temp.name().equals(this.merchantType)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("商家类型超出规定范围值");
        return result;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public IndustryType getIndustryType() {
        IndustryType result=null;
        for (IndustryType temp:IndustryType.values()){
            if (temp.name().equals(this.industryType)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("行业类别超出规定范围值");
        return result;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

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

    public Scale getScale() {
        Scale result=null;
        for (Scale temp:Scale.values()){
            if (temp.name().equals(this.scale)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("规模超出规定范围值");
        return result;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Sex getPrincipalSex() {
        Sex result=null;
        for (Sex temp:Sex.values()){
            if (temp.name().equals(this.principalSex)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("性别超出规定范围值");
        return result;
    }

    public void setPrincipalSex(String principalSex) {
        this.principalSex = principalSex;
    }
}
