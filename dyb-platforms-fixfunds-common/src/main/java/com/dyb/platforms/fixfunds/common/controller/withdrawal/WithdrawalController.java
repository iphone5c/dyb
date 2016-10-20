package com.dyb.platforms.fixfunds.common.controller.withdrawal;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.business.withdrawal.service.IWithdrawalService;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/withdrawal")
public class WithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(WithdrawalController.class);//日志

    @Autowired
    private IWithdrawalService withdrawalService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getWithdrawalApply")
    public Object getWithdrawalApply(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取回购申请列表");
        PageList<WithdrawalModel> orderModelPageList=new PageList<>();
        List<WithdrawalModel> withdrawalModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime", true);
        queryParams.addParameter("applyStatus", ApplyStatus.未审核);
        PageList<Withdrawal> withdrawalPageList=withdrawalService.getWithdrawalPageList(queryParams, pageIndex, pageSize, true);
        for (Withdrawal withdrawal:withdrawalPageList.getList()){
            Account account=accountService.getAccountByCode(withdrawal.getWithdrawalCode(),true);
            if(account==null)
                return validationResult(1001,"找不到此申请人信息");
            WithdrawalModel withdrawalModel=new WithdrawalModel();
            withdrawalModel.setWithdrawal(withdrawal);
            if (account.getAccountType()== AccountType.信使){
                withdrawalModel.setApplyAccountName(account.getMember().getRealName());
            }else if (account.getAccountType()== AccountType.商家){
                withdrawalModel.setApplyAccountName(account.getMerchant().getPrincipalName());
            }else if (account.getAccountType()== AccountType.服务商){
                withdrawalModel.setApplyAccountName(account.getServiceProviders().getServiceProviderName());
            }
            withdrawalModelList.add(withdrawalModel);
        }
        orderModelPageList.setTotalSize(withdrawalPageList.getTotalSize());
        orderModelPageList.setPageSize(withdrawalPageList.getPageSize());
        orderModelPageList.setPageIndex(withdrawalPageList.getPageIndex());
        orderModelPageList.setPageCount(withdrawalPageList.getPageCount());
        orderModelPageList.setList(withdrawalModelList);
        return result(orderModelPageList);
    }

}
