package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.transfer.service.ITransferService;
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
@RequestMapping(value = "/web/merchant/transfer")
public class WebTransferController extends BaseController {

    public Logger log = Logger.getLogger(WebTransferController.class);//日志

    @Autowired
    private ITransferService transferService;

    /**
     * 获取获赠记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getTransferPageList")
    public Object getTransferPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取获赠记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("donationTime",false);
        return result(transferService.getTransferPageList(queryParams, pageIndex, pageSize, true));
    }

}
