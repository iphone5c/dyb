package com.dyb.platforms.fixfunds.services.business.blacklist.service;


import com.dyb.platforms.fixfunds.services.business.blacklist.entity.Blacklist;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IBlacklistService {

    /**
     * 根据code查询黑名单信息
     * @param clacklistCode 黑名单Code
     * @return 黑名单信息
     */
    public Blacklist getBlacklistByCode(String clacklistCode);

    /**
     *获取黑名单分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Blacklist> getBlacklistPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

}
