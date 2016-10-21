package com.dyb.platforms.fixfunds.common.controller.conversion;

import com.dyb.platforms.fixfunds.common.controller.conversion.model.ConversionModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;
import com.dyb.platforms.fixfunds.services.business.conversion.service.IConversionService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
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
    public Object getConversionApply(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,String keyWord){
        log.info("获取转换信使豆申请列表");
        PageList<ConversionModel> conversionModelPageList=new PageList<>();
        List<ConversionModel> conversionModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime", true);
        queryParams.addParameter("applyStatus", ApplyStatus.未审核);
        if (!DybUtils.isEmptyOrNull(keyWord)){
            queryParams.addMulAttrParameter("conversionCode","%"+keyWord+"%");
            queryParams.addMulAttrParameter("conversionAccount","%"+keyWord+"%");
        }
        PageList<Conversion> conversionPageList=conversionService.getConversionPageList(queryParams, pageIndex, pageSize, true);
        for (Conversion conversion:conversionPageList.getList()){
            Account account=accountService.getAccountByCode(conversion.getConversionAccount(),true);
            if(account==null)
                return validationResult(1001,"找不到此申请人信息");
            ConversionModel conversionModel=new ConversionModel();
            conversionModel.setConversion(conversion);
            if (account.getAccountType()== AccountType.信使){
                conversionModel.setApplyAccountName(account.getMember().getRealName());
            }else if (account.getAccountType()== AccountType.商家){
                conversionModel.setApplyAccountName(account.getMerchant().getPrincipalName());
            }else if (account.getAccountType()== AccountType.服务商){
                conversionModel.setApplyAccountName(account.getServiceProviders().getServiceProviderName());
            }
            conversionModelList.add(conversionModel);
        }
        conversionModelPageList.setTotalSize(conversionPageList.getTotalSize());
        conversionModelPageList.setPageSize(conversionPageList.getPageSize());
        conversionModelPageList.setPageIndex(conversionPageList.getPageIndex());
        conversionModelPageList.setPageCount(conversionPageList.getPageCount());
        conversionModelPageList.setList(conversionModelList);
        return result(conversionModelPageList);
    }

    /**
     * 审核通过转换申请
     * @param conversionCode
     * @return
     */
    @RequestMapping(value = "/approvedConversion")
    public Object approvedConversion(String conversionCode){
        log.info("审核通过转换申请");
        if (DybUtils.isEmptyOrNull(conversionCode))
            return validationResult(1001,"转换编号不能为空");
        boolean flag=conversionService.approvedConversion(conversionCode);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }

    /**
     * 审核不通过
     * @param conversionCode
     * @return
     */
    @RequestMapping(value = "/cancelConversion")
    public Object cancelConversion(String conversionCode){
        log.info("审核不通过转换申请");
        if (DybUtils.isEmptyOrNull(conversionCode))
            return validationResult(1001,"转换编号不能为空");
        boolean flag=conversionService.cancelConversion(conversionCode);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }

}
