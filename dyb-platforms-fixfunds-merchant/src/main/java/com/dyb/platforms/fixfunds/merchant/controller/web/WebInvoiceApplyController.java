package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.accountincentive.service.IAccountIncentiveService;
import com.dyb.platforms.fixfunds.services.business.invoiceapply.service.IInvoiceApplyService;
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
@RequestMapping(value = "/web/merchant/invoiceapply")
public class WebInvoiceApplyController extends BaseController {

    public Logger log = Logger.getLogger(WebInvoiceApplyController.class);//日志

    @Autowired
    private IInvoiceApplyService invoiceApplyService;

    /**
     * 获取让利款发票申请列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getAccountIncentivePageList")
    public Object getAccountIncentivePageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取让利款发票申请列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("invoiceTime",false);
        return result(invoiceApplyService.getInvoiceApplyPageList(queryParams, pageIndex, pageSize, true));
    }

}
