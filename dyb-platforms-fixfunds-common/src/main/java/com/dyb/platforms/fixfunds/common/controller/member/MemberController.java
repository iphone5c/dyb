package com.dyb.platforms.fixfunds.common.controller.member;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/member")
public class MemberController extends BaseController {

    public Logger log = Logger.getLogger(MemberController.class);//日志

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getMemberList")
    public Object getMemberList(int pageIndex,int pageSize){
        log.info("获取信使列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("registrationTime",true);
        queryParams.addMulAttrParameter("accountStatus", AccountStatus.正常.name());
        queryParams.addMulAttrParameter("accountStatus", AccountStatus.禁用.name());
        queryParams.addParameter("accountType", AccountType.信使);
        PageList<Account> accountPageList=accountService.getAccountPageList(queryParams,pageIndex,pageSize,true);
        for (Account account:accountPageList.getList()){
            Account temp=accountService.getAccountByCode(account.getAccountCode(),true);
            account.setMember(temp.getMember());
        }
        return result(accountPageList);
    }

    /**
     * 禁用指定信使
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/disableMember")
    public Object disableMember(String accountCode){
        log.info("禁用指定信使");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"禁用指定信使时，code不能为空或null");
        boolean flag=accountService.disableAccount(accountCode);
        if (flag){
            return result("禁用成功");
        }else{
            return validationResult(1001,"禁用失败");
        }
    }

    /**
     * 将指定信使解除禁用
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/removeDisableMember")
    public Object removeDisableMember(String accountCode){
        log.info("将指定信使解除禁用");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"解除指定信使的禁用时，code不能为空或null");
        boolean flag=accountService.removeDisableAccount(accountCode);
        if (flag){
            return result("解除禁用成功");
        }else{
            return validationResult(1001,"解除禁用失败");
        }
    }

    /**
     * 指定信使重置登录密码
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/resetMemberPassword")
    public Object resetMemberPassword(String accountCode){
        log.info("指定信使重置登录密码");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"指定信使重置密码时，code不能为空或null");
        boolean flag=accountService.resetAccountPassword(accountCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    /**
     * 指定信使重置二级密码
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/resetMemberTradePassword")
    public Object resetMemberTradePassword(String accountCode){
        log.info("指定信使重置二级密码");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"指定信使重置二级密码时，code不能为空或null");
        boolean flag=accountService.resetAccountTradePassword(accountCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

}
