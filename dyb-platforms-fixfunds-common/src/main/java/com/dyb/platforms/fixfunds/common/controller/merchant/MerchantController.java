package com.dyb.platforms.fixfunds.common.controller.merchant;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/merchant")
public class MerchantController extends BaseController {

    public Logger log = Logger.getLogger(MerchantController.class);//日志

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getMerchantList")
    public Object getMerchantList(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取商家列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("registrationTime",true);
        queryParams.addMulAttrParameter("accountStatus", AccountStatus.正常.name());
        queryParams.addMulAttrParameter("accountStatus", AccountStatus.禁用.name());
        queryParams.addParameter("accountType", AccountType.商家);
        PageList<Account> accountPageList=accountService.getAccountPageList(queryParams,pageIndex,pageSize,true);
        for (Account account:accountPageList.getList()){
            Account temp=accountService.getAccountByCode(account.getAccountCode(),true);
            account.setMerchant(temp.getMerchant());
        }
        return result(accountPageList);
    }

    @RequestMapping(value = "/getMerchantAuditList")
    public Object getMerchantAuditList(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,String keyWord){
        log.info("获取商家审核列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("registrationTime",true);
        queryParams.addMulAttrParameter("accountStatus", AccountStatus.审核中.name());
        queryParams.addParameter("accountType", AccountType.商家);
        if (!DybUtils.isEmptyOrNull(keyWord)){
            queryParams.addMulAttrParameter("accountCode","%"+keyWord+"%");
            queryParams.addMulAttrParameter("accountPhone","%"+keyWord+"%");
        }
        PageList<Account> accountPageList=accountService.getAccountPageList(queryParams,pageIndex,pageSize,true);
        for (Account account:accountPageList.getList()){
            Account temp=accountService.getAccountByCode(account.getAccountCode(),true);
            account.setMerchant(temp.getMerchant());
        }
        return result(accountPageList);
    }

    /**
     * 审核通过商家申请
     * @param merchantCode
     * @return
     */
    @RequestMapping(value = "/approvedMerchant")
    public Object approvedMerchant(String merchantCode){
        log.info("审核通过商家申请");
        if (DybUtils.isEmptyOrNull(merchantCode))
            return validationResult(1001,"商家编号不能为空");
        boolean flag=accountService.approvedAccount(merchantCode);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }

    /**
     * 审核不通过
     * @param merchantCode
     * @return
     */
    @RequestMapping(value = "/cancelMerchant")
    public Object cancelMerchant(String merchantCode){
        log.info("审核不通过商家申请");
        if (DybUtils.isEmptyOrNull(merchantCode))
            return validationResult(1001,"商家编号不能为空");
        boolean flag=accountService.cancelAccount(merchantCode);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }

    /**
     * 禁用指定商家
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/disableMerchant")
    public Object disableMerchant(String accountCode){
        log.info("禁用指定商家");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"禁用指定商家时，code不能为空或null");
        boolean flag=accountService.disableAccount(accountCode);
        if (flag){
            return result("禁用成功");
        }else{
            return validationResult(1001,"禁用失败");
        }
    }

    /**
     * 将指定商家解除禁用
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/removeDisableMerchant")
    public Object removeDisableMerchant(String accountCode){
        log.info("将指定商家解除禁用");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"解除指定商家的禁用时，code不能为空或null");
        boolean flag=accountService.removeDisableAccount(accountCode);
        if (flag){
            return result("解除禁用成功");
        }else{
            return validationResult(1001,"解除禁用失败");
        }
    }

    /**
     * 指定商家重置登录密码
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/resetMerchantPassword")
    public Object resetMerchantPassword(String accountCode){
        log.info("指定商家重置登录密码");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"指定商家重置密码时，code不能为空或null");
        boolean flag=accountService.resetAccountPassword(accountCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    /**
     * 指定商家重置二级密码
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/resetMerchantTradePassword")
    public Object resetMerchantTradePassword(String accountCode){
        log.info("指定商家重置二级密码");
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"指定商家重置二级密码时，code不能为空或null");
        boolean flag=accountService.resetAccountTradePassword(accountCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

}
