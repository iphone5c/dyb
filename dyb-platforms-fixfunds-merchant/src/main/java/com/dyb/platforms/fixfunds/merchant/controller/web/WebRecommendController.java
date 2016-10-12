package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.RecommendIncentiveModel;
import com.dyb.platforms.fixfunds.merchant.controller.web.model.RecommendRecordModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.service.IRecommendIncentiveService;
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
@RequestMapping(value = "/web/merchant/recommend")
public class WebRecommendController extends BaseController {

    public Logger log = Logger.getLogger(WebRecommendController.class);//日志

    @Autowired
    private IRecommendIncentiveService recommendIncentiveService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取当前登录用户推荐激励列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getRecommendIncentivePageList")
    public Object getRecommendIncentivePageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取当前登录用户推荐激励列表分页");
        PageList<RecommendIncentiveModel> recommendIncentiveModelPageList=new PageList<>();
        List<RecommendIncentiveModel> recommendIncentiveModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("recommendIncentiveTime",false);
        PageList<RecommendIncentive> recommendIncentivePageList=recommendIncentiveService.getRecommendIncentivePageList(queryParams, pageIndex, pageSize, true);
        for (RecommendIncentive recommendIncentive:recommendIncentivePageList.getList()){
            Account account=accountService.getAccountByCode(recommendIncentive.getRecommendAccountCode(), true);
            if (account==null)
                return validationResult(1001,"没有找到此被推荐人的信息");
            RecommendIncentiveModel recommendIncentiveModel=new RecommendIncentiveModel();
            if (account.getAccountType()== AccountType.信使){
                recommendIncentiveModel.setName(account.getMember().getRealName());
            }else if (account.getAccountType()== AccountType.商家){
                recommendIncentiveModel.setName(account.getMerchant().getPrincipalName());
            }else if (account.getAccountType()== AccountType.服务商){
                recommendIncentiveModel.setName(account.getServiceProviders().getServiceProviderName());
            }
            recommendIncentiveModel.setRecommendIncentive(recommendIncentive);
            recommendIncentiveModelList.add(recommendIncentiveModel);
        }
        recommendIncentiveModelPageList.setPageCount(recommendIncentivePageList.getPageCount());
        recommendIncentiveModelPageList.setPageIndex(recommendIncentivePageList.getPageIndex());
        recommendIncentiveModelPageList.setPageSize(recommendIncentivePageList.getPageSize());
        recommendIncentiveModelPageList.setTotalSize(recommendIncentivePageList.getTotalSize());
        recommendIncentiveModelPageList.setList(recommendIncentiveModelList);
        return result(recommendIncentiveModelPageList);
    }

    /**
     * 获取当前登录用户推荐记录列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getRecommendRecordPageList")
    public Object getRecommendRecordPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取当前登录用户推荐记录列表分页");
        PageList<RecommendRecordModel> recommendRecordModelPageList=new PageList<>();
        List<RecommendRecordModel> recommendRecordModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("referrerCode",DybUtils.getCurrentAccount(request).getAccountCode());
        PageList<Account> accountPageList=accountService.getAccountPageList(queryParams,pageIndex,pageSize,true);
        for (Account account:accountPageList.getList()){
            Account temp=accountService.getAccountByCode(account.getAccountCode(),true);
            if (temp==null)
                return validationResult(1001,"找不到此被推荐人信息");
            RecommendRecordModel recommendRecordModel=new RecommendRecordModel();
            if (temp.getAccountType()== AccountType.信使){
                recommendRecordModel.setRealName(temp.getMember().getRealName());
                recommendRecordModel.setAddress(temp.getMember().getNativePlace());
                recommendRecordModel.setEmail(temp.getMember().getMemberEmail());
                recommendRecordModel.setIndustry(temp.getMember().getIndustry());
            }else if (temp.getAccountType()== AccountType.商家){
                recommendRecordModel.setRealName(temp.getMerchant().getPrincipalName());
                recommendRecordModel.setAddress(temp.getMerchant().getMerchantAddress());
                recommendRecordModel.setEmail(temp.getMerchant().getPrincipalEmail());
                recommendRecordModel.setIndustry(temp.getMerchant().getIndustry());
            }else if (temp.getAccountType()== AccountType.服务商){
                recommendRecordModel.setRealName(temp.getServiceProviders().getServiceProviderName());
                recommendRecordModel.setAddress(temp.getServiceProviders().getAddress());
                recommendRecordModel.setEmail(temp.getServiceProviders().getEmail());
                recommendRecordModel.setIndustry(temp.getServiceProviders().getIndustry());
            }
            recommendRecordModel.setAccount(temp);
            recommendRecordModelList.add(recommendRecordModel);
        }
        recommendRecordModelPageList.setPageSize(accountPageList.getPageSize());
        recommendRecordModelPageList.setPageIndex(accountPageList.getPageIndex());
        recommendRecordModelPageList.setPageCount(accountPageList.getPageCount());
        recommendRecordModelPageList.setTotalSize(accountPageList.getTotalSize());
        recommendRecordModelPageList.setList(recommendRecordModelList);
        return result(recommendRecordModelPageList);
    }

}
