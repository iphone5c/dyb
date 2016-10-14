package com.dyb.platforms.fixfunds.services.business.donation.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.donation.entity.Donation;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IDonationService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<Donation> getDonationList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的直捐记录信息
     * @param donationCode 直捐记录code
     * @return 直捐记录信息
     */
    public Donation getDonationByCode(String donationCode);

    /**
     *获取直捐记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Donation> getDonationPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 直捐
     * @param account 直捐账户
     * @param messengerBeanType 直捐类型
     * @param donationMessengerBean 直捐数量
     * @param tradePassword 二级密码
     * @return true表示操作成功 false表示操作失败
     */
    public boolean donation(Account account,MessengerBeanType messengerBeanType,Double donationMessengerBean,String tradePassword);

    /**
     * 添加直捐记录
     * @param donation
     * @return
     */
    public Donation createDonation(Donation donation);
}
