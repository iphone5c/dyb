package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.RecommendRecordModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.service.IRecommendIncentiveService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/client/merchant/recommend")
public class ClientRecommendController extends BaseController {

    public Logger log = Logger.getLogger(ClientRecommendController.class);//日志

    @Autowired
    private IRecommendIncentiveService recommendIncentiveService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取当前登录用户推荐记录列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getRecommendRecordPageList")
    public Object getRecommendRecordPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("移动端获取当前登录用户推荐记录列表分页");
        PageList<RecommendRecordModel> recommendRecordModelPageList=new PageList<>();
        List<RecommendRecordModel> recommendRecordModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("referrerCode",getCurrentAccountClient(request).getAccountCode());
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

    /**
     * 获取推荐信使豆列表
     * @param status 1:今天 2：近一周  3：近一个月  4：近一个季度
     * @return
     */
    @RequestMapping(value = "/getRecommendIncentiveList")
    public Object getRecommendIncentiveList(HttpServletRequest request,@RequestParam(required=false,defaultValue="1")int status) throws ParseException {
        log.info("移动端获取推荐信使豆列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", getCurrentAccountClient(request).getAccountCode());
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(new Date(), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("recommendIncentiveTime",min,new Date());
        }else if (status==2){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(), -7), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("recommendIncentiveTime",min,new Date());
        }else if (status==3){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("recommendIncentiveTime",min,new Date());
        }else if (status==4){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -3), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("recommendIncentiveTime",min,new Date());
        }
        queryParams.addOrderBy("recommendIncentiveTime",false);
        List<RecommendIncentive> recommendIncentiveList=recommendIncentiveService.getRecommendIncentiveList(queryParams, 0, -1, true);
        return result(getDatasToRecommendIncentive(getCurrentAccountClient(request),recommendIncentiveList));
    }

    private Map<String,Object> getDatasToRecommendIncentive(Account currentAccount,List<RecommendIncentive> recommendIncentiveList){
        if (currentAccount.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("不是商家用户");
        Account temp=accountService.getAccountByCode(currentAccount.getAccountCode(),true);

        Double messengerBean=0d;
        Map<String,Object> result=new HashMap<>();
        Map<String,List<RecommendIncentive>> recommendIncentiveMap=new LinkedHashMap<>();
        for (RecommendIncentive recommendIncentive:recommendIncentiveList){
            messengerBean+=recommendIncentive.getMessengerBean();
            String date=DybConvert.dateToString(recommendIncentive.getRecommendIncentiveTime(),DybConvert.DATEFORMAT_DATA_EN_SHORT);
            List<RecommendIncentive> recommendIncentives=recommendIncentiveMap.get(date);
            if (recommendIncentives==null){
                List<RecommendIncentive> recommendIncentiveArrayList=new ArrayList<>();
                recommendIncentiveArrayList.add(recommendIncentive);
                recommendIncentiveMap.put(date,recommendIncentiveArrayList);
            }else {
                recommendIncentives.add(recommendIncentive);
            }

        }
        result.put("datas",recommendIncentiveMap);
        result.put("messengerBean",messengerBean);
        return result;
    }

}
