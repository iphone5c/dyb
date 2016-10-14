package com.dyb.platforms.fixfunds.services.business.benefitdonation.service;


import com.dyb.platforms.fixfunds.services.business.benefitdonation.entity.BenefitDonation;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IBenefitDonationService {

    /**
     * 根据code查询让利捐赠信息
     * @param benefitDonationCode 让利捐赠Code
     * @return 让利捐赠信息
     */
    public BenefitDonation getBenefitDonationByCode(String benefitDonationCode);

    /**
     *获取让利捐赠分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<BenefitDonation> getBenefitDonationPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

}
