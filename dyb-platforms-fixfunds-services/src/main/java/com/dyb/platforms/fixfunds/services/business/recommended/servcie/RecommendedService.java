package com.dyb.platforms.fixfunds.services.business.recommended.servcie;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.recommended.dao.IRecommendedDao;
import com.dyb.platforms.fixfunds.services.business.recommended.entity.Recommended;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
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
@Service("recommendedService")
public class RecommendedService extends BaseService implements IRecommendedService {

    public Logger log = Logger.getLogger(RecommendedService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IRecommendedDao recommendedDao;

    /**
     * 添加一条新的推荐记录
     * @param recommended 推荐记录
     * @return 推荐记录
     */
    @Override
    public Recommended createRecommended(Recommended recommended) {
        if (recommended==null)
            throw new DybRuntimeException("添加推荐记录时，recommended对象不能为空");
        if (DybUtils.isEmptyOrNull(recommended.getReferrerCode()))
            throw new DybRuntimeException("添加推荐记录时，推荐人不能为空");
        if (recommended.getReferrerType()==null)
            throw new DybRuntimeException("添加推荐记录时，推荐人类型不能为空");
        if (DybUtils.isEmptyOrNull(recommended.getBtjrCode()))
            throw new DybRuntimeException("添加推荐记录时，被推荐人不能为空");
        if (recommended.getBtjrType()==null)
            throw new DybRuntimeException("添加推荐记录时，被推荐人类型不能为空");
        recommended.setRecommendedCode(codeBuilder.getRecommendedCode());
        int info = recommendedDao.insertObject(recommended);
        return info>0?recommended:null;
    }
}
