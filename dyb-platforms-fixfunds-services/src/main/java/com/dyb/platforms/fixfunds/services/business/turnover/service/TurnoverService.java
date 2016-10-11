package com.dyb.platforms.fixfunds.services.business.turnover.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.turnover.dao.ITurnoverDao;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
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
@Service("turnoverService")
public class TurnoverService extends BaseService implements ITurnoverService {

    public Logger log = Logger.getLogger(TurnoverService.class);//日志

    @Autowired
    private ITurnoverDao turnoverDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     * 根据code获取营业额信息
     * @param turnoverCode 营业code
     * @return 营业额信息
     */
    @Override
    public Turnover getTurnoverByCode(String turnoverCode) {
        if (DybUtils.isEmptyOrNull(turnoverCode))
            throw new DybRuntimeException("根据code查询营业额信息时，code不能为空");
        return turnoverDao.getObject(turnoverCode,true);
    }

    /**
     *获取营业额分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Turnover> getTurnoverPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return turnoverDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @return 对象列表
     */
    @Override
    public List<Turnover> getTurnoverList(QueryParams wheres) {
        return turnoverDao.queryList(wheres,0,-1,true);
    }
}
