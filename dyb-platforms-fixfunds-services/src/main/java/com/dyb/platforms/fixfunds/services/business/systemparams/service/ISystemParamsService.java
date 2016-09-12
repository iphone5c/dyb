package com.dyb.platforms.fixfunds.services.business.systemparams.service;

import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ISystemParamsService {

    /**
     *获取系统参数配置分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<SystemParams> getSystemParamsPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据系统参数配置code查找系统参数配置
     * @param systemParamsCode 系统参数配置code
     * @return 系统参数配置对象
     */
    public SystemParams getSystemParamsByCode(String systemParamsCode);

    /**
     * 添加系统参数配置信息
     * @param systemParams 系统参数配置对象
     * @return 系统参数配置对象
     */
    public SystemParams createSystemParams(SystemParams systemParams);

    /**
     * 修改系统参数配置信息
     * @param systemParams 系统参数配置对象
     * @return 系统参数配置对象
     */
    public SystemParams updateSystemParams(SystemParams systemParams);

    /**
     * 根据key获取系统参数配置信息
     * @param systemParamsKey 系统参数配置key
     * @return 系统参数配置
     */
    public SystemParams getSystemParamsByKey(String systemParamsKey);
}
