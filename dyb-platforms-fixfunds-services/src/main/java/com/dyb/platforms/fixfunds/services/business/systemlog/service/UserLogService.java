package com.dyb.platforms.fixfunds.services.business.systemlog.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.systemlog.dao.IUserLogDao;
import com.dyb.platforms.fixfunds.services.business.systemlog.entity.UserLog;
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

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("userLogService")
public class UserLogService extends BaseService implements IUserLogService {

    public Logger log = Logger.getLogger(UserLogService.class);//日志

    @Autowired
    private IUserLogDao userLogDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     * 新增用户日志记录
     * @param userLog
     * @return
     */
    @Override
    public UserLog createUserLog(UserLog userLog) {
        if (userLog==null)
            throw new DybRuntimeException("新增用户操作日志，userlog不能为空或null");
        if (DybUtils.isEmptyOrNull(userLog.getUserCode()))
            throw new DybRuntimeException("新建用户操作日志，必须指明操作用户");
        if (DybUtils.isEmptyOrNull(userLog.getUserLoginIp()))
            throw new DybRuntimeException("新建用户操作日志，必须指明操作用户的IP地址");
        if (DybUtils.isEmptyOrNull(userLog.getOperationType()))
            throw new DybRuntimeException("新建用户操作日志，必须指明操作类型");
        userLog.setUserLogCode(codeBuilder.getUserLogCode());
        userLog.setCreateTime(new Date());
        int info=userLogDao.insertObject(userLog);
        return info>0?userLog:null;
    }

    /**
     *获取用户操作日志分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<UserLog> getUserLogPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return userLogDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }
}
