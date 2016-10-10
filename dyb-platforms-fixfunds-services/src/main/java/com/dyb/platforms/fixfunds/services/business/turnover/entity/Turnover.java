/**
 * 2016/9/27 19:52:14 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.turnover.entity;

import com.dyb.platforms.fixfunds.services.business.turnover.entity.em.BenefitPriceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 营业额
 * Created by lenovo on 2016/09/27.
 */
public class Turnover implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -3948892370773894656L;

    // 营业额编号 [主键]
    private String turnoverCode;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 营业时间
    private Date turnoverTime;
    // 营业总额
    private Double turnoverPrice;
    // 应交让利款
    private Double benefitPrice;
    // 已交让利款
    private Double yetBenefitPrice;
    // 剩余待交让利款
    private Double residueBenefitPrice;
    // 让利状态
    private BenefitPriceStatus benefitPriceStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 创建时间
    private Date createTime;
    // 备注
    private String remark;
    // 账户code
    private String accountCode;

    /** 
     * 获取营业额编号 [主键]
     * 
     * @return 营业额编号
     */
    public String getTurnoverCode() {
        return turnoverCode;
    }

    /** 
     * 设置营业额编号 [主键]
     * 
     * @param turnoverCode 营业额编号
     */
    public void setTurnoverCode(String turnoverCode) {
        this.turnoverCode = turnoverCode;
    }

    /** 
     * 获取营业时间
     * 
     * @return 营业时间
     */
    public Date getTurnoverTime() {
        return turnoverTime;
    }

    /** 
     * 设置营业时间
     * 
     * @param turnoverTime 营业时间
     */
    public void setTurnoverTime(Date turnoverTime) {
        this.turnoverTime = turnoverTime;
    }

    /** 
     * 获取营业总额
     * 
     * @return 营业总额
     */
    public Double getTurnoverPrice() {
        return turnoverPrice;
    }

    /** 
     * 设置营业总额
     * 
     * @param turnoverPrice 营业总额
     */
    public void setTurnoverPrice(Double turnoverPrice) {
        this.turnoverPrice = turnoverPrice;
    }

    /** 
     * 获取应交让利款
     * 
     * @return 应交让利款
     */
    public Double getBenefitPrice() {
        return benefitPrice;
    }

    /** 
     * 设置应交让利款
     * 
     * @param benefitPrice 应交让利款
     */
    public void setBenefitPrice(Double benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

    /** 
     * 获取已交让利款
     * 
     * @return 已交让利款
     */
    public Double getYetBenefitPrice() {
        return yetBenefitPrice;
    }

    /** 
     * 设置已交让利款
     * 
     * @param yetBenefitPrice 已交让利款
     */
    public void setYetBenefitPrice(Double yetBenefitPrice) {
        this.yetBenefitPrice = yetBenefitPrice;
    }

    /** 
     * 获取剩余待交让利款
     * 
     * @return 剩余待交让利款
     */
    public Double getResidueBenefitPrice() {
        return residueBenefitPrice;
    }

    /** 
     * 设置剩余待交让利款
     * 
     * @param residueBenefitPrice 剩余待交让利款
     */
    public void setResidueBenefitPrice(Double residueBenefitPrice) {
        this.residueBenefitPrice = residueBenefitPrice;
    }

    /** 
     * 获取让利状态
     * 
     * @return 让利状态
     */
    public BenefitPriceStatus getBenefitPriceStatus() {
        return benefitPriceStatus;
    }

    /** 
     * 设置让利状态
     * 
     * @param benefitPriceStatus 让利状态
     */
    public void setBenefitPriceStatus(BenefitPriceStatus benefitPriceStatus) {
        this.benefitPriceStatus = benefitPriceStatus;
    }

    /** 
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置创建时间
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /** 
     * 设置备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取账户code
     *
     * @return 账户code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 设置账户code
     *
     * @param accountCode 账户code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
