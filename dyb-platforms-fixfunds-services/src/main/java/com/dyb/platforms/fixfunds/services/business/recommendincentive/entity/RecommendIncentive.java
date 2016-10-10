/**
 * 2016/10/10 10:35:19 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.recommendincentive.entity;

import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 推荐激励信息
 * Created by lenovo on 2016/10/10.
 */
public class RecommendIncentive implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -4455416982274231296L;

    // 推荐编号code [主键]
    private String recommendIncentiveCode;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 激励日期
    private Date recommendIncentiveTime;
    // 被推荐人code
    private String recommendAccountCode;
    // 激励信使豆
    private Double messengerBean;
    // 激励类型
    private Integer incentiveType;
    // 激励来源
    private AccountType incentiveSources;
    // 推荐人账户code
    private String accountCode;

    /** 
     * 获取推荐编号code [主键]
     * 
     * @return 推荐编号code
     */
    public String getRecommendIncentiveCode() {
        return recommendIncentiveCode;
    }

    /** 
     * 设置推荐编号code [主键]
     * 
     * @param recommendIncentiveCode 推荐编号code
     */
    public void setRecommendIncentiveCode(String recommendIncentiveCode) {
        this.recommendIncentiveCode = recommendIncentiveCode;
    }

    /** 
     * 获取激励日期
     * 
     * @return 激励日期
     */
    public Date getRecommendIncentiveTime() {
        return recommendIncentiveTime;
    }

    /** 
     * 设置激励日期
     * 
     * @param recommendIncentiveTime 激励日期
     */
    public void setRecommendIncentiveTime(Date recommendIncentiveTime) {
        this.recommendIncentiveTime = recommendIncentiveTime;
    }

    /** 
     * 获取被推荐人code
     * 
     * @return 被推荐人code
     */
    public String getRecommendAccountCode() {
        return recommendAccountCode;
    }

    /** 
     * 设置被推荐人code
     * 
     * @param recommendAccountCode 被推荐人code
     */
    public void setRecommendAccountCode(String recommendAccountCode) {
        this.recommendAccountCode = recommendAccountCode;
    }

    /** 
     * 获取激励信使豆
     * 
     * @return 激励信使豆
     */
    public Double getMessengerBean() {
        return messengerBean;
    }

    /** 
     * 设置激励信使豆
     * 
     * @param messengerBean 激励信使豆
     */
    public void setMessengerBean(Double messengerBean) {
        this.messengerBean = messengerBean;
    }

    /** 
     * 获取激励类型
     * 
     * @return 激励类型
     */
    public Integer getIncentiveType() {
        return incentiveType;
    }

    /** 
     * 设置激励类型
     * 
     * @param incentiveType 激励类型
     */
    public void setIncentiveType(Integer incentiveType) {
        this.incentiveType = incentiveType;
    }

    /** 
     * 获取激励来源
     * 
     * @return 激励来源
     */
    public AccountType getIncentiveSources() {
        return incentiveSources;
    }

    /** 
     * 设置激励来源
     * 
     * @param incentiveSources 激励来源
     */
    public void setIncentiveSources(AccountType incentiveSources) {
        this.incentiveSources = incentiveSources;
    }

    /** 
     * 获取推荐人账户code
     * 
     * @return 推荐人账户code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /** 
     * 设置推荐人账户code
     * 
     * @param accountCode 推荐人账户code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
