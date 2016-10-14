package com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.service;


import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.entity.ConversionInvoiceDetails;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IConversionInvoiceDetailsService {

    /**
     * 根据code查询转换发票明细信息
     * @param conversionInvoiceDetailsCode 转换发票明细Code
     * @return 转换发票明细信息
     */
    public ConversionInvoiceDetails getConversionInvoiceDetailsByCode(String conversionInvoiceDetailsCode);

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<ConversionInvoiceDetails> getConversionInvoiceDetailsList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 新增转换发票明细
     * @param conversionCode 转换记录code
     * @param conversionInvoiceDetailses 转换发票明细
     * @return true 表示操作成功 false表示操作失败
     */
    public boolean createConversionInvoiceDetails(String conversionCode,List<ConversionInvoiceDetails> conversionInvoiceDetailses);

}
