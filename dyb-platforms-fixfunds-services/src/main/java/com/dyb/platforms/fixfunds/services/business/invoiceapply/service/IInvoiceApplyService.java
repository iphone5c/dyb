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

    /**
     * 申请发票
     * @param invoiceApplyCode 发票申请code
     * @param countryPhone 公司座机
     * @param taxpayers 纳税人识别号
     * @param bankAccountCode 银行编号
     * @param sendAddressCode 寄送地址编号
     * @return true表示操作成功 false表示操作失败
     */
    public boolean invoiceApply(String invoiceApplyCode,String countryPhone,String taxpayers,String bankAccountCode,String sendAddressCode);
}
