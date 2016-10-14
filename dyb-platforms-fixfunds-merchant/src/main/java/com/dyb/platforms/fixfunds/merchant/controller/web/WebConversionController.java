package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.conversion.service.IConversionService;
import com.dyb.platforms.fixfunds.services.business.conversioninvoicedetails.entity.ConversionInvoiceDetails;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/conversion")
public class WebConversionController extends BaseController {

    public Logger log = Logger.getLogger(WebConversionController.class);//日志

    @Autowired
    private IConversionService conversionService;
    @Autowired
    private ISystemParamsService systemParamsService;

    /**
     * 获取当前登陆用户转换表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getConversionPageList")
    public Object getConversionPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取当前登陆用户转换表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("conversionAccount", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(conversionService.getConversionPageList(queryParams,pageIndex,pageSize,true));
    }

    /**
     * 信使豆转换申请
     * @param request
     * @param messengerBeanType 转换类型
     * @param messengerBean 转换数量
     * @param tradePassword 二级密码
     * @param conversionInvoiceDetailses 发票明细
     * @return
     */
    @RequestMapping(value = "/messengerBeanConversion")
    public Object messengerBeanConversion(HttpServletRequest request,String messengerBeanType,Double messengerBean,String tradePassword,List<ConversionInvoiceDetails> conversionInvoiceDetailses){
        if (DybUtils.isEmptyOrNull(messengerBeanType))
            return validationResult(1001,"转换类型不能为空");
        MessengerBeanType conversionType=null;
        for (MessengerBeanType type:MessengerBeanType.values()){
            if (type!=MessengerBeanType.待提供发票||type!=MessengerBeanType.待缴税)
                continue;
            if (messengerBeanType.equals(type.name())){
                conversionType=type;
            }
        }
        if (conversionType==null)
            return validationResult(1001,"转换类型超出指定范围值");
        if (DybUtils.isEmptyOrNull(tradePassword))
            return validationResult(1001,"二级密码不能为空");
        SystemParams systemParams=null;
        if (conversionType==MessengerBeanType.待提供发票){
            systemParams=systemParamsService.getSystemParamsByKey("conversionTaxMerchant");
        }else if (conversionType==MessengerBeanType.待缴税){
            systemParams=systemParamsService.getSystemParamsByKey("conversionTaxMember");
        }
        if (systemParams==null)
            return validationResult(1001,"扣税比例尚未设置");
        Double deductions=Double.parseDouble(systemParams.getSystemParamsValue())*messengerBean;
        boolean flag=conversionService.messengerBeanConversion(DybUtils.getCurrentAccount(request).getAccountCode(),conversionType,messengerBean,deductions,tradePassword,conversionInvoiceDetailses);
        if (!flag)
            return validationResult(1001,"信使豆转换申请失败");
        return result("信使豆转换申请成功");
    }

}
