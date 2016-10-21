package com.dyb.platforms.fixfunds.common.controller.order;

import com.dyb.platforms.fixfunds.common.controller.order.model.OrderModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.entity.em.OrderStatus;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/order")
public class OrderController extends BaseController {

    public Logger log = Logger.getLogger(OrderController.class);//日志

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getMessengerRegistrationApply")
    public Object getMessengerRegistrationApply(@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,String keyWord){
        log.info("获取信使消费登记申请列表");
        PageList<OrderModel> orderModelPageList=new PageList<>();
        List<OrderModel> orderModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime", true);
        queryParams.addParameter("status", OrderStatus.待结算);
        if (!DybUtils.isEmptyOrNull(keyWord)){
            queryParams.addMulAttrParameter("orderCode","%"+keyWord+"%");
            queryParams.addMulAttrParameter("memberCode","%"+keyWord+"%");
            queryParams.addMulAttrParameter("merchantCode","%"+keyWord+"%");
        }
        PageList<Order> orderPageList=orderService.getOrderPageList(queryParams, pageIndex, pageSize, true);
        for (Order order:orderPageList.getList()){
            Account member=accountService.getAccountByCode(order.getMemberCode(),true);
            Account merchant=accountService.getAccountByCode(order.getMerchantCode(),true);
            if(member==null)
                return validationResult(1001,"找不到此信使信息");
            if (merchant==null)
                return validationResult(1001,"找不到此商家信息");
            OrderModel orderModel=new OrderModel();
            orderModel.setOrder(order);
            orderModel.setMember(member);
            orderModel.setMerchant(merchant);
            orderModelList.add(orderModel);
        }
        orderModelPageList.setTotalSize(orderPageList.getTotalSize());
        orderModelPageList.setPageSize(orderPageList.getPageSize());
        orderModelPageList.setPageIndex(orderPageList.getPageIndex());
        orderModelPageList.setPageCount(orderPageList.getPageCount());
        orderModelPageList.setList(orderModelList);
        return result(orderModelPageList);
    }

    /**
     * 审核订单通过
     * @param orderCode 订单code
     * @return
     */
    @RequestMapping(value = "/approvedOrder")
    public Object approvedOrder(String orderCode){
        log.info("审核订单通过");
        if (DybUtils.isEmptyOrNull(orderCode))
            return validationResult(1001,"订单编号不能为空");
        boolean flag=orderService.approvedOrder(orderCode);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }

    /**
     * 撤销订单
     * @param orderCode 订单code
     * @return
     */
    @RequestMapping(value = "/cancelOrder")
    public Object cancelOrder(String orderCode){
        log.info("撤销订单");
        if (DybUtils.isEmptyOrNull(orderCode))
            return validationResult(1001,"订单编号不能为空");
        boolean flag=orderService.cancelOrder(orderCode);
        if (!flag)
            return validationResult(1001,"撤销失败");
        return result("撤销成功");
    }

}
