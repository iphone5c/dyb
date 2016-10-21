package com.dyb.platforms.fixfunds.common.controller.messengerbean;

import com.dyb.platforms.fixfunds.common.controller.messengerbean.model.MessengerBeanModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
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
@RequestMapping(value = "/back/commons/messengerBean")
public class MessengerBeanController extends BaseController {

    public Logger log = Logger.getLogger(MessengerBeanController.class);//日志

    @Autowired
    private IMessengerBeanService messengerBeanService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getMessengerBeanPageList")
    public Object getMessengerBeanPageList(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,String keyWord){
        log.info("获取信使豆列表");
        PageList<MessengerBeanModel> messengerBeanModelPageList=new PageList<>();
        List<MessengerBeanModel> messengerBeanModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        if (!DybUtils.isEmptyOrNull(keyWord)){
            queryParams.addMulAttrParameter("accountCode","%"+keyWord+"%");
        }
        PageList<MessengerBean> messengerBeanPageList=messengerBeanService.getMessengerBeanPageList(queryParams, pageIndex, pageSize, true);
        for (MessengerBean messengerBean:messengerBeanPageList.getList()){
            Account account=accountService.getAccountByCode(messengerBean.getAccountCode(),true);
            if(account==null)
                return validationResult(1001,"找不到此账户信息");
            MessengerBeanModel messengerBeanModel=new MessengerBeanModel();
            messengerBeanModel.setAccountName(account.getAccountName());
            messengerBeanModel.setAccountPhone(account.getAccountPhone());
            if (account.getAccountType()== AccountType.信使){
                messengerBeanModel.setRealName(account.getMember().getRealName());
            }else if (account.getAccountType()== AccountType.商家){
                messengerBeanModel.setRealName(account.getMerchant().getPrincipalName());
            }else if (account.getAccountType()== AccountType.服务商){
                messengerBeanModel.setRealName(account.getServiceProviders().getServiceProviderName());
            }
            messengerBeanModel.setMessengerBean(messengerBean);
            messengerBeanModelList.add(messengerBeanModel);
        }
        messengerBeanModelPageList.setTotalSize(messengerBeanPageList.getTotalSize());
        messengerBeanModelPageList.setPageSize(messengerBeanPageList.getPageSize());
        messengerBeanModelPageList.setPageIndex(messengerBeanPageList.getPageIndex());
        messengerBeanModelPageList.setPageCount(messengerBeanPageList.getPageCount());
        messengerBeanModelPageList.setList(messengerBeanModelList);
        return result(messengerBeanModelPageList);
    }

}
