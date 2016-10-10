package com.dyb.platforms.fixfunds.services.business.invoiceapply.service;

import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.InvoiceApply;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IInvoiceApplyService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<InvoiceApply> getInvoiceApplyList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的让利款发票申请信息
     * @param invoiceApplyCode 让利款发票申请code
     * @return 让利款发票申请信息
     */
    public InvoiceApply getInvoiceApplyByCode(String invoiceApplyCode);

    /**
     *获取让利款发票申请分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<InvoiceApply> getInvoiceApplyPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);
}
