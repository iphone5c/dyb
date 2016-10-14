package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/bankaccount")
public class WebBankAccountController extends BaseController {

    public Logger log = Logger.getLogger(WebBankAccountController.class);//日志

    @Autowired
    private IBankAccountService bankAccountService;

    /**
     * 银行卡添加
     * @param request
     * @param response
     * @param bankAccount 银行卡信息
     */
    @RequestMapping(value = "/addBankAccount")
    public Object addBankAccount(HttpServletRequest request,HttpServletResponse response,BankAccount bankAccount) {
        log.info("银行卡添加");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", DybUtils.getCurrentAccount(request).getAccountCode());
        List<BankAccount> bankAccountList=bankAccountService.getBankAccountList(queryParams,0,-1,true);
        if (bankAccountList!=null&&bankAccountList.size()>5)
            return validationResult(1001,"银行卡已满，请先删除一些不常用的卡信息");
        if (bankAccount==null)
            return validationResult(1001,"银行卡信息不能为空");
        bankAccount.setAccountCode(DybUtils.getCurrentAccount(request).getAccountCode());
        BankAccount temp=bankAccountService.createBankAccount(bankAccount);
        if (temp==null){
            return validationResult(1001,"添加失败");
        }else {
            return result("添加成功");
        }
    }

    /**
     * 根据code删除银行卡信息
     * @param request
     * @param response
     * @param bankAccountCode 银行卡code
     */
    @RequestMapping(value = "/deleteBankAccount")
    public Object deleteBankAccount(HttpServletRequest request,HttpServletResponse response,String bankAccountCode){
        log.info("银行卡删除");
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            return validationResult(1001,"银行卡删除时，code不能为空");
        boolean flag=bankAccountService.deleteBankAccount(bankAccountCode);
        if (flag)
            return result("删除成功");
        else
            return validationResult(1001, "删除失败");
    }

    /**
     * 根据当前登陆商家获取银行卡列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getBankAccountListByCurrent")
    public Object getBankAccountPageList(HttpServletRequest request,HttpServletResponse response){
        log.info("根据当前登陆商家获取分页银行卡列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(bankAccountService.getBankAccountList(queryParams, 0, -1, true));
    }

    /**
     * 设置默认银行卡
     * @param request
     */
    @RequestMapping(value = "/setDefaultBankAccount")
    public Object setDefaultBankAccount(HttpServletRequest request,String bankAccountCode){
        log.info("设置默认银行卡");
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            return validationResult(1001,"设置的默认银行卡编号不能为空");
        boolean flag=bankAccountService.setDefaultBankAccountByCode(DybUtils.getCurrentAccount(request).getAccountCode(),bankAccountCode);
        if (!flag)
            return validationResult(1001,"设置默认银行卡失败");
        return result("设置成功");
    }

}
