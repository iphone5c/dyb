/**
 * 2016/9/26 9:16:20 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.member.entity;

import com.dyb.platforms.fixfunds.services.business.member.entity.em.Certificate;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.Industry;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 信使信息
 * Created by lenovo on 2016/09/26.
 */
public class Member implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -1003698679154598528L;

    // 信使编号code [主键]
    private String memberCode;
    // 真实姓名
    private String realName;
    // 性别
    private Sex sex;
    // 籍贯
    private String nativePlace;
    // 省级代码
    private String province;
    // 市级代码
    private String city;
    // 证件类型
    private Certificate certificate;
    // 证件号码
    private String certificateNumber;
    // 所在行业
    private Industry industry;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 生日
    private Date birthday;
    // 个人邮箱
    private String memberEmail;
    // 账户Code
    private String accountCode;

    /**
     * 获取信使编号code [主键]
     *
     * @return 信使编号code
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 设置信使编号code [主键]
     *
     * @param memberCode 信使编号code
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    /**
     * 获取真实姓名
     *
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * 获取籍贯
     *
     * @return 籍贯
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace 籍贯
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取省级代码
     *
     * @return 省级代码
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省级代码
     *
     * @param province 省级代码
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市级代码
     *
     * @return 市级代码
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市级代码
     *
     * @param city 市级代码
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取证件类型
     *
     * @return 证件类型
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * 设置证件类型
     *
     * @param certificate 证件类型
     */
    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    /**
     * 获取证件号码
     *
     * @return 证件号码
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * 设置证件号码
     *
     * @param certificateNumber 证件号码
     */
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
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
     * 获取生日
     *
     * @return 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取个人邮箱
     *
     * @return 个人邮箱
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    /**
     * 设置个人邮箱
     *
     * @param memberEmail 个人邮箱
     */
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    /**
     * 获取账户Code
     *
     * @return 账户Code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 设置账户Code
     *
     * @param accountCode 账户Code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
