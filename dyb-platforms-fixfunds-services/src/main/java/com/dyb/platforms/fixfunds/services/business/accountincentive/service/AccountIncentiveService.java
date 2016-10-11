package com.dyb.platforms.fixfunds.services.business.accountincentive.service;

import com.dyb.platforms.fixfunds.services.business.accountincentive.dao.IAccountIncentiveDao;
import com.dyb.platforms.fixfunds.services.business.accountincentive.entity.AccountIncentive;
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
@Service("accountIncentiveService")
public class AccountIncentiveService extends BaseService implements IAccountIncentiveService {

    public Logger log = Logger.getLogger(AccountIncentiveService.class);//日志

    @Autowired
    private IAccountIncentiveDao accountIncentiveDao;

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
    public List<AccountIncentive> getAccountIncentiveList(QueryParams wheres, int skip, int size, boolean detail) {
        return accountIncentiveDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的账户激励信息
     * @param accountIncentiveCode 账户激励code
     * @return 账户激励信息
     */
    @Override
    public AccountIncentive getAccountIncentiveByCode(String accountIncentiveCode) {
        if (DybUtils.isEmptyOrNull(accountIncentiveCode))
            throw new DybRuntimeException("根据账户激励code获取账户激励信息时，账户激励code不能为空");
        return accountIncentiveDao.getObject(accountIncentiveCode,true);
    }

    /**
     *获取账户激励分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<AccountIncentive> getAccountIncentivePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return accountIncentiveDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
