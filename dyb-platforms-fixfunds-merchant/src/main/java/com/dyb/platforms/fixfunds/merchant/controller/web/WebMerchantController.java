package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.MerchantParamModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant")
public class WebMerchantController extends BaseController {

    public Logger log = Logger.getLogger(WebMerchantController.class);//日志

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IBankAccountService bankAccountService;

    /**
     * 商户信息链接注册
     * @param account 账户对象
     * @param merchant 商家账户对象
     * @param bankAccount 银行账号信息
     * @return 商家账户对象
     */
    @RequestMapping(value = "/registerMerchantAccount")
    public void registerMerchantAccount(HttpServletRequest request,HttpServletResponse response,Account account,Merchant merchant,BankAccount bankAccount,String referrerCode,MerchantParamModel merchantParamModel) {
        log.info("商家注册");
        if (account==null)
            validationResultJSONP(request,response,1001,"账户信息不能为空");
        if (merchant==null)
            validationResultJSONP(request,response,1001,"商家注册时，商家资料不能为空");
        if (bankAccount==null)
            validationResultJSONP(request,response,1001,"商家注册时，银行账户信息不能为空");
        if (DybUtils.isEmptyOrNull(referrerCode))
            validationResultJSONP(request,response,1001,"商家注册时，推荐人不能为空");
        merchant.setMerchantType(merchantParamModel.getMerchantType());
        merchant.setIndustryType(merchantParamModel.getIndustryType());
        merchant.setIndustry(merchantParamModel.getIndustry());
        merchant.setScale(merchantParamModel.getScale());
        merchant.setPrincipalSex(merchantParamModel.getPrincipalSex());
        Account registerMerchantAccount=accountService.registerMerchant(account,merchant,bankAccount,referrerCode);
        if (registerMerchantAccount==null){
            validationResultJSONP(request,response,1001,"注册失败");
        }else {
            resultJSONP(request,response,"注册成功");
        }
    }

    /**
     * 获取当前登陆商家地理位置信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMerchantAddressByCurrent")
    public void getMerchantAddressByCode(HttpServletRequest request,HttpServletResponse response){
        log.info("获取当前登陆商家地理位置信息");
        Account account=accountService.getAccountByCode(DybUtils.getCurrentAccount(request).getAccountCode(),true);
        if (account==null)
            validationResultJSONP(request,response,1001,"找不到此账户信息");
        if (account.getMerchant()==null)
            validationResultJSONP(request,response,1001,"找不到此账户的详情信息");
        Map<String,String> result=new HashMap<>();
        result.put("address",account.getMerchant().getMerchantAddress());
        result.put("longitude",account.getMerchant().getLongitude());
        result.put("latitude",account.getMerchant().getLatitude());
        resultJSONP(request,response,result);
    }

    /**
     * 修改当前登陆商家地理位置
     * @param request
     * @param response
     * @param address 地址
     * @param longitude 经度
     * @param latitude 纬度
     */
    @RequestMapping(value = "/modifyMerchantAddressByCurrent")
    public void modifyMerchantAddressByCurrent(HttpServletRequest request,HttpServletResponse response,String address,String longitude,String latitude){
        log.info("修改当前登陆商家地理位置");
        if (DybUtils.isEmptyOrNull(address))
            validationResultJSONP(request,response,1001,"修改商家地理位置，地址不能为空");
        if (DybUtils.isEmptyOrNull(longitude))
            validationResultJSONP(request,response,1001,"修改商家地理位置，经度不能为空");
        if (DybUtils.isEmptyOrNull(latitude))
            validationResultJSONP(request,response,1001,"修改商家地理位置，纬度不能为空");
        Account account=accountService.getAccountByCode(DybUtils.getCurrentAccount(request).getAccountCode(),true);
        if (account==null)
            validationResultJSONP(request,response,1001,"找不到此账户信息");
        if (account.getMerchant()==null)
            validationResultJSONP(request,response,1001,"找不到此账户的详情信息");
        Merchant merchant = merchantService.updateMerchantAddressByCode(account.getMerchant().getMerchantCode(),address,longitude,latitude);
        if (merchant==null)
            validationResultJSONP(request,response,1001,"商家地理位置修改失败");
        resultJSONP(request,response,"商家地理位置修改成功");
    }

