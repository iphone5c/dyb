package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.withdrawal.service.IWithdrawalService;
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
@RequestMapping(value = "/web/merchant/withdrawal")
public class WebWithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(WebWithdrawalController.class);//日志

    @Autowired
    private IWithdrawalService withdrawalService;

    /**
     * 获取当前登陆用户回购记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getWithdrawalPageList")
    public Object getWithdrawalPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取当前登陆用户回购记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("withdrawalAccount", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(withdrawalService.getWithdrawalPageList(queryParams,pageIndex,pageSize,true));
    }

}
