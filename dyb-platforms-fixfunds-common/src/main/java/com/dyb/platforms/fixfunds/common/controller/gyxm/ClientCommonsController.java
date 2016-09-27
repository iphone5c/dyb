package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/client/commons")
public class ClientCommonsController extends BaseController {

    public Logger log = Logger.getLogger(ClientCommonsController.class);//日志

    @Autowired
    private IAccountService accountService;

    /**
     * 移动端登陆验证
     * @param loginName 登陆ID
     * @param password 登陆密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    @RequestMapping(value = "/loginClient")
    public Object loginClient(HttpServletRequest request,HttpServletResponse response,String loginName,String password,String accountType) {
        log.info("移动端登陆验证");
        Account account=accountService.loginAccountForClient(loginName, password, AccountType.getAccountTypeByName(accountType));
        if (account==null){
            return validationResult(1001,"登陆失败");
        }else {
            return result(account);
        }
    }

}
