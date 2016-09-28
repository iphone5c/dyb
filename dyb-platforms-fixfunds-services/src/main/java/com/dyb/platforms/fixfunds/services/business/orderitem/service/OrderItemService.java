package com.dyb.platforms.fixfunds.services.business.orderitem.service;

import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;
import com.dyb.platforms.fixfunds.services.business.commodity.service.ICommodityService;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.orderitem.dao.IOrderItemDao;
import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("orderItemService")
public class OrderItemService extends BaseService implements IOrderItemService {

    public Logger log = Logger.getLogger(OrderItemService.class);//日志

    @Autowired
    private IOrderItemDao orderItemDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICommodityService commodityService;

    /**
     * 新增订单明细
     * @param orderCode 所属订单编号
     * @param orderItem 订单明细对象
     * @return 订单明细对象
     */
    @Override
    public OrderItem createOrderItem(String orderCode,OrderItem orderItem) {
        if (orderItem==null)
            throw new DybRuntimeException("新增订单明细时，order对象不能为空");
        if (DybUtils.isEmptyOrNull(orderCode))
            throw new DybRuntimeException("新增订单明细时，所属订单不能为空");
        if (orderService.getOrderByCode(orderCode)==null)
            throw new DybRuntimeException("新增订单明细时，找不到此订单信息");
        if (DybUtils.isEmptyOrNull(orderItem.getCommodityCode()))
            throw new DybRuntimeException("新增订单明细时，购买的产品不能为空");
        Commodity commodity=commodityService.getCommodityByCode(orderItem.getCommodityCode());
        if (commodity==null)
            throw new DybRuntimeException("新增订单明细时，找不到此产品信息");
        if (orderItem.getTradeAmount()<=0)
            throw new DybRuntimeException("新增订单明细时，购买数量必须是大于零的正整数");

        orderItem.setOrderItemCode(UUID.randomUUID().toString());
        orderItem.setOrderCode(orderCode);
        orderItem.setTradePrice(commodity.getPrice()*orderItem.getTradeAmount());
        orderItem.setCreateTime(new Date());
        int info=orderItemDao.insertObject(orderItem);
        return info>0?orderItem:null;
    }

    /**
     * 根据code查询订单明细信息
     * @param orderItemCode 订单明细Code
     * @return 订单明细信息
     */
    @Override
    public OrderItem getOrderItemByCode(String orderItemCode) {
        if (DybUtils.isEmptyOrNull(orderItemCode))
            throw new DybRuntimeException("查询订单明细信息时，code不能为空或null");
        return orderItemDao.getObject(orderItemCode,true);
    }

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    @Override
    public List<OrderItem> getOrderItemList(QueryParams wheres, int skip, int size, boolean detail) {
        return orderItemDao.queryList(wheres,skip,size,detail);
    }

}
