package com.dyb.platforms.fixfunds.services.business.order.service;


import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

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

}
