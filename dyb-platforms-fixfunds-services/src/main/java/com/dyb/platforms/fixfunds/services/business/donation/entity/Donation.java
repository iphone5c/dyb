/**
 * 2016/10/10 10:43:46 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.donation.entity;

import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 直捐
 * Created by lenovo on 2016/10/10.
 */
public class Donation implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -2226815480844497408L;

    // 直捐编号code [主键]
    private String donationCode;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 直捐日期
    private Date donationTime;
    // 直捐信使豆
    private Double donationMessengerBean;
    // 信使豆类型
    private MessengerBeanType donationType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 创建日期
    private Date createTime;
    // 直捐人code
    private String donationAccount;

    /** 
     * 获取直捐编号code [主键]
     * 
     * @return 直捐编号code
     */
    public String getDonationCode() {
        return donationCode;
    }

    /** 
     * 设置直捐编号code [主键]
     * 
     * @param donationCode 直捐编号code
     */
    public void setDonationCode(String donationCode) {
        this.donationCode = donationCode;
    }

    /** 
     * 获取直捐日期
     * 
     * @return 直捐日期
     */
    public Date getDonationTime() {
        return donationTime;
    }

    /** 
     * 设置直捐日期
     * 
     * @param donationTime 直捐日期
     */
    public void setDonationTime(Date donationTime) {
        this.donationTime = donationTime;
    }

    /** 
     * 获取直捐信使豆
     * 
     * @return 直捐信使豆
     */
    public Double getDonationMessengerBean() {
        return donationMessengerBean;
    }

    /** 
     * 设置直捐信使豆
     * 
     * @param donationMessengerBean 直捐信使豆
     */
    public void setDonationMessengerBean(Double donationMessengerBean) {
        this.donationMessengerBean = donationMessengerBean;
    }

    /** 
     * 获取信使豆类型
     * 
     * @return 信使豆类型
     */
    public MessengerBeanType getDonationType() {
        return donationType;
    }

    /** 
     * 设置信使豆类型
     * 
     * @param donationType 信使豆类型
     */
    public void setDonationType(MessengerBeanType donationType) {
        this.donationType = donationType;
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
     * 获取直捐人code
     * 
     * @return 直捐人code
     */
    public String getDonationAccount() {
        return donationAccount;
    }

    /** 
     * 设置直捐人code
     * 
     * @param donationAccount 直捐人code
     */
    public void setDonationAccount(String donationAccount) {
        this.donationAccount = donationAccount;
    }

}
