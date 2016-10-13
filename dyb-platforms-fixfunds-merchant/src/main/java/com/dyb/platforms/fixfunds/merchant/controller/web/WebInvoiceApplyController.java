package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.InvoiceApplyModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.entity.InvoiceApply;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.service.IInvoiceApplyService;
import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;
import com.dyb.platforms.fixfunds.services.business.sendaddress.service.ISendAddressService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/invoiceapply")
public class WebInvoiceApplyController extends BaseController {

    public Logger log = Logger.getLogger(WebInvoiceApplyController.class);//日志

    @Autowired
    private IInvoiceApplyService invoiceApplyService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private ISendAddressService sendAddressService;

    /**
     * 获取让利款发票申请列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getInvoiceApplyPageList")
    public Object getInvoiceApplyPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取让利款发票申请列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("invoiceTime",false);
        return result(invoiceApplyService.getInvoiceApplyPageList(queryParams, pageIndex, pageSize, true));
    }

    /**
     * 根据code查询发票详情
     * @param invoiceApplyCode 申请编号
     * @return
     */
    @RequestMapping(value = "/getInvoiceApplyByCode")
    public Object getInvoiceApplyByCode(String invoiceApplyCode){
        log.info("根据code查询发票详情");
        if (DybUtils.isEmptyOrNull(invoiceApplyCode))
            return validationResult(1001,"申请发票code不能为空");
        InvoiceApply invoiceApply=invoiceApplyService.getInvoiceApplyByCode(invoiceApplyCode);
        if (invoiceApply==null)
            return validationResult(1001,"没有找到此发票申请信息");
        Account account=accountService.getAccountByCode(invoiceApply.getAccountCode(),true);
        if(account==null)
            return validationResult(1001,"没有找到此申请发票的商家信息");
        BankAccount bankAccount=bankAccountService.getBankAccountByDefaultChecked(account.getAccountCode());
        SendAddress sendAddress=sendAddressService.getSendAddressByDefaultChecked(account.getAccountCode());
        InvoiceApplyModel invoiceApplyModel=new InvoiceApplyModel();
        invoiceApplyModel.setMerchant(account.getMerchant());
        invoiceApplyModel.setBankAccount(bankAccount);
        invoiceApplyModel.setSendAddress(sendAddress);
        invoiceApplyModel.setInvoiceApply(invoiceApply);
        return result(invoiceApplyModel);
    }

    /**
     * 发票申请
     * @param invoiceApplyCode 发票申请单号
     * @param countryPhone 公司座机
     * @param taxpayers 纳税人识别号
     * @param bankAccountCode 银行编号
     * @param sendAddressCode 寄送地址编号
     * @return
     */
    public Object invoiceApply(String invoiceApplyCode,String countryPhone,String taxpayers,String bankAccountCode,String sendAddressCode){
        if (DybUtils.isEmptyOrNull(invoiceApplyCode))
            return validationResult(1001,"申请发票code不能为空");
        if (DybUtils.isEmptyOrNull(countryPhone))
            return validationResult(1001,"公司座机不能为空");
        if (DybUtils.isEmptyOrNull(taxpayers))
            return validationResult(1001,"纳税人识别号不能为空");
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            return validationResult(1001,"银行编号不能为空");
        if (DybUtils.isEmptyOrNull(sendAddressCode))
            return validationResult(1001,"寄送地址编号不能为空");
        boolean flag=invoiceApplyService.invoiceApply(invoiceApplyCode,countryPhone,taxpayers,bankAccountCode,sendAddressCode);
        if (flag)
            return validationResult(1001,"申请失败");
        return result("申请成功");
    }

}
