package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.TurnoverDetailsModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
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
@RequestMapping(value = "/web/merchant/turnover")
public class WebTurnoverController extends BaseController {

    public Logger log = Logger.getLogger(WebTurnoverController.class);//日志

    @Autowired
    private ITurnoverService turnoverService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取营业额列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getTurnoverPageList")
    public Object getTurnoverPageList(HttpServletRequest request,int pageIndex,int pageSize){
        log.info("获取营业额列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("turnoverTime",false);
        return result(turnoverService.getTurnoverPageList(queryParams, pageIndex, pageSize, true));
    }

    /**
     * 获取营业明细
     * @return
     */
    @RequestMapping(value = "/getTurnoverDetailsPageList")
    public Object getTurnoverDetailsPageList(HttpServletRequest request,String turnoverCode,int pageIndex,int pageSize) throws ParseException {
        log.info("获取营业明细");
        if (DybUtils.isEmptyOrNull(turnoverCode))
            return validationResult(1001,"营业额明细查询的code不能为空");
        Turnover turnover = turnoverService.getTurnoverByCode(turnoverCode);
        if (turnover==null)
            return validationResult(1001,"找不到此营业信息");

        PageList<TurnoverDetailsModel> turnoverDetailsModelPageList=new PageList<>();
        List<TurnoverDetailsModel> turnoverDetailsModelList=new ArrayList<>();
        Date min = DybConvert.toDate(DybConvert.dateToString(turnover.getTurnoverTime(), DybConvert.DATEFORMAT_DATA_EN_LONG), DybConvert.DATEFORMAT_DATA_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(turnover.getTurnoverTime(),1),DybConvert.DATEFORMAT_DATA_EN_LONG),DybConvert.DATEFORMAT_DATA_EN_LONG);
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("merchantCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addParameterByRange("tradeTime",min,max);
        PageList<Order> orderPageList=orderService.getOrderPageList(queryParams,pageIndex,pageSize,true);
        for (Order order:orderPageList.getList()){
            TurnoverDetailsModel turnoverDetailsModel=new TurnoverDetailsModel();
            Account member = accountService.getAccountByCode(order.getMemberCode(),true);
            Account merchant = accountService.getAccountByCode(order.getMerchantCode(),true);
            if (member==null)
                return validationResult(1001,"找不到此订单信使信息");
            if (merchant==null)
                return validationResult(1001,"找不到此订单商家信息");
            turnoverDetailsModel.setOrder(order);
            turnoverDetailsModel.setMember(member);
            turnoverDetailsModel.setMerchant(merchant);
            turnoverDetailsModelList.add(turnoverDetailsModel);
        }
        turnoverDetailsModelPageList.setPageSize(orderPageList.getPageSize());
        turnoverDetailsModelPageList.setPageIndex(orderPageList.getPageIndex());
        turnoverDetailsModelPageList.setPageCount(orderPageList.getPageCount());
        turnoverDetailsModelPageList.setTotalSize(orderPageList.getTotalSize());
        turnoverDetailsModelPageList.setList(turnoverDetailsModelList);
        return result(turnoverDetailsModelPageList);
    }

}
