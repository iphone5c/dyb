package com.dyb.platforms.fixfunds.services.business.benefitdonation.service;

import com.dyb.platforms.fixfunds.services.business.benefitdonation.dao.IBenefitDonationDao;
import com.dyb.platforms.fixfunds.services.business.benefitdonation.entity.BenefitDonation;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
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
@Service("benefitDonationService")
public class BenefitDonationService extends BaseService implements IBenefitDonationService {

    public Logger log = Logger.getLogger(BenefitDonationService.class);//日志

    @Autowired
    private IBenefitDonationDao benefitDonationDao;

    /**
     * 根据code查询让利捐赠信息
     * @param benefitDonationCode 让利捐赠Code
     * @return 让利捐赠信息
     */
    @Override
    public BenefitDonation getBenefitDonationByCode(String benefitDonationCode) {
        if (DybUtils.isEmptyOrNull(benefitDonationCode))
            throw new DybRuntimeException("查询让利捐赠信息时，code不能为空或null");
        return benefitDonationDao.getObject(benefitDonationCode,true);
    }

    /**
     *获取让利捐赠分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<BenefitDonation> getBenefitDonationPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return benefitDonationDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
