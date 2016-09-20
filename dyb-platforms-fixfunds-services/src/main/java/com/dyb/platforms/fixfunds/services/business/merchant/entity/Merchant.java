/**
 * 2016/9/20 14:18:04 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.merchant.entity;

import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商家信息
 * Created by lenovo on 2016/09/20.
 */
public class Merchant implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -2851874908829049344L;

    // 商家Code [主键]
    private String merchantCode;
    // 商家名称
    private String merchantName;
    // 序号
    private int sequence;
    // 激励模式
    private Double incentiveMode;
    // 店铺名称
    private String shopName;
    // 商家类型
    private MerchantType merchantType;
    // 营业开始时间
    private String businessStartTime;
    // 营业结束时间
    private String businessEndTime;
    // 商家地址
    private String merchantAddress;
    // 主营业务
    private String mainBusiness;
    // 行业类别
    private IndustryType industryType;
    // 所在行业
    private Industry industry;
    // 商家规模
    private Scale scale;
    // 联系电话
    private String phone;
    // 商家简介
    private String merchantDescription;
    // 负责人姓名
    private String principalName;
    // 负责人岗位
    private String principalJobs;
    // 负责人性别
    private Sex principalSex;
    // 负责人身份证号
    private String principalIdCard;
    // 负责人邮箱地址
    private String principalEmail;
    // 负责人手机号
    private String principalPhone;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //负责人出生日期
    private Date principalBirthday;
    // 营业执照照片
    private String businessLicensePhoto;
    // 法人身份证照片
    private String legalPersonPhoto;
    // 推荐人身份证照片
    private String refereesPhoto;
    // 捐赠承诺书照片
    private String givingPledgePhoto;
    // 店面门头照照片
    private String shopFrontDoorHeadPhoto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 注册日期
    private Date createTime;
    // 登陆密码
    private String password;
    // 二次密码
    private String tradePassword;
    // 商家账户状态
    private MerchantStatus merchantStatus;
    // 商家用户名
    private String accountName;

    /**
     * 获取商家Code [主键]
     *
     * @return 商家Code
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 设置商家Code [主键]
     *
     * @param merchantCode 商家Code
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * 获取商家名称
     *
     * @return 商家名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商家名称
     *
     * @param merchantName 商家名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取序号
     *
     * @return 序号
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * 设置序号
     *
     * @param sequence 序号
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取激励模式
     *
     * @return 激励模式
     */
    public Double getIncentiveMode() {
        return incentiveMode;
    }

    /**
     * 设置激励模式
     *
     * @param incentiveMode 激励模式
     */
    public void setIncentiveMode(Double incentiveMode) {
        this.incentiveMode = incentiveMode;
    }

    /**
     * 获取店铺名称
     *
     * @return 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取商家类型
     *
     * @return 商家类型
     */
    public MerchantType getMerchantType() {
        return merchantType;
    }

    /**
     * 设置商家类型
     *
     * @param merchantType 商家类型
     */
    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * 获取营业开始时间
     *
     * @return 营业开始时间
     */
    public String getBusinessStartTime() {
        return businessStartTime;
    }

    /**
     * 设置营业开始时间
     *
     * @param businessStartTime 营业开始时间
     */
    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    /**
     * 获取营业结束时间
     *
     * @return 营业结束时间
     */
    public String getBusinessEndTime() {
        return businessEndTime;
    }

    /**
     * 设置营业结束时间
     *
     * @param businessEndTime 营业结束时间
     */
    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    /**
     * 获取商家地址
     *
     * @return 商家地址
     */
    public String getMerchantAddress() {
        return merchantAddress;
    }

    /**
     * 设置商家地址
     *
     * @param merchantAddress 商家地址
     */
    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    /**
     * 获取主营业务
     *
     * @return 主营业务
     */
    public String getMainBusiness() {
        return mainBusiness;
    }

    /**
     * 设置主营业务
     *
     * @param mainBusiness 主营业务
     */
    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    /**
     * 获取行业类别
     *
     * @return 行业类别
     */
    public IndustryType getIndustryType() {
        return industryType;
    }

    /**
     * 设置行业类别
     *
     * @param industryType 行业类别
     */
    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

    /**
     * 获取所在行业
     *
     * @return 所在行业
     */
    public Industry getIndustry() {
        return industry;
    }

    /**
     * 设置所在行业
     *
     * @param industry 所在行业
     */
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    /**
     * 获取商家规模
     *
     * @return 商家规模
     */
    public Scale getScale() {
        return scale;
    }

    /**
     * 设置商家规模
     *
     * @param scale 商家规模
     */
    public void setScale(Scale scale) {
        this.scale = scale;
    }

    /**
     * 获取联系电话
     *
     * @return 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取商家简介
     *
     * @return 商家简介
     */
    public String getMerchantDescription() {
        return merchantDescription;
    }

    /**
     * 设置商家简介
     *
     * @param merchantDescription 商家简介
     */
    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
    }

    /**
     * 获取负责人姓名
     *
     * @return 负责人姓名
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * 设置负责人姓名
     *
     * @param principalName 负责人姓名
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    /**
     * 获取负责人岗位
     *
     * @return 负责人岗位
     */
    public String getPrincipalJobs() {
        return principalJobs;
    }

    /**
     * 设置负责人岗位
     *
     * @param principalJobs 负责人岗位
     */
    public void setPrincipalJobs(String principalJobs) {
        this.principalJobs = principalJobs;
    }

    /**
     * 获取负责人性别
     *
     * @return 负责人性别
     */
    public Sex getPrincipalSex() {
        return principalSex;
    }

    /**
     * 设置负责人性别
     *
     * @param principalSex 负责人性别
     */
    public void setPrincipalSex(Sex principalSex) {
        this.principalSex = principalSex;
    }

    /**
     * 获取负责人身份证号
     *
     * @return 负责人身份证号
     */
    public String getPrincipalIdCard() {
        return principalIdCard;
    }

    /**
     * 设置负责人身份证号
     *
     * @param principalIdCard 负责人身份证号
     */
    public void setPrincipalIdCard(String principalIdCard) {
        this.principalIdCard = principalIdCard;
    }

    /**
     * 获取负责人邮箱地址
     *
     * @return 负责人邮箱地址
     */
    public String getPrincipalEmail() {
        return principalEmail;
    }

    /**
     * 设置负责人邮箱地址
     *
     * @param principalEmail 负责人邮箱地址
     */
    public void setPrincipalEmail(String principalEmail) {
        this.principalEmail = principalEmail;
    }

    /**
     * 获取负责人手机号
     *
     * @return 负责人手机号
     */
    public String getPrincipalPhone() {
        return principalPhone;
    }

    /**
     * 设置负责人手机号
     *
     * @param principalPhone 负责人手机号
     */
    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    /**
     * 获取负责人出生日期
     *
     * @return 负责人出生日期
     */
    public Date getPrincipalBirthday() {
        return principalBirthday;
    }

    /**
     * 设置负责人出生日期
     *
     * @param principalBirthday 负责人出生日期
     */
    public void setPrincipalBirthday(Date principalBirthday) {
        this.principalBirthday = principalBirthday;
    }

    /**
     * 获取营业执照照片
     *
     * @return 营业执照照片
     */
    public String getBusinessLicensePhoto() {
        return businessLicensePhoto;
    }

    /**
     * 设置营业执照照片
     *
     * @param businessLicensePhoto 营业执照照片
     */
    public void setBusinessLicensePhoto(String businessLicensePhoto) {
        this.businessLicensePhoto = businessLicensePhoto;
    }

    /**
     * 获取法人身份证照片
     *
     * @return 法人身份证照片
     */
    public String getLegalPersonPhoto() {
        return legalPersonPhoto;
    }

    /**
     * 设置法人身份证照片
     *
     * @param legalPersonPhoto 法人身份证照片
     */
    public void setLegalPersonPhoto(String legalPersonPhoto) {
        this.legalPersonPhoto = legalPersonPhoto;
    }

    /**
     * 获取推荐人身份证照片
     *
     * @return 推荐人身份证照片
     */
    public String getRefereesPhoto() {
        return refereesPhoto;
    }

    /**
     * 设置推荐人身份证照片
     *
     * @param refereesPhoto 推荐人身份证照片
     */
    public void setRefereesPhoto(String refereesPhoto) {
        this.refereesPhoto = refereesPhoto;
    }

    /**
     * 获取捐赠承诺书照片
     *
     * @return 捐赠承诺书照片
     */
    public String getGivingPledgePhoto() {
        return givingPledgePhoto;
    }

    /**
     * 设置捐赠承诺书照片
     *
     * @param givingPledgePhoto 捐赠承诺书照片
     */
    public void setGivingPledgePhoto(String givingPledgePhoto) {
        this.givingPledgePhoto = givingPledgePhoto;
    }

    /**
     * 获取店面门头照照片
     *
     * @return 店面门头照照片
     */
    public String getShopFrontDoorHeadPhoto() {
        return shopFrontDoorHeadPhoto;
    }

    /**
     * 设置店面门头照照片
     *
     * @param shopFrontDoorHeadPhoto 店面门头照照片
     */
    public void setShopFrontDoorHeadPhoto(String shopFrontDoorHeadPhoto) {
        this.shopFrontDoorHeadPhoto = shopFrontDoorHeadPhoto;
    }

    /**
     * 获取注册日期
     *
     * @return 注册日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册日期
     *
     * @param createTime 注册日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取登陆密码
     *
     * @return 登陆密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登陆密码
     *
     * @param password 登陆密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取二次密码
     *
     * @return 二次密码
     */
    public String getTradePassword() {
        return tradePassword;
    }

    /**
     * 设置二次密码
     *
     * @param tradePassword 二次密码
     */
    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    /**
     * 获取商家账户状态
     *
     * @return 商家账户状态
     */
    public MerchantStatus getMerchantStatus() {
        return merchantStatus;
    }

    /**
     * 设置商家账户状态
     *
     * @param merchantStatus 商家账户状态
     */
    public void setMerchantStatus(MerchantStatus merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    /**
     * 获取商家用户名
     *
     * @return 商家用户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置商家用户名
     *
     * @param accountName 商家用户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
