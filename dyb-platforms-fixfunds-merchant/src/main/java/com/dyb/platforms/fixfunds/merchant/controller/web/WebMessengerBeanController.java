package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.accountincentive.service.IAccountIncentiveService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.NameValue;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/messengerbean")
public class WebMessengerBeanController extends BaseController {

    public Logger log = Logger.getLogger(WebMessengerBeanController.class);//日志

    @Autowired
    private IMessengerBeanService messengerBeanService;

    /**
     * 获取当前登录用户的信使豆类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMessengerBeanTypeByCurrent")
    public Object getMessengerBeanTypeByCurrent(HttpServletRequest request){
        Account account=DybUtils.getCurrentAccount(request);
        if (account==null)
            return validationResult(9999,"请先登录");
        List<NameValue> nameValueList=new ArrayList<>();
        if (account.getAccountType()== AccountType.信使){
            nameValueList.add(NameValue.create(MessengerBeanType.普通信使豆.toString(),MessengerBeanType.普通信使豆.toString()));
            nameValueList.add(NameValue.create(MessengerBeanType.待缴税.toString(),MessengerBeanType.待缴税.toString()));
        }else if (account.getAccountType()==AccountType.商家){
            nameValueList.add(NameValue.create(MessengerBeanType.普通信使豆.toString(),MessengerBeanType.普通信使豆.toString()));
            nameValueList.add(NameValue.create(MessengerBeanType.待提供发票.toString(),MessengerBeanType.待提供发票.toString()));
        }else if (account.getAccountType()==AccountType.服务商){
            nameValueList.add(NameValue.create(MessengerBeanType.普通信使豆.toString(),MessengerBeanType.普通信使豆.toString()));
            nameValueList.add(NameValue.create(MessengerBeanType.待缴税.toString(),MessengerBeanType.待缴税.toString()));
        }
        return result(nameValueList);
    }

}
