package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/commons")
public class WebCommonsController extends BaseController {

    public Logger log = Logger.getLogger(WebCommonsController.class);//日志

    @Autowired
    private IAccountService accountService;

    /**
     * web登陆验证
     * @param loginName 登陆ID
     * @param password 登陆密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    @RequestMapping(value = "/loginAccount")
    public Object loginAccount(HttpServletRequest request,String loginName,String password,String accountType) {
        log.info("登陆验证");
        Account account=accountService.loginAccountForClient(loginName, password, AccountType.getAccountTypeByName(accountType));
        if (account==null){
            return validationResult(1001,"登陆失败");
        }else {
            request.getSession().setAttribute("CURRENT_ACCOUNT",account);
            return result("登陆成功");
        }
    }

    /**
     * 获取菜单
     * @param accountType 账户类型
     * @return
     */
    @RequestMapping(value = "/getWebMenu")
    public Object getWebMenu(String accountType){
        log.info("获取账户菜单");
        if (DybUtils.isEmptyOrNull(accountType))
            throw new DybRuntimeException("获取菜单账户类型不能为空");
        AccountType menuType=AccountType.getAccountTypeByName(accountType);
        if (menuType==AccountType.信使){
            return result(SettingConfigureationFactory.getMenuListByKey("MEMBER_MENU"));
        }else if (menuType==AccountType.商家){
            return result(SettingConfigureationFactory.getMenuListByKey("MERCHANT_MENU"));
        }else if (menuType==AccountType.服务商){
            return result(SettingConfigureationFactory.getMenuListByKey("SERVICEPROVIDERS_MENU"));
        }else
            return validationResult(1001,"尚未定义此账户类型的菜单");
    }

}
