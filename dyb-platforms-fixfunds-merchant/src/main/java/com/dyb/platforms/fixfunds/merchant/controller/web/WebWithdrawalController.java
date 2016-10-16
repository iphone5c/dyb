package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.alibaba.fastjson.JSON;
import com.dyb.platforms.fixfunds.merchant.controller.web.model.WithdrawalParamModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Invoice;
import com.dyb.platforms.fixfunds.services.business.withdrawal.service.IWithdrawalService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/withdrawal")
public class WebWithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(WebWithdrawalController.class);//日志

    @Autowired
    private IWithdrawalService withdrawalService;
    @Autowired
    private IMessengerBeanService messengerBeanService;
    @Autowired
    private ISystemParamsService systemParamsService;

    /**
     * 获取当前登陆用户回购记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getWithdrawalPageList")
    public Object getWithdrawalPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取当前登陆用户回购记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("withdrawalAccount", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(withdrawalService.getWithdrawalPageList(queryParams,pageIndex,pageSize,true));
    }

    /**
     * 回购信使豆申请
     * @return
     */
    @RequestMapping(value = "/withdrawalMessengerBean")
    public Object withdrawalMessengerBean(HttpServletRequest request){
        log.info("回购信使豆申请");
        String info=request.getParameter("withdrawalMessengerBeanParam");
        if (DybUtils.isEmptyOrNull(info))
            throw new IllegalArgumentException("参数为空");
        WithdrawalParamModel withdrawalParamModel= JSON.parseObject(info, WithdrawalParamModel.class);
        //回购类型
        String withdrawalType=withdrawalParamModel.getWithdrawalType();
        // 回购的信使豆
        Double withdrawalNum=withdrawalParamModel.getWithdrawalNum();
        //二级密码
        String tradePassword=withdrawalParamModel.getTradePassword();
        //发票明细
        List<Invoice> invoiceList=withdrawalParamModel.getInvoiceList();
        if (DybUtils.isEmptyOrNull(withdrawalType))
            return validationResult(1001,"回购类型不能为空");
        if (withdrawalNum%100!=0)
            return validationResult(1001,"回购数量必须是100的整倍数");
        if (DybUtils.isEmptyOrNull(tradePassword))
            return validationResult(1001,"二级密码不能为空");
        MessengerBeanType messengerBeanType=null;
        for (MessengerBeanType temp:MessengerBeanType.values()){
            if (!(temp==MessengerBeanType.普通信使豆||temp==MessengerBeanType.待提供发票||temp==MessengerBeanType.待缴税)){
                continue;
            }
            if (withdrawalType.equals(temp.name())){
                messengerBeanType=temp;
                break;
            }
        }
        if (messengerBeanType==null)
            return validationResult(1001,"回购类型超出指定范围值");
        boolean flag=withdrawalService.withdrawalMessengerBean(messengerBeanType,withdrawalNum,tradePassword,DybUtils.getCurrentAccount(request).getAccountCode(),invoiceList);
        if (!flag)
            return validationResult(1001,"回购申请失败");
        return result("回购申请成功");
    }

    /**
     * 回购信使豆申请编辑
     * @return
     */
    @RequestMapping(value = "/getWithdrawalApplyEdit")
    public Object getWithdrawalApplyEdit(HttpServletRequest request,String withdrawalType){
        if (DybUtils.isEmptyOrNull(withdrawalType))
            return validationResult(1001,"回购类型不能为空");
        MessengerBeanType messengerBeanType=null;
        for (MessengerBeanType temp:MessengerBeanType.values()){
            if (!(temp==MessengerBeanType.普通信使豆||temp==MessengerBeanType.待提供发票||temp==MessengerBeanType.待缴税)){
                continue;
            }
            if (withdrawalType.equals(temp.name())){
                messengerBeanType=temp;
                break;
            }
        }
        if (messengerBeanType==null)
            return validationResult(1001,"回购类型超出指定范围值");
        MessengerBean messengerBean=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(DybUtils.getCurrentAccount(request).getAccountCode(), messengerBeanType);
        if (messengerBean==null)
            return validationResult(1001,"没有找到当前用户的信使豆类型");
        SystemParams poundage=systemParamsService.getSystemParamsByKey("poundage");
        if (poundage==null)
            throw new DybRuntimeException("手续费参数未指定");
        Map<String,Object> datas=new HashMap<>();
        datas.put("messengerBean",messengerBean.getMessengerBean());
        datas.put("poundage",poundage);
        if (messengerBeanType==MessengerBeanType.待缴税){
            SystemParams systemParams=systemParamsService.getSystemParamsByKey("conversionTaxNoInvoice");
            if (systemParams==null)
                throw new DybRuntimeException("扣税比例尚未设置");
            datas.put("deductions",Double.parseDouble(systemParams.getSystemParamsValue()));
        }else if (messengerBeanType==MessengerBeanType.待提供发票){
            SystemParams systemParams=systemParamsService.getSystemParamsByKey("conversionTaxInvoice");
            if (systemParams==null)
                throw new DybRuntimeException("扣税比例尚未设置");
            datas.put("deductions",Double.parseDouble(systemParams.getSystemParamsValue()));
        }
        return result(datas);
    }

}
