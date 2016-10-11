package com.dyb.platforms.fixfunds.services.business.recommendincentive.service;

import com.dyb.platforms.fixfunds.services.business.recommendincentive.dao.IRecommendIncentiveDao;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;
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
@Service("recommendIncentiveService")
public class RecommendIncentiveService extends BaseService implements IRecommendIncentiveService {

    public Logger log = Logger.getLogger(RecommendIncentiveService.class);//日志

    @Autowired
    private IRecommendIncentiveDao recommendIncentiveDao;

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
    public List<RecommendIncentive> getRecommendIncentiveList(QueryParams wheres, int skip, int size, boolean detail) {
        return recommendIncentiveDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的推荐激励信息
     * @param recommendIncentiveCode 推荐激励code
     * @return 推荐激励信息
     */
    @Override
    public RecommendIncentive getRecommendIncentiveByCode(String recommendIncentiveCode) {
        if (DybUtils.isEmptyOrNull(recommendIncentiveCode))
            throw new DybRuntimeException("根据推荐激励code获取推荐激励信息时，推荐激励code不能为空");
        return recommendIncentiveDao.getObject(recommendIncentiveCode,true);
    }

    /**
     *获取推荐激励分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<RecommendIncentive> getRecommendIncentivePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return recommendIncentiveDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
