package com.dyb.platforms.fixfunds.services.business.conversion.service;

import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IConversionService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<Conversion> getConversionList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的转换信使豆记录信息
     * @param conversionCode 转换信使豆记录code
     * @return 转换信使豆记录信息
     */
    public Conversion getConversionByCode(String conversionCode);

    /**
     *获取转换信使豆记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Conversion> getConversionPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);
}
