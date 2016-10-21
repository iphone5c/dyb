package com.dyb.platforms.fixfunds.services.business.withdrawal.service;

import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Invoice;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IWithdrawalService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<Withdrawal> getWithdrawalList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的回购记录信息
     * @param withdrawalCode 回购记录code
     * @return 回购记录信息
     */
    public Withdrawal getWithdrawalByCode(String withdrawalCode);

    /**
     *获取回购记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Withdrawal> getWithdrawalPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 回购申请
     * @param withdrawalType 回购类型
     * @param merssengerBean 回购数量
     * @param tradePassword 二级密码
     * @param withdrawalAccount 回购人code
     * @param invoiceList 发票明细
     * @return true表示操作成功 false表示操作失败
     */
    public boolean withdrawalMessengerBean(MessengerBeanType withdrawalType,Double merssengerBean,String tradePassword,String withdrawalAccount,List<Invoice> invoiceList);

    /**
     * 新增回购信息
     * @param withdrawal
     * @return
     */
    public Withdrawal createWithdrawal(Withdrawal withdrawal);

    /**
     * 操作指定回购状态
     * @param withdrawalCode 回购code
     * @param applyStatus 申请状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationWithdrawalStatus(String withdrawalCode,ApplyStatus applyStatus);

    /**
     * 审核通过回购
     * @param withdrawalCode 回购编号
     * @return true表示操作成功 false表示操作失败
     */
    public boolean approvedWithdrawal(String withdrawalCode);

    /**
     * 审核不通过
     * @param withdrawalCode 回购编号
     * @return true表示操作成功 false表示操作失败
     */
    public boolean cancelWithdrawal(String withdrawalCode);
}
