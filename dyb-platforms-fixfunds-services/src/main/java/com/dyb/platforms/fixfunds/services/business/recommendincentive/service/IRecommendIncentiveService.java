package com.dyb.platforms.fixfunds.services.business.recommendincentive.service;

import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IRecommendIncentiveService {

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    public List<RecommendIncentive> getRecommendIncentiveList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 根据账户code获取默认的推荐激励信息
     * @param recommendIncentiveCode 推荐激励code
     * @return 推荐激励信息信息
     */
    public RecommendIncentive getRecommendIncentiveByCode(String recommendIncentiveCode);

    /**
     *获取推荐激励分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<RecommendIncentive> getRecommendIncentivePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 批量插入推荐激励记录
     * @param recommendIncentives
     * @return true表示操作成功 false表示操作失败
     */
    public boolean createRecommendIncentiveList(RecommendIncentive[] recommendIncentives);
}
