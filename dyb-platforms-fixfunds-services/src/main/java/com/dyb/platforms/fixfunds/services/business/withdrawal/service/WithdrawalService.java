package com.dyb.platforms.fixfunds.services.business.withdrawal.service;

import com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("withdrawalService")
public class WithdrawalService extends BaseService implements IWithdrawalService {

    public Logger log = Logger.getLogger(WithdrawalService.class);//日志

    @Autowired
    private IWithdrawalDao withdrawalDao;

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
    public List<Withdrawal> getWithdrawalList(QueryParams wheres, int skip, int size, boolean detail) {
        return withdrawalDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的回购记录信息
     * @param withdrawalCode 回购记录code
     * @return 回购记录信息
     */
    @Override
    public Withdrawal getWithdrawalByCode(String withdrawalCode) {
        if (DybUtils.isEmptyOrNull(withdrawalCode))
            throw new DybRuntimeException("根据回购记录code获取回购记录信息时，回购记录code不能为空");
        return withdrawalDao.getObject(withdrawalCode,true);
    }

    /**
     *获取回购记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Withdrawal> getWithdrawalPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return withdrawalDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
