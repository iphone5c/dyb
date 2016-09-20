package com.dyb.platforms.fixfunds.services.business.recommended.servcie;

import com.dyb.platforms.fixfunds.services.business.recommended.entity.Recommended;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IRecommendedService {

    /**
     * 添加一条新的推荐记录
     * @param recommended 推荐记录
     * @return 推荐记录
     */
    public Recommended createRecommended(Recommended recommended);

}
