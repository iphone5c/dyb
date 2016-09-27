package com.dyb.platforms.fixfunds.services.business.orderitem.service;


import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IOrderItemService {

    /**
     * 新增订单明细
     * @param orderCode 所属订单编号
     * @param orderItem 订单明细对象
     * @return 订单明细对象
     */
    public OrderItem createOrderItem(String orderCode,OrderItem orderItem);

    /**
     * 根据code查询订单明细信息
     * @param orderItemCode 订单明细Code
     * @return 订单明细信息
     */
    public OrderItem getOrderItemByCode(String orderItemCode);

    /**
     *获取订单明细分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<OrderItem> getOrderItemPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

}
