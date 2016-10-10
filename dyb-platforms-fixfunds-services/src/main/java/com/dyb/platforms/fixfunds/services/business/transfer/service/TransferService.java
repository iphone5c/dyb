package com.dyb.platforms.fixfunds.services.business.transfer.service;

import com.dyb.platforms.fixfunds.services.business.transfer.dao.ITransferDao;
import com.dyb.platforms.fixfunds.services.business.transfer.entity.Transfer;
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
@Service("transferService")
public class TransferService extends BaseService implements ITransferService {

    public Logger log = Logger.getLogger(TransferService.class);//日志

    @Autowired
    private ITransferDao transferDao;

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
    public List<Transfer> getTransferList(QueryParams wheres, int skip, int size, boolean detail) {
        return transferDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的转赠信息
     * @param transferCode 转赠code
     * @return 转赠信息
     */
    @Override
    public Transfer getTransferByCode(String transferCode) {
        if (DybUtils.isEmptyOrNull(transferCode))
            throw new DybRuntimeException("根据转赠code获取转赠信息时，转赠code不能为空");
        return transferDao.getObject(transferCode,true);
    }

    /**
     *获取转赠分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Transfer> getTransferPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return transferDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
