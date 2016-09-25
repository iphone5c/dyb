package com.dyb.platforms.fixfunds.merchant.controller;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.business.recommended.entity.em.AccountType;
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
@RequestMapping(value = "/back/merchant/merchant")
public class MerchantController extends BaseController {

    public Logger log = Logger.getLogger(MerchantController.class);//日志

    @Autowired
    private IMerchantService merchantService;

    /**
     * 商户信息链接注册
     * @param merchant 商家账户对象
     * @param bankAccount 银行账号信息
     * @param tjrCode 推荐人的code
     * @param tjrType 推荐人类型(1是商家 2是信使)
     * @return 商家账户对象
     */
    @RequestMapping(value = "/registerMerchant")
    public Object registerMerchant(Merchant merchant,BankAccount bankAccount,String tjrCode,String tjrType) {
        log.info("商家注册");
        Merchant registerMerchant=null;
        if (DybUtils.isEmptyOrNull(tjrType))
            return validationResult(1001,"推荐人类型不能为空");
        if (tjrCode.equals("1")){
            registerMerchant=merchantService.createMerchantRegistered(merchant,bankAccount,tjrCode,AccountType.商家);
        }else if (tjrCode.equals("2")){
            registerMerchant=merchantService.createMerchantRegistered(merchant,bankAccount,tjrCode,AccountType.信使);
        }
        if (registerMerchant==null){
            return validationResult(1001,"注册失败");
        }else {
            return result("注册成功");
        }
    }

}
