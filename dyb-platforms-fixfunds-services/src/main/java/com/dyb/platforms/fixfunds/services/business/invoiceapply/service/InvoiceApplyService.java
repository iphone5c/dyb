package com.dyb.platforms.fixfunds.services.business.invoiceapply.service;

import com.dyb.platforms.fixfunds.services.business.invoiceapply.dao.IInvoiceApplyDao;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.InvoiceApply;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("invoiceApplyService")
public class InvoiceApplyService extends BaseService implements IInvoiceApplyService {

    public Logger log = Logger.getLogger(InvoiceApplyService.class);//日志

    @Autowired
    private IInvoiceApplyDao invoiceApplyDao;

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    @Override
    public List<InvoiceApply> getInvoiceApplyList(QueryParams wheres, int skip, int size, boolean detail) {
        return invoiceApplyDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的让利款发票申请信息
     * @param invoiceApplyCode 让利款发票申请code
     * @return 让利款发票申请信息
     */
    @Override
    public InvoiceApply getInvoiceApplyByCode(String invoiceApplyCode) {
        if (DybUtils.isEmptyOrNull(invoiceApplyCode))
            throw new DybRuntimeException("根据让利款发票申请code获取让利款发票申请信息时，让利款发票申请code不能为空");
        return invoiceApplyDao.getObject(invoiceApplyCode,true);
    }

    /**
     *获取让利款发票申请分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<InvoiceApply> getInvoiceApplyPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return invoiceApplyDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
