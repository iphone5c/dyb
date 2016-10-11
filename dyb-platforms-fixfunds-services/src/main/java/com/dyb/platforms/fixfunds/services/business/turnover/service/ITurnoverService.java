package com.dyb.platforms.fixfunds.services.business.turnover.service;

import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ITurnoverService {

    /**
     * 根据code获取营业额信息
     * @param turnoverCode 营业code
     * @return 营业额信息
     */
    public Turnover getTurnoverByCode(String turnoverCode);

    /**
     *获取用户操作日志分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Turnover> getTurnoverPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @return 对象列表
     */
    public List<Turnover> getTurnoverList(QueryParams wheres);

}
