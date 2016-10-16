package com.dyb.platforms.fixfunds.services.business.conversion.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.conversion.dao.IConversionDao;
import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;
import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.entity.ConversionInvoiceDetails;
import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.service.IConversionInvoiceDetailsService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("conversionService")
public class ConversionService extends BaseService implements IConversionService {

    public Logger log = Logger.getLogger(ConversionService.class);//日志

    @Autowired
    private IConversionDao conversionDao;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IConversionInvoiceDetailsService conversionInvoiceDetailsService;
    @Autowired
    private IMessengerBeanService messengerBeanService;

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
    public List<Conversion> getConversionList(QueryParams wheres, int skip, int size, boolean detail) {
        return conversionDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的转换信使豆记录信息
     * @param conversionCode 转换信使豆记录code
     * @return 转换信使豆记录信息
     */
    @Override
    public Conversion getConversionByCode(String conversionCode) {
        if (DybUtils.isEmptyOrNull(conversionCode))
            throw new DybRuntimeException("根据转换信使豆记录code获取转换信使豆记录信息时，转换信使豆记录code不能为空");
        return conversionDao.getObject(conversionCode,true);
    }

    /**
     *获取转换信使豆记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Conversion> getConversionPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return conversionDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 信使豆转换申请
     * @param accountCode 申请人code
     * @param messengerBeanType 转换类型
     * @param messengerBean 转换的数量
     * @param deductions 代扣税
     * @param tradePassword 二级密码
     * @param conversionInvoiceDetailses 发票明细
     * @return true表示操作成功 false表示操作成功
     */
    @Override
    public boolean messengerBeanConversion(String accountCode,MessengerBeanType messengerBeanType,Double messengerBean,Double deductions,String tradePassword,List<ConversionInvoiceDetails> conversionInvoiceDetailses) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("转换申请人不能为空");
        if (messengerBeanType==null)
            throw new DybRuntimeException("转换类型不能为空");
        if (messengerBean%100!=0)
            throw new DybRuntimeException("转换数量必须是100的整倍数");
        if (DybUtils.isEmptyOrNull(tradePassword))
            throw new DybRuntimeException("二级密码不能为空");
        if (messengerBean<0)
            throw new DybRuntimeException("代扣税必须大于零");
        Account account=accountService.getAccountByCode(accountCode,false);
        if (account==null)
            throw new DybRuntimeException("找不到此申请人的信息");
        if (!DybUtils.verifyPassword(tradePassword,account.getTradePassword()))
            throw new DybRuntimeException("二级密码输入错误");
        Conversion conversion=new Conversion();
        conversion.setConversionType(messengerBeanType);
        conversion.setApplyConversionNum(messengerBean);
        conversion.setDeductions(deductions);
        conversion.setConversionAccount(account.getAccountCode());
        Conversion temp=this.createConversion(conversion,conversionInvoiceDetailses);
        return temp==null?false:true;
    }

    /**
     * 新增转换信使豆申请记录
     * @param conversion 转换记录
     * @param conversionInvoiceDetailses 转换发票明细
     * @return
     */
    @Override
    public Conversion createConversion(Conversion conversion, List<ConversionInvoiceDetails> conversionInvoiceDetailses) {
        if (conversion==null)
            throw new DybRuntimeException("新增转换信使豆申请记录时，conversion不能为空");
        if (conversion.getConversionType()==null)
            throw new DybRuntimeException("新增转换信使豆申请记录时，转换类型不能为空");
        if (!(conversion.getConversionType()==MessengerBeanType.待提供发票||conversion.getConversionType()==MessengerBeanType.待缴税))
            throw new DybRuntimeException("新增转换信使豆申请记录时，转换类型超出指定范围值");
        if (conversion.getApplyConversionNum()<=0)
            throw new DybRuntimeException("新增转换信使豆申请记录时，申请转换信使豆数量必须大于零");
        if (conversion.getDeductions()<0)
            throw new DybRuntimeException("新增转换信使豆申请记录时，代扣税必须大于等于零");
        if (DybUtils.isEmptyOrNull(conversion.getConversionAccount()))
            throw new DybRuntimeException("新增转换信使豆申请记录时，申请转换人不能为空");
        Account account=accountService.getAccountByCode(conversion.getConversionAccount(),false);
        if (account==null)
            throw new DybRuntimeException("新增转换信使豆申请记录时，找不到此申请人信息");

        String conversionCode=codeBuilder.getConversionCode();
        conversion.setConversionCode(conversionCode);
        conversion.setApplyTime(new Date());
        conversion.setApplyStatus(ApplyStatus.未审核);
        conversion.setCreateTime(new Date());
        //添加发票信息
        if (conversion.getConversionType()==MessengerBeanType.待提供发票){
            //商家申请
            if (conversionInvoiceDetailses==null||conversionInvoiceDetailses.size()<=0)
                throw new DybRuntimeException("新增转换信使豆申请记录时，转换发票明细不能为空");
            boolean flag=conversionInvoiceDetailsService.createConversionInvoiceDetails(conversionCode,conversionInvoiceDetailses);
            if (!flag)
                throw new DybRuntimeException("新增转换信使豆申请记录时，创建转换发票明细失败");
        }
        //账户信使豆处理
        MessengerBean messengerBean=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(account.getAccountCode(),conversion.getConversionType());
        if (messengerBean==null)
            throw new DybRuntimeException("新增转换信使豆申请记录时，找不到此账户的信使豆信息");
        //可用余额
        Double mBean=messengerBean.getMessengerBean();
        //冻结的信使豆
        Double fBean=messengerBean.getFreeze();
        if (mBean<conversion.getApplyConversionNum())
            throw new DybRuntimeException("可提信使豆余额不足");
        messengerBean.setMessengerBean(mBean-conversion.getApplyConversionNum());
        messengerBean.setFreeze(fBean+conversion.getApplyConversionNum());
        MessengerBean updateMessengerBean=messengerBeanService.updateMessengerBean(messengerBean);
        if (updateMessengerBean==null)
            throw new DybRuntimeException("新增转换信使豆申请记录时，账户信使豆处理失败");
        int info=conversionDao.insertObject(conversion);
        return info>0?conversion:null;
    }

}
