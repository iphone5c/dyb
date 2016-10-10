package com.dyb.platforms.fixfunds.services.business.conversion.service;

import com.dyb.platforms.fixfunds.services.business.conversion.dao.IConversionDao;
import com.dyb.platforms.fixfunds.services.business.conversion.entity.Conversion;
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
@Service("conversionService")
public class ConversionService extends BaseService implements IConversionService {

    public Logger log = Logger.getLogger(ConversionService.class);//日志

    @Autowired
    private IConversionDao conversionDao;

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
    public List<Conversion> getConversionList(QueryParams wheres, int skip, int size, boolean detail) {
        return conversionDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的转换信使豆记录信息
     * @param conversionCode 转换信使豆记录code
     * @return 转换信使豆记录信息
     */
    @Override
    public Conversion getConversionByCode(String conversionCode) {
        if (DybUtils.isEmptyOrNull(conversionCode))
            throw new DybRuntimeException("根据转换信使豆记录code获取转换信使豆记录信息时，转换信使豆记录code不能为空");
        return conversionDao.getObject(conversionCode,true);
    }

    /**
     *获取转换信使豆记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Conversion> getConversionPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return conversionDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
