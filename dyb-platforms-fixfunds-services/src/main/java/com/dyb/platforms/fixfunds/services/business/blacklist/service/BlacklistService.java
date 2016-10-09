package com.dyb.platforms.fixfunds.services.business.blacklist.service;

import com.dyb.platforms.fixfunds.services.business.blacklist.dao.IBlacklistDao;
import com.dyb.platforms.fixfunds.services.business.blacklist.entity.Blacklist;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("blacklistService")
public class BlacklistService extends BaseService implements IBlacklistService {

    public Logger log = Logger.getLogger(BlacklistService.class);//日志

    @Autowired
    private IBlacklistDao blacklistDao;

    /**
     * 根据code查询黑名单信息
     * @param blacklistCode 黑名单Code
     * @return 黑名单信息
     */
    @Override
    public Blacklist getBlacklistByCode(String blacklistCode) {
        if (DybUtils.isEmptyOrNull(blacklistCode))
            throw new DybRuntimeException("查询黑名单信息时，code不能为空或null");
        return blacklistDao.getObject(blacklistCode,true);
    }

    /**
     *获取黑名单分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Blacklist> getBlacklistPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return blacklistDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
