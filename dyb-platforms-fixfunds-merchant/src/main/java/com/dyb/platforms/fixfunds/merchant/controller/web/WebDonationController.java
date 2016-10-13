package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.donation.service.IDonationService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/donation")
public class WebDonationController extends BaseController {

    public Logger log = Logger.getLogger(WebDonationController.class);//日志

    @Autowired
    private IDonationService donationService;

    /**
     * 获取直捐记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getDonationPageList")
    public Object getDonationPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取直捐记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("donationTime",false);
        return result(donationService.getDonationPageList(queryParams, pageIndex, pageSize, true));
    }

    /**
     *直捐
     * @param donationMessengerBean
     * @param donationType
     * @param tradePassword
     * @return
     */
    public Object donation(HttpServletRequest request,Double donationMessengerBean,String donationType,String tradePassword){
        log.info("直捐操作");
        if (donationMessengerBean<=0)
            return validationResult(1001,"捐赠的信使豆必须大于零");
        if (DybUtils.isEmptyOrNull(donationType))
            return validationResult(1001,"直捐类型不能为空");
        MessengerBeanType donationTypeUpdate=null;
        for (MessengerBeanType messengerBeanType:MessengerBeanType.values()){
            if (messengerBeanType.name().equals(donationType)){
                donationTypeUpdate=messengerBeanType;
                break;
            }
        }
        if (donationTypeUpdate==null)
            return validationResult(1001,"直捐类型超出指定范围");
        if (DybUtils.isEmptyOrNull(tradePassword))
            return validationResult(1001,"二级密码不能为空");
        Account account=DybUtils.getCurrentAccount(request);
        boolean flag=donationService.donation(account,donationTypeUpdate,donationMessengerBean,tradePassword);
        if (!flag)
            return validationResult(1001,"直捐操作失败");
        return result("直捐成功");
    }

}
