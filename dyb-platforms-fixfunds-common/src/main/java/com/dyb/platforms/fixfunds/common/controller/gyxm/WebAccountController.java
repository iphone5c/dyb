package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.common.controller.gyxm.model.BlacklistModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.blacklist.entity.Blacklist;
import com.dyb.platforms.fixfunds.services.business.blacklist.service.IBlacklistService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/account")
public class WebAccountController extends BaseController {

    public Logger log = Logger.getLogger(WebAccountController.class);//日志

    @Autowired
    private IAccountService accountService;

    /**
     * 修改当前登录用户登录密码
     * @param request
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 旧密码
     * @return
     */
    @RequestMapping(value = "/modifyPasswordByCurrentAccount")
    public Object modifyPasswordByCurrentAccount(HttpServletRequest request,String oldPassword,String newPassword,String confirmPassword){
        log.info("修改当前登录用户登录密码");
        if (DybUtils.isEmptyOrNull(oldPassword))
            return validationResult(1001,"旧密码不能为空");
        if (DybUtils.isEmptyOrNull(newPassword))
            return validationResult(1001,"新密码不能为空");
        if (DybUtils.isEmptyOrNull(confirmPassword))
            return validationResult(1001,"确认密码不能为空");
        if (!newPassword.equals(confirmPassword))
            return validationResult(1001,"新密码与确认密码不一致");
        boolean flag=accountService.modifyPassword(DybUtils.getCurrentAccount(request).getAccountCode(),oldPassword,newPassword,confirmPassword);
        if (!flag)
            return validationResult(1001,"修改失败");
        return result("修改成功");
    }

    /**
     * 获取菜单
     * @param accountType 账户类型
     * @return
     */
    @RequestMapping(value = "/getWebMenu")
    public Object getWebMenu(HttpServletRequest request,HttpServletResponse response,String accountType){
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

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCurrentAccount")
    public Object getCurrentAccount(HttpServletRequest request){
        Account account=DybUtils.getCurrentAccount(request);
        Map<String ,Object> accountMap=new HashMap<>();
        accountMap.put("code",account.getAccountCode());
        accountMap.put("accountType",account.getAccountType());
        accountMap.put("accountName",account.getAccountName());
        return result(accountMap);
    }

}
