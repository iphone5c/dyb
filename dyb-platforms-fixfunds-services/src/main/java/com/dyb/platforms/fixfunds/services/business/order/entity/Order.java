/**
 * 2016/9/27 19:51:28 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.order.entity;

import com.dyb.platforms.fixfunds.services.business.order.entity.em.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 订单信息
 * Created by lenovo on 2016/09/27.
 */
public class Order implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -5272722633216866304L;

    // 订单编号 [主键]
    private String orderCode;
    // 信使编号
    private String memberCode;
    // 商家Code
    private String merchantCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 交易时间
    private Date tradeTime;
    // 交易总价
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 创建时间
    private Date createTime;
    // 订单状态
    private OrderStatus status;
    // 资料照片
    private String certificateFile;
    // 激励模式
    private Integer incentiveMode;

    /** 
     * 获取订单编号 [主键]
     * 
     * @return 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /** 
     * 设置订单编号 [主键]
     * 
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /** 
     * 获取信使编号
     * 
     * @return 信使编号
     */
    public String getMemberCode() {
        return memberCode;
    }

    /** 
     * 设置信使编号
     * 
     * @param memberCode 信使编号
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    /** 
     * 获取商家Code
     * 
     * @return 商家Code
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /** 
     * 设置商家Code
     * 
     * @param merchantCode 商家Code
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /** 
     * 获取交易时间
     * 
     * @return 交易时间
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /** 
     * 设置交易时间
     * 
     * @param tradeTime 交易时间
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    /** 
     * 获取交易总价
     * 
     * @return 交易总价
     */
    public Double getPrice() {
        return price;
    }

    /** 
     * 设置交易总价
     * 
     * @param price 交易总价
     */
    public void setPrice(Double price) {
        this.price = price;
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
     * 获取订单状态
     * 
     * @return 订单状态
     */
    public OrderStatus getStatus() {
        return status;
    }

    /** 
     * 设置订单状态
     * 
     * @param status 订单状态
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /** 
     * 获取资料照片
     * 
     * @return 资料照片
     */
    public String getCertificateFile() {
        return certificateFile;
    }

    /** 
     * 设置资料照片
     * 
     * @param certificateFile 资料照片
     */
    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    /**
     * 获取激励模式
     *
     * @return 激励模式
     */
    public Integer getIncentiveMode() {
        return incentiveMode;
    }

    /**
     * 设置激励模式
     *
     * @param incentiveMode 激励模式
     */
    public void setIncentiveMode(Integer incentiveMode) {
        this.incentiveMode = incentiveMode;
    }
}
