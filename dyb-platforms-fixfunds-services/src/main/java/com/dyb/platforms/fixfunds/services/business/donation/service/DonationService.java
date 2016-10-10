package com.dyb.platforms.fixfunds.services.business.donation.service;

import com.dyb.platforms.fixfunds.services.business.donation.dao.IDonationDao;
import com.dyb.platforms.fixfunds.services.business.donation.entity.Donation;
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
@Service("donationService")
public class DonationService extends BaseService implements IDonationService {

    public Logger log = Logger.getLogger(DonationService.class);//日志

    @Autowired
    private IDonationDao donationDao;

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
    public List<Donation> getDonationList(QueryParams wheres, int skip, int size, boolean detail) {
        return donationDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的直捐记录信息
     * @param donationCode 直捐记录code
     * @return 直捐记录信息
     */
    @Override
    public Donation getDonationByCode(String donationCode) {
        if (DybUtils.isEmptyOrNull(donationCode))
            throw new DybRuntimeException("根据直捐记录code获取直捐记录信息时，直捐记录code不能为空");
        return donationDao.getObject(donationCode,true);
    }

    /**
     *获取直捐记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Donation> getDonationPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return donationDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
