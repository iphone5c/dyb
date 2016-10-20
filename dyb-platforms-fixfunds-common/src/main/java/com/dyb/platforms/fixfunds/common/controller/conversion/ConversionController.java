package com.dyb.platforms.fixfunds.common.controller.conversion;

import com.dyb.platforms.fixfunds.common.controller.conversion.model.ConversionModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;
import com.dyb.platforms.fixfunds.services.business.conversion.service.IConversionService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
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
@RequestMapping(value = "/back/commons/conversion")
public class ConversionController extends BaseController {

    public Logger log = Logger.getLogger(ConversionController.class);//日志

    @Autowired
    private IConversionService conversionService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getConversionApply")
    public Object getConversionApply(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取转换信使豆申请列表");
        PageList<ConversionModel> conversionModelPageList=new PageList<>();
        List<ConversionModel> conversionModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime", true);
        queryParams.addParameter("applyStatus", ApplyStatus.未审核);
        PageList<Conversion> conversionPageList=conversionService.getConversionPageList(queryParams, pageIndex, pageSize, true);
        for (Conversion conversion:conversionPageList.getList()){
            Account account=accountService.getAccountByCode(conversion.getConversionAccount(),true);
            if(account==null)
                return validationResult(1001,"找不到此申请人信息");
            ConversionModel withdrawalModel=new ConversionModel();
            withdrawalModel.setConversion(conversion);
            if (account.getAccountType()== AccountType.信使){
                withdrawalModel.setApplyAccountName(account.getMember().getRealName());
            }else if (account.getAccountType()== AccountType.商家){
                withdrawalModel.setApplyAccountName(account.getMerchant().getPrincipalName());
            }else if (account.getAccountType()== AccountType.服务商){
                withdrawalModel.setApplyAccountName(account.getServiceProviders().getServiceProviderName());
            }
            conversionModelList.add(withdrawalModel);
        }
        conversionModelPageList.setTotalSize(conversionPageList.getTotalSize());
        conversionModelPageList.setPageSize(conversionPageList.getPageSize());
        conversionModelPageList.setPageIndex(conversionPageList.getPageIndex());
        conversionModelPageList.setPageCount(conversionPageList.getPageCount());
        conversionModelPageList.setList(conversionModelList);
        return result(conversionModelPageList);
    }

}
