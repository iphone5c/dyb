package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.benefitdonation.service.IBenefitDonationService;
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
@RequestMapping(value = "/web/merchant/benefitdonation")
public class WebBenefitDonationController extends BaseController {

    public Logger log = Logger.getLogger(WebBenefitDonationController.class);//日志

    @Autowired
    private IBenefitDonationService benefitDonationService;

    /**
     * 获取当前登陆用户让利捐款表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getBenefitDonationPageList")
    public Object getBenefitDonationPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取当前登陆用户让利捐款表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(benefitDonationService.getBenefitDonationPageList(queryParams,pageIndex,pageSize,true));
    }

}
