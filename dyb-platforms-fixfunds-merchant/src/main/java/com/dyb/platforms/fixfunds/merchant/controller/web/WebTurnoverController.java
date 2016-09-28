package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/commodity")
public class WebTurnoverController extends BaseController {

    public Logger log = Logger.getLogger(WebTurnoverController.class);//日志

    @Autowired
    private ITurnoverService turnoverService;

    /**
     * 获取营业额列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getTurnoverPageList")
    public void getTurnoverPageList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize){
        log.info("获取营业额列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("turnoverTime",false);
        resultJSONP(request, response, turnoverService.getTurnoverPageList(queryParams, pageIndex, pageSize, true));
    }

}
