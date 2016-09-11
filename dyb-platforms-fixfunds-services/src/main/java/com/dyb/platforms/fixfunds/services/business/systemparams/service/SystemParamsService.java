package com.dyb.platforms.fixfunds.services.business.systemparams.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.systemparams.dao.ISystemParamsDao;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
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
@Service("systemParamsService")
public class SystemParamsService extends BaseService implements ISystemParamsService {

    public Logger log = Logger.getLogger(SystemParamsService.class);//日志

    @Autowired
    private ISystemParamsDao systemParamsDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     *获取系统参数配置分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<SystemParams> getSystemParamsPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return systemParamsDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据系统参数配置code查找系统参数配置
     * @param systemParamsCode 系统参数配置code
     * @return 系统参数配置对象
     */
    @Override
    public SystemParams getSystemParamsByCode(String systemParamsCode) {
        if (DybUtils.isEmptyOrNull(systemParamsCode))
            throw new DybRuntimeException("根据code查找系统参数配置信息时，Code不能为空");
        return systemParamsDao.getObject(systemParamsCode,true);
    }
}
