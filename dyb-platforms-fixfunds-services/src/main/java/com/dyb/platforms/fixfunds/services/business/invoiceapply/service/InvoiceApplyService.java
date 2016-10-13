package com.dyb.platforms.fixfunds.services.business.invoiceapply.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.dao.IInvoiceApplyDao;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.InvoiceApply;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.em.InvoiceApplyStatus;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;
import com.dyb.platforms.fixfunds.services.business.sendaddress.service.ISendAddressService;
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
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private ISendAddressService sendAddressService;
    @Autowired
    private IMerchantService merchantService;

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

    /**
     * 申请发票
     * @param invoiceApplyCode 发票申请code
     * @param countryPhone 公司座机
     * @param taxpayers 纳税人识别号
     * @param bankAccountCode 银行编号
     * @param sendAddressCode 寄送地址编号
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean invoiceApply(String invoiceApplyCode, String countryPhone, String taxpayers, String bankAccountCode, String sendAddressCode) {
        InvoiceApply invoiceApply=invoiceApplyDao.getObject(invoiceApplyCode, true);
        if (invoiceApply==null)
            throw new DybRuntimeException("没有找到此申请发票信息");
        Account merchant=accountService.getAccountByCode(invoiceApply.getAccountCode(),true);
        if (merchant==null)
            throw new DybRuntimeException("找不到此商家账户信息");
        BankAccount bankAccount=bankAccountService.getBankAccountByCode(bankAccountCode);
        if (bankAccount==null)
            throw new DybRuntimeException("找不到此银行卡信息");
        SendAddress sendAddress=sendAddressService.getSendAddressByCode(sendAddressCode);
        if (sendAddress==null)
            throw new DybRuntimeException("找不到此寄送地址信息");
        //设置银行卡信息
        invoiceApply.setBankName(bankAccount.getBankName());
        invoiceApply.setBankBranch(bankAccount.getBankBranch());
        invoiceApply.setBankNum(bankAccount.getBankNum());
        invoiceApply.setBankAccountName(bankAccount.getBankAccountName());
        //设置寄送地址信息
        invoiceApply.setAddress(sendAddress.getAddress());
        invoiceApply.setReceiver(sendAddress.getReceiver());
        invoiceApply.setPhone(sendAddress.getPhone());
        //设置纳税人识别号
        invoiceApply.setTaxpayers(taxpayers);
        //设置申请状态
        invoiceApply.setInvoiceApplyStatus(InvoiceApplyStatus.待审核);

        //跟换公司座机号码
        merchant.getMerchant().setCountryPhone(countryPhone);
        merchantService.updateMerchantByCode(merchant.getMerchant());

        int info=invoiceApplyDao.updateObject(invoiceApply);
        return info>0?true:false;
    }

}
