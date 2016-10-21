package com.dyb.platforms.fixfunds.services.business.order.service;


import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.entity.em.OrderStatus;
import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IOrderService {

    /**
     * 新增订单
     * @param order 订单对象
     * @param orderItemList 订单明细列表
     * @return 订单对象
     */
    public Order createOrder(Order order,List<OrderItem> orderItemList);

    /**
     * 根据code查询订单信息
     * @param orderCode 订单Code
     * @return 订单信息
     */
    public Order getOrderByCode(String orderCode);

    /**
     *获取订单分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Order> getOrderPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据商户账号code获取订单
     * @param merchantAccount 商户账号code
     * @return
     */
    public List<Order> getOrderByMerchantAccount(String merchantAccount);

    /**
     * 根据商户账号code获取指定时间范围的订单
     * @param merchantAccount 商户账号code
     * @param min 最小日期
     * @param max 最大日期
     * @return
     */
    public List<Order> getOrderByMerchantAccount(String merchantAccount,Date min,Date max);

    /**
     * 操作指定订单状态
     * @param orderCode 订单code
     * @param orderStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationOrderStatus(String orderCode,OrderStatus orderStatus);

    /**
     * 审核通过订单
     * @param orderCode 订单编号
     * @return true表示操作成功 false表示操作失败
     */
    public boolean approvedOrder(String orderCode);

    /**
     * 撤销订单
     * @param orderCode 订单编号
     * @return true表示操作成功 false表示操作失败
     */
    public boolean cancelOrder(String orderCode);

}
