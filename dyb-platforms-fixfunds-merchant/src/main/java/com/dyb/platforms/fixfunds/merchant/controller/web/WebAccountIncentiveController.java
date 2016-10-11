package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.TurnoverDetailsModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.accountincentive.service.IAccountIncentiveService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/accountincentive")
public class WebAccountIncentiveController extends BaseController {

    public Logger log = Logger.getLogger(WebAccountIncentiveController.class);//日志

    @Autowired
    private IAccountIncentiveService accountIncentiveService;

    /**
     * 获取我的激励列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getAccountIncentivePageList")
    public Object getAccountIncentivePageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取我的激励列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("accountIncentiveTime",false);
        return result(accountIncentiveService.getAccountIncentivePageList(queryParams, pageIndex, pageSize, true));
    }

}
