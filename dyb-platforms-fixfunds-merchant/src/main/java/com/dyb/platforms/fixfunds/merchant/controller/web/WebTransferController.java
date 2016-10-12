package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.TransferModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.transfer.entity.Transfer;
import com.dyb.platforms.fixfunds.services.business.transfer.service.ITransferService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/transfer")
public class WebTransferController extends BaseController {

    public Logger log = Logger.getLogger(WebTransferController.class);//日志

    @Autowired
    private ITransferService transferService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取获赠记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getTransferPageList")
    public Object getTransferPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取获赠记录表分页");
        PageList<TransferModel> transferModelPageList=new PageList<>();
        List<TransferModel> transferModelList=new ArrayList<>();

        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("donationTime",false);
        PageList<Transfer> transferPageList=transferService.getTransferPageList(queryParams, pageIndex, pageSize, true);
        for (Transfer transfer:transferPageList.getList()){
            Account transferAccount=accountService.getAccountByCode(transfer.getTransferAccount(),true);
            Account gainAccount=accountService.getAccountByCode(transfer.getGainAccount(),true);
            if (transferAccount==null)
                return validationResult(1001,"找不到此转赠人信息");
            if (gainAccount==null)
                return validationResult(1001,"找不到此获赠人信息");
            TransferModel transferModel=new TransferModel();
            if (transferAccount.getAccountType()== AccountType.信使){
                transferModel.setTransferAccountName(transferAccount.getMember().getRealName());
            }else if (transferAccount.getAccountType()== AccountType.商家){
                transferModel.setTransferAccountName(transferAccount.getMerchant().getPrincipalName());
            }else if (transferAccount.getAccountType()== AccountType.服务商){
                transferModel.setTransferAccountName(transferAccount.getServiceProviders().getServiceProviderName());
            }
            if (gainAccount.getAccountType()== AccountType.信使){
                transferModel.setGainAccountName(gainAccount.getMember().getRealName());
            }else if (gainAccount.getAccountType()== AccountType.商家){
                transferModel.setGainAccountName(gainAccount.getMerchant().getPrincipalName());
            }else if (gainAccount.getAccountType()== AccountType.服务商){
                transferModel.setGainAccountName(gainAccount.getServiceProviders().getServiceProviderName());
            }
            transferModel.setTransferAccountPhone(transferAccount.getAccountPhone());
            transferModel.setGainAccountPhone(gainAccount.getAccountPhone());
            transferModelList.add(transferModel);
        }
        transferModelPageList.setPageSize(transferPageList.getPageSize());
        transferModelPageList.setPageIndex(transferPageList.getPageIndex());
        transferModelPageList.setPageCount(transferPageList.getPageCount());
        transferModelPageList.setTotalSize(transferPageList.getTotalSize());
        transferModelPageList.setList(transferModelList);
        return result(transferModelPageList);
    }

}
