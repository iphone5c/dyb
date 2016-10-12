package com.dyb.platforms.fixfunds.merchant.controller.web.model;

import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.*;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;

/**
 * 商户注册参数接受
 * Created by lenovo on 2016/9/26.
 */
public class MerchantParamModel {
    private String merchantType;
    private String industryType;
    private String industry;
    private String scale;
    private String principalSex;
    //执照类型1：表示新版  2：表示旧版
    private int flag=1;
    //营业执照第一张
    private String businessLicensePhoto1;
    //营业执照第二张
    private String businessLicensePhoto2;
    //法人身份证照片
    private String legalPersonPhoto;
    //推荐人身份证照片
    private String recommendPersonPhoto;
    //捐赠承诺书照片
    private String donationPhoto;
    //店面门头照照片
    private String storePhoto;

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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getBusinessLicensePhoto1() {
        return businessLicensePhoto1;
    }

    public void setBusinessLicensePhoto1(String businessLicensePhoto1) {
        this.businessLicensePhoto1 = businessLicensePhoto1;
    }

    public String getBusinessLicensePhoto2() {
        return businessLicensePhoto2;
    }

    public void setBusinessLicensePhoto2(String businessLicensePhoto2) {
        this.businessLicensePhoto2 = businessLicensePhoto2;
    }

    public String getLegalPersonPhoto() {
        return legalPersonPhoto;
    }

    public void setLegalPersonPhoto(String legalPersonPhoto) {
        this.legalPersonPhoto = legalPersonPhoto;
    }

    public String getRecommendPersonPhoto() {
        return recommendPersonPhoto;
    }

    public void setRecommendPersonPhoto(String recommendPersonPhoto) {
        this.recommendPersonPhoto = recommendPersonPhoto;
    }

    public String getDonationPhoto() {
        return donationPhoto;
    }

    public void setDonationPhoto(String donationPhoto) {
        this.donationPhoto = donationPhoto;
    }

    public String getStorePhoto() {
        return storePhoto;
    }

    public void setStorePhoto(String storePhoto) {
        this.storePhoto = storePhoto;
    }
}
