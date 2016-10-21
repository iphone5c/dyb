package com.dyb.platforms.fixfunds.services.business.order.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.order.dao.IOrderDao;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.entity.em.OrderStatus;
import com.dyb.platforms.fixfunds.services.business.orderitem.entity.OrderItem;
import com.dyb.platforms.fixfunds.services.business.orderitem.service.IOrderItemService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("orderService")
public class OrderService extends BaseService implements IOrderService {

    public Logger log = Logger.getLogger(OrderService.class);//日志

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     * 新增订单
     * @param order 订单对象
     * @param orderItemList 订单明细列表
     * @return 订单对象
     */
    @Override
    public Order createOrder(Order order,List<OrderItem> orderItemList) {
        if (order==null)
            throw new DybRuntimeException("新增订单时，order对象不能为空");
        if (orderItemList==null||orderItemList.size()<=0)
            throw new DybRuntimeException("新增订单时，订单明细不能为空");
        if (DybUtils.isEmptyOrNull(order.getMemberCode()))
            throw new DybRuntimeException("新增订单时，被登记的信使不能为空");
        if (DybUtils.isEmptyOrNull(order.getMerchantCode()))
            throw new DybRuntimeException("新增订单时，登记的商家不能为空");
        if (order.getTradeTime()==null)
            throw new DybRuntimeException("新增订单时，登记时间不能为空");
        Account member=accountService.getAccountByCode(order.getMemberCode(), false);
        Account merchant=accountService.getAccountByCode(order.getMerchantCode(), true);
        if (member==null||member.getAccountType()!= AccountType.信使)
            throw new DybRuntimeException("新增订单时，找不到此信使的信息");
        if (merchant==null||merchant.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("新增订单时，找不到此商家的信息");
        String orderCode=codeBuilder.getOrderCode();
        order.setOrderCode(orderCode);
        order.setStatus(OrderStatus.待提交资料);
        order.setCreateTime(new Date());
        order.setIncentiveMode(merchant.getMerchant().getIncentiveMode());

        for (OrderItem orderItem:orderItemList){
            OrderItem temp=orderItemService.createOrderItem(orderCode,orderItem);
            if (temp==null)
                throw new DybRuntimeException("新增订单时，订单明细添加失败");
            order.setPrice(order.getPrice()+orderItem.getTradePrice());
        }
        int info=orderDao.insertObject(order);
        return info>0?order:null;
    }

    /**
     * 根据code查询订单信息
     * @param orderCode 订单Code
     * @return 订单信息
     */
    @Override
    public Order getOrderByCode(String orderCode) {
        if (DybUtils.isEmptyOrNull(orderCode))
            throw new DybRuntimeException("查询订单信息时，code不能为空或null");
        return orderDao.getObject(orderCode,true);
    }

    /**
     *获取订单分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Order> getOrderPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return orderDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据商户账号code获取订单
     * @param merchantAccount 商户账号code
     * @return
     */
    @Override
    public List<Order> getOrderByMerchantAccount(String merchantAccount) {
        if(DybUtils.isEmptyOrNull(merchantAccount))
            throw new DybRuntimeException("根据商户账号code获取订单时，账号code不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("merchantCode", merchantAccount);
        return orderDao.queryList(queryParams,0,-1,true);
    }

    /**
     * 根据商户账号code获取指定时间范围的订单
     * @param merchantAccount 商户账号code
     * @param min 最小日期
     * @param max 最大日期
     * @return
     */
    @Override
    public List<Order> getOrderByMerchantAccount(String merchantAccount, Date min, Date max) {
        if(DybUtils.isEmptyOrNull(merchantAccount))
            throw new DybRuntimeException("根据商户账号code获取订单时，账号code不能为空");
        if (min==null)
            throw new DybRuntimeException("最小时间不能为空");
        if (max==null)
            throw new DybRuntimeException("最大时间不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("merchantCode", merchantAccount);
        queryParams.addParameterByRange("tradeTime",min,max);
        return orderDao.queryList(queryParams,0,-1,true);
    }

    /**
     * 操作指定订单状态
     * @param orderCode 订单code
     * @param orderStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationOrderStatus(String orderCode, OrderStatus orderStatus) {
        if (DybUtils.isEmptyOrNull(orderCode))
            throw new DybRuntimeException("操作指定订单状态时，code不能为空或null");
        if (orderStatus==null)
            throw new DybRuntimeException("操作指定订单状态时，修改的订单状态不能为空");
        Order order=orderDao.getObject(orderCode,true);
        if (order==null)
            throw new DybRuntimeException("操作指定订单状态时，找不到此订单信息，code："+order);
        order.setStatus(orderStatus);
        int info=orderDao.updateObject(order);
        return info>0?true:false;
    }

    /**
     * 审核通过订单
     * @param orderCode 订单编号
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean approvedOrder(String orderCode) {
        if (DybUtils.isEmptyOrNull(orderCode))
            throw new DybRuntimeException("审核通过订单时，code不能为空或null");
        return this.operationOrderStatus(orderCode,OrderStatus.待提交资料);
    }

    /**
     * 撤销订单
     * @param orderCode 订单编号
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean cancelOrder(String orderCode) {
        if (DybUtils.isEmptyOrNull(orderCode))
            throw new DybRuntimeException("撤销订单时，code不能为空或null");
        return this.operationOrderStatus(orderCode,OrderStatus.已撤销);
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
    public List<Order> getOrderList(QueryParams wheres, int skip, int size, boolean detail) {
        return orderDao.queryList(wheres,skip,size,detail);
    }

}
