package com.dyb.platforms.fixfunds.member.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.member.entity.em.Certificate;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.*;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;

/**
 * 信使注册参数接受枚举
 * Created by lenovo on 2016/9/26.
 */
public class MemberParamModel {
    private String sex;
    private String certificate;
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

    public Sex getSex() {
        Sex result=null;
        for (Sex temp:Sex.values()){
            if (temp.name().equals(this.sex)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("性别超出规定范围值");
        return result;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Certificate getCertificate() {
        Certificate result=null;
        for (Certificate temp:Certificate.values()){
            if (temp.name().equals(this.certificate)){
                result=temp;
            }
        }
        if (result==null)
            throw new DybRuntimeException("证件类型超出规定范围值");
        return result;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