    /**
     * 获取当前登陆商家信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMerchantByCurrent")
    public void getMerchantByCurrent(HttpServletRequest request,HttpServletResponse response){
        log.info("获取当前登陆商家信息");
        Account account=accountService.getAccountByCode(DybUtils.getCurrentAccount(request).getAccountCode(),true);
        Account tjr=accountService.getAccountByCode(account.getReferrerCode(),true);
        if (account==null)
            validationResultJSONP(request,response,1001,"找不到此账户信息");
        if (account.getMerchant()==null)
            validationResultJSONP(request,response,1001,"找不到此账户的详情信息");

        Map<String,Object> result=new HashMap<>();
        result.put("merchant",account);
        if (tjr.getAccountType()== AccountType.信使){
            result.put("tjrRealName",tjr.getMember().getRealName());
        }else if (tjr.getAccountType()== AccountType.商家){
            result.put("tjrRealName",tjr.getMerchant().getPrincipalName());
        }else if (tjr.getAccountType()== AccountType.服务商){
            result.put("tjrRealName",tjr.getServiceProviders().getServiceProviderName());
        }
        result.put("tjrPhone",tjr.getAccountPhone());
        result.put("bank",bankAccountService.getBankAccountByDefaultChecked(account.getAccountCode()));
        resultJSONP(request,response,result);
    }

    /**
     *修改当前登陆商家资料
     * @param request
     * @param response
     * @param merchant 商户详情
     * @param bankAccount 银行卡信息
     */
    @RequestMapping(value = "/modifyMerchantByCurrent")
    public void modifyMerchantByCurrent(HttpServletRequest request,HttpServletResponse response ,Merchant merchant,BankAccount bankAccount,MerchantParamModel merchantParamModel){
        log.info("修改当前登陆商家资料");
        if (merchant==null)
            validationResultJSONP(request,response,1001,"修改商户资料，商户详情信息不能为空");
        if (bankAccount==null)
            validationResultJSONP(request,response,1001,"修改商户资料，银行卡信息不能为空");
        Account account=accountService.getAccountByCode(DybUtils.getCurrentAccount(request).getAccountCode(),true);
        if (account==null)
            validationResultJSONP(request,response,1001,"找不到此账户信息");
        if (account.getMerchant()==null)
            validationResultJSONP(request,response,1001,"找不到此账户的详情信息");
        Merchant updateMerchant = account.getMerchant();
        updateMerchant.setIndustry(merchantParamModel.getIndustry());
        updateMerchant.setScale(merchantParamModel.getScale());
        updateMerchant.setPrincipalSex(merchantParamModel.getPrincipalSex());
        updateMerchant.setMainBusiness(merchant.getMainBusiness());
        updateMerchant.setCountryPhone(merchant.getCountryPhone());
        updateMerchant.setBusinessStartTime(merchant.getBusinessStartTime());
        updateMerchant.setBusinessEndTime(merchant.getBusinessEndTime());
        updateMerchant.setMerchantDescription(merchant.getMerchantDescription());
        updateMerchant.setPrincipalName(merchant.getPrincipalName());
        updateMerchant.setPrincipalJobs(merchant.getPrincipalJobs());
        updateMerchant.setPrincipalIdCard(merchant.getPrincipalIdCard());
        updateMerchant.setPrincipalEmail(merchant.getPrincipalEmail());

        BankAccount updateBankAccount = bankAccountService.getBankAccountByDefaultChecked(account.getAccountCode());
        if (updateBankAccount==null)
            validationResultJSONP(request,response,1001,"尚未设置默认银行卡信息");
        updateBankAccount.setBankName(bankAccount.getBankName());
        updateBankAccount.setBankBranch(bankAccount.getBankBranch());
        updateBankAccount.setBankAccountName(bankAccount.getBankAccountName());
        updateBankAccount.setBankNum(bankAccount.getBankNum());

        Account result=accountService.updateMerchant(account,merchant,bankAccount);
        if (result==null)
            validationResultJSONP(request,response,1001,"商户修改资料失败");
        resultJSONP(request,response,"商户修改资料成功");
    }

}
