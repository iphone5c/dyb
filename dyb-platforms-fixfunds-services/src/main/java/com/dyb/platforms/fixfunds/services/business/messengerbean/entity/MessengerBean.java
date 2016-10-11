/**
 * 2016/10/10 10:42:00 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.messengerbean.entity;

import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;

/**
 * 信使豆信息
 * Created by lenovo on 2016/10/10.
 */
public class MessengerBean implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -7457060472786920448L;

    // 信使豆编号 [主键]
    private String messengerBeanCode;
    // 信使豆数量
    private Double messengerBean;
    // 信使豆类型
    private MessengerBeanType messengerBeanType;
    // 账户code
    private String accountCode;

    /** 
     * 获取信使豆编号 [主键]
     * 
     * @return 信使豆编号
     */
    public String getMessengerBeanCode() {
        return messengerBeanCode;
    }

    /** 
     * 设置信使豆编号 [主键]
     * 
     * @param messengerBeanCode 信使豆编号
     */
    public void setMessengerBeanCode(String messengerBeanCode) {
        this.messengerBeanCode = messengerBeanCode;
    }

    /** 
     * 获取信使豆数量
     * 
     * @return 信使豆数量
     */
    public Double getMessengerBean() {
        return messengerBean;
    }

    /** 
     * 设置信使豆数量
     * 
     * @param messengerBean 信使豆数量
     */
    public void setMessengerBean(Double messengerBean) {
        this.messengerBean = messengerBean;
    }

    /** 
     * 获取信使豆类型
     * 
     * @return 信使豆类型
     */
    public MessengerBeanType getMessengerBeanType() {
        return messengerBeanType;
    }

    /** 
     * 设置信使豆类型
     * 
     * @param messengerBeanType 信使豆类型
     */
    public void setMessengerBeanType(MessengerBeanType messengerBeanType) {
        this.messengerBeanType = messengerBeanType;
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
