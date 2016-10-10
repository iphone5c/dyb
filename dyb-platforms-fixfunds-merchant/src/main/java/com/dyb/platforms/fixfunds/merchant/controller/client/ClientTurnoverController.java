package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.merchant.controller.client.model.TurnoverParamModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
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
 * Created by lenovo on 2016/10/10.
 */
@RestController
@RequestMapping(value = "/client/merchant")
public class ClientTurnoverController extends BaseController {

    public Logger log = Logger.getLogger(ClientMerchantController.class);//日志

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
        log.info("移动端获取营业额列表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", getCurrentAccountClient(request).getAccountCode());
        queryParams.addOrderBy("turnoverTime",false);
        return result(turnoverService.getTurnoverPageList(queryParams, pageIndex, pageSize, true));
    }

    /**
     * 获取当前登陆用户今日营业额
     * @return
     */
    public Object getTurnoverListToday(HttpServletRequest request) throws ParseException {
        log.info("移动端获取当前登陆用户今日营业额");
        List<TurnoverParamModel> turnoverParamModelList=new ArrayList<>();
        Date min = DybConvert.toDate(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_LONG),DybConvert.DATEFORMAT_DATA_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(),1),DybConvert.DATEFORMAT_DATA_EN_LONG),DybConvert.DATEFORMAT_DATA_EN_LONG);
        List<Order> orderList=orderService.getOrderByMerchantAccount(getCurrentAccountClient(request).getAccountCode(),min,max);
        for (Order order:orderList){
            TurnoverParamModel turnoverParamModel=new TurnoverParamModel();
            Account member = accountService.getAccountByCode(order.getMemberCode(),true);
            Account merchant = accountService.getAccountByCode(order.getMerchantCode(),true);
            if (member==null)
                return validationResult(1001,"找不到此订单信使信息");
            if (merchant==null)
                return validationResult(1001,"找不到此订单商家信息");
            turnoverParamModel.setOrder(order);
            turnoverParamModel.setMember(member);
            turnoverParamModel.setMerchant(merchant);
            turnoverParamModelList.add(turnoverParamModel);
        }
        return result(turnoverParamModelList);
    }
}
