package com.dyb.platforms.fixfunds.services.business.sendaddress.service;


import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ISendAddressService {

    /**
     * 新增寄送地址
     * @param cendAddress 寄送地址对象
     * @return 寄送地址对象
     */
    public SendAddress createSendAddress(SendAddress cendAddress);

    /**
     * 根据code删除寄送地址
     * @param cendAddressCode 寄送地址code
     * @return true表示成功 false表示失败
     */
    public Boolean deleteSendAddress(String cendAddressCode);

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<SendAddress> getSendAddressList(QueryParams wheres, int skip, int size, boolean detail);

}
