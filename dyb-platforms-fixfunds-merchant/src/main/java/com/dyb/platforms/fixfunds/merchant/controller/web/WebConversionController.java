package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.conversion.service.IConversionService;
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
@RequestMapping(value = "/web/merchant/conversion")
public class WebConversionController extends BaseController {

    public Logger log = Logger.getLogger(WebConversionController.class);//日志

    @Autowired
    private IConversionService conversionService;

    /**
     * 获取当前登陆用户转换表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getConversionPageList")
    public Object getConversionPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取当前登陆用户转换表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("conversionAccount", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(conversionService.getConversionPageList(queryParams,pageIndex,pageSize,true));
    }

}
