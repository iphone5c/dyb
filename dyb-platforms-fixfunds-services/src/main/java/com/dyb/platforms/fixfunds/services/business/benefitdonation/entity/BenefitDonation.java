/**
 * 2016/10/14 17:20:42 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.benefitdonation.entity;

import java.util.Date;

/**
 * 让利款捐赠
 * Created by lenovo on 2016/10/14.
 */
public class BenefitDonation implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -2981932527842899968L;

    // 让利捐赠编号code [主键]
    private String benefitDonationCode;
    // 让利捐赠日期
    private Date donationTime;
    // 让利捐赠信使豆
    private Double donationMessengerBean;
    // 创建日期
    private Date createTime;
    // 让利捐赠人code
    private String donationAccount;

    /** 
     * 获取让利捐赠编号code [主键]
     * 
     * @return 让利捐赠编号code
     */
    public String getBenefitDonationCode() {
        return benefitDonationCode;
    }

    /** 
     * 设置让利捐赠编号code [主键]
     * 
     * @param benefitDonationCode 让利捐赠编号code
     */
    public void setBenefitDonationCode(String benefitDonationCode) {
        this.benefitDonationCode = benefitDonationCode;
    }

    /** 
     * 获取让利捐赠日期
     * 
     * @return 让利捐赠日期
     */
    public Date getDonationTime() {
        return donationTime;
    }

    /** 
     * 设置让利捐赠日期
     * 
     * @param donationTime 让利捐赠日期
     */
    public void setDonationTime(Date donationTime) {
        this.donationTime = donationTime;
    }

    /** 
     * 获取让利捐赠信使豆
     * 
     * @return 让利捐赠信使豆
     */
    public Double getDonationMessengerBean() {
        return donationMessengerBean;
    }

    /** 
     * 设置让利捐赠信使豆
     * 
     * @param donationMessengerBean 让利捐赠信使豆
     */
    public void setDonationMessengerBean(Double donationMessengerBean) {
        this.donationMessengerBean = donationMessengerBean;
    }

    /** 
     * 获取创建日期
     * 
     * @return 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置创建日期
     * 
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取让利捐赠人code
     * 
     * @return 让利捐赠人code
     */
    public String getDonationAccount() {
        return donationAccount;
    }

    /** 
     * 设置让利捐赠人code
     * 
     * @param donationAccount 让利捐赠人code
     */
    public void setDonationAccount(String donationAccount) {
        this.donationAccount = donationAccount;
    }

}
