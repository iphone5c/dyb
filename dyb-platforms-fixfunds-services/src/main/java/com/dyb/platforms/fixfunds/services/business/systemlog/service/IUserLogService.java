package com.dyb.platforms.fixfunds.services.business.systemlog.service;

import com.dyb.platforms.fixfunds.services.business.systemlog.entity.UserLog;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IUserLogService {

    /**
     * 新增用户日志记录
     * @param userLog
     * @return
     */
    public UserLog createUserLog(UserLog userLog);

    /**
     *获取用户操作日志分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<UserLog> getUserLogPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

}
