package com.dyb.platforms.fixfunds.services.business.orderitem.service;


import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

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
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<OrderItem> getOrderItemList(QueryParams wheres, int skip, int size, boolean detail);

}
