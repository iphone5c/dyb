package com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.service;

import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.dao.IConversionInvoiceDetailsDao;
import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.entity.ConversionInvoiceDetails;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("conversionInvoiceDetailsService")
public class ConversionInvoiceDetailsService extends BaseService implements IConversionInvoiceDetailsService {

    public Logger log = Logger.getLogger(ConversionInvoiceDetailsService.class);//日志

    @Autowired
    private IConversionInvoiceDetailsDao conversionInvoiceDetailsDao;

    /**
     * 根据code查询转换发票明细信息
     * @param conversionInvoiceDetailsCode 转换发票明细Code
     * @return 转换发票明细信息
     */
    @Override
    public ConversionInvoiceDetails getConversionInvoiceDetailsByCode(String conversionInvoiceDetailsCode) {
        if (DybUtils.isEmptyOrNull(conversionInvoiceDetailsCode))
            throw new DybRuntimeException("查询转换发票明细信息时，code不能为空或null");
        return conversionInvoiceDetailsDao.getObject(conversionInvoiceDetailsCode,true);
    }

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
    public List<ConversionInvoiceDetails> getConversionInvoiceDetailsList(QueryParams wheres, int skip, int size, boolean detail) {
        return conversionInvoiceDetailsDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 新增转换发票明细
     * @param conversionCode 转换记录code
     * @param conversionInvoiceDetailses 转换发票明细
     * @return true 表示操作成功 false表示操作失败
     */
    @Override
    public boolean createConversionInvoiceDetails(String conversionCode, List<ConversionInvoiceDetails> conversionInvoiceDetailses) {
        if (DybUtils.isEmptyOrNull(conversionCode))
            throw new DybRuntimeException("新增转换发票明细时，转换信使豆code不能为空");
        if (conversionInvoiceDetailses==null||conversionInvoiceDetailses.size()<=0)
            throw new DybRuntimeException("新增转换发票明细时，转换发票明细不能为空");
        ConversionInvoiceDetails[] insertConversionInvoiceDetails=new ConversionInvoiceDetails[conversionInvoiceDetailses.size()];
        int i=0;
        for (ConversionInvoiceDetails temp:conversionInvoiceDetailses){
            if (DybUtils.isEmptyOrNull(temp.getInvoiceNum()))
                throw new DybRuntimeException("新增转换发票明细时，发票号不能为空");
            if (temp.getInvoiceMoney()<0)
                throw new DybRuntimeException("新增转换发票明细时，发票面额必须大于零");
            temp.setConversionInvoiceDetailsCode(UUID.randomUUID().toString());
            temp.setConversionCode(conversionCode);
            insertConversionInvoiceDetails[i]=temp;
            i++;
        }
        int info=conversionInvoiceDetailsDao.insertList(insertConversionInvoiceDetails);
        return info>0?true:false;
    }

}
