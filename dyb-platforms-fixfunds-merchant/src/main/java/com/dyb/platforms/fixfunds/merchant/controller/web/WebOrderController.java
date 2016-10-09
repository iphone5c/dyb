package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.merchant.controller.web.model.OrderParamModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/commodity")
public class WebOrderController extends BaseController {

    public Logger log = Logger.getLogger(WebOrderController.class);//日志

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    /**
     * 信使消费登记
     * @param accountKey 信使的Code或绑定手机号
     * @param orderItemList 订单明细
     * @return 商家账户对象
     */
    @RequestMapping(value = "/consumerRegistration")
    public Object consumerRegistration(HttpServletRequest request,HttpServletResponse response,String accountKey,List<OrderItem> orderItemList) {
        log.info("信使消费登记");
        if (DybUtils.isEmptyOrNull(accountKey))
            return validationResult(1001,"信使消费登记时，信使的Code或绑定手机号不能为空");
        if (orderItemList==null||orderItemList.size()<=0)
            return validationResult(1001,"信使消费登记时，必须添加消费的商品");
        Account account=accountService.getAccountByCodeOrPhone(accountKey, AccountType.信使);
        if (account==null)
            return validationResult(1001,"找不此信使信息");
        Order order = new Order();
        order.setMemberCode(account.getAccountCode());
        order.setMerchantCode(DybUtils.getCurrentAccount(request).getAccountCode());
        order.setTradeTime(new Date());

        Order temp=orderService.createOrder(order,orderItemList);
        if (temp==null){
            return validationResult(1001,"登记失败");
        }else {
            return result("登记成功");
        }
    }

    /**
     * 获取订单列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getOrderPageList")
    public Object getOrderPageList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize){
        log.info("获取订单列表分页");
        PageList<OrderParamModel> orderParamModelPageList=new PageList<>();
        List<OrderParamModel> orderParamModelList=new ArrayList<>();

        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("merchantCode",DybUtils.getCurrentAccount(request).getAccountCode());
        queryParams.addOrderBy("tradeTime",false);
        PageList<Order> orderPageList=orderService.getOrderPageList(queryParams,pageIndex,pageSize,true);
        for (Order order:orderPageList.getList()){
            Account account = accountService.getAccountByCode(order.getMemberCode(),true);
            if (account==null)
                return validationResult(1001,"此订单找不到对应的信使信息，orderCode："+order.getOrderCode());
            if (account.getMember()==null)
                return validationResult(1001,"此订单找不到对应的信使详情信息，orderCode："+order.getOrderCode());
            OrderParamModel orderParamModel=new OrderParamModel();
            orderParamModel.setOrder(order);
            orderParamModel.setAccount(account);
            orderParamModelList.add(orderParamModel);
        }
        orderParamModelPageList.setList(orderParamModelList);
        orderParamModelPageList.setTotalSize(orderPageList.getTotalSize());
        orderParamModelPageList.setPageCount(orderPageList.getPageCount());
        orderParamModelPageList.setPageIndex(orderPageList.getPageIndex());
        orderParamModelPageList.setPageSize(orderPageList.getPageSize());
        return result(orderParamModelPageList);
    }

}
