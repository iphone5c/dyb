package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.donation.service.IDonationService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Object getDonationPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取直捐记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("donationTime",false);
        return result(donationService.getDonationPageList(queryParams, pageIndex, pageSize, true));
    }

}
