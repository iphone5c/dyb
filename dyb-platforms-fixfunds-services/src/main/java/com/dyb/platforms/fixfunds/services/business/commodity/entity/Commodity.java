/**
 * 2016/9/27 19:46:15 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.commodity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品信息
 * Created by lenovo on 2016/09/27.
 */
public class Commodity implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -2120482978467123200L;

    // 商品编号code [主键]
    private String commodityCode;
    // 商品名称
    private String name;
    // 商品编号
    private String commodityNum;
    // 型号/规格
    private String specifications;
    // 单价
    private double price;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 账户code（关联商家）
    private String accountCode;

    /** 
     * 获取商品编号code [主键]
     * 
     * @return 商品编号code
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /** 
     * 设置商品编号code [主键]
     * 
     * @param commodityCode 商品编号code
     */
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    /** 
     * 获取商品名称
     * 
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置商品名称
     * 
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * 获取商品编号
     * 
     * @return 商品编号
     */
    public String getCommodityNum() {
        return commodityNum;
    }

    /** 
     * 设置商品编号
     * 
     * @param commodityNum 商品编号
     */
    public void setCommodityNum(String commodityNum) {
        this.commodityNum = commodityNum;
    }

    /** 
     * 获取型号/规格
     * 
     * @return 型号/规格
     */
    public String getSpecifications() {
        return specifications;
    }

    /** 
     * 设置型号/规格
     * 
     * @param specifications 型号/规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /** 
     * 获取单价
     * 
     * @return 单价
     */
    public double getPrice() {
        return price;
    }

    /** 
     * 设置单价
     * 
     * @param price 单价
     */
    public void setPrice(double price) {
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
     * 获取账户code（关联商家）
     * 
     * @return 账户code（关联商家）
     */
    public String getAccountCode() {
        return accountCode;
    }

    /** 
     * 设置账户code（关联商家）
     * 
     * @param accountCode 账户code（关联商家）
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
