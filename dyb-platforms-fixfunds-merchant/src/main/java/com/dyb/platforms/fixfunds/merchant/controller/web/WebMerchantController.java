package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant")
public class WebMerchantController extends BaseController {

    public Logger log = Logger.getLogger(WebMerchantController.class);//日志

    @Autowired
    private IAccountService accountService;

    /**
     * 商户信息链接注册
     * @param account 账户对象
     * @param merchant 商家账户对象
     * @param bankAccount 银行账号信息
     * @param referrerCode 推荐人的code
     * @return 商家账户对象
     */
    @RequestMapping(value = "/registerMerchantAccount")
    public Object registerMerchantAccount(Account account,Merchant merchant,BankAccount bankAccount,String referrerCode) {
        log.info("商家注册");
        if (account==null)
            return validationResult(1001,"商家注册时，账户信息不能为空");
        if (merchant==null)
            return validationResult(1001,"商家注册时，商家资料不能为空");
        if (bankAccount==null)
            return validationResult(1001,"商家注册时，银行账户信息不能为空");
        if (DybUtils.isEmptyOrNull(referrerCode))
            return validationResult(1001,"商家注册时，推荐人不能为空");
        Account registerMerchantAccount=accountService.registerMerchant(account,merchant,bankAccount,referrerCode);
        if (registerMerchantAccount==null){
            return validationResult(1001,"注册失败");
        }else {
            return result("注册成功");
        }
    }

}
