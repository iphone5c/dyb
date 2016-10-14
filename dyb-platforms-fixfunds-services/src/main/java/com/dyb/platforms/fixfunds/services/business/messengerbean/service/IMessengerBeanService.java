package com.dyb.platforms.fixfunds.services.business.messengerbean.service;

import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IMessengerBeanService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<MessengerBean> getMessengerBeanList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据code获取信使豆
     * @param messengerBeanCode
     * @return
     */
    public MessengerBean getMessengerBeanByCode(String messengerBeanCode);

    /**
     * 查询指定人的所有信使豆
     * @param accountCode 账户code
     * @return
     */
    public List<MessengerBean> getMessengerBeanListByAccountCode(String accountCode);

    /**
     * 查询指定人的指定类型的信使豆信息
     * @param accountCode 账户code
     * @param messengerBeanType 信使豆类型
     * @return
     */
    public MessengerBean getMessengerBeanByAccountCodeAndMessengerType(String accountCode,MessengerBeanType messengerBeanType);

    /**
     * 新增信使豆信息
     * @param messengerBean
     * @return
     */
    public MessengerBean createMessengerBean(MessengerBean messengerBean);

    /**
     * 更新信使豆信息
     * @param messengerBean
     * @return
     */
    public MessengerBean updateMessengerBean(MessengerBean messengerBean);

}
