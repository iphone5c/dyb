package com.dyb.platforms.fixfunds.services.business.donation.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.donation.dao.IDonationDao;
import com.dyb.platforms.fixfunds.services.business.donation.entity.Donation;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICodeBuilder codeBuilder;

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

    /**
     * 直捐
     * @param account 直捐账户
     * @param messengerBeanType 直捐类型
     * @param donationMessengerBean 直捐数量
     * @param tradePassword 二级密码
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean donation(Account account, MessengerBeanType messengerBeanType, Double donationMessengerBean, String tradePassword) {
        if (account==null)
            throw new DybRuntimeException("直捐人不能为空");
        if (messengerBeanType==null)
            throw new DybRuntimeException("直捐类型不能为空");
        if (donationMessengerBean<=0)
            throw new DybRuntimeException("直捐数量必须大于零");
        if (DybUtils.isEmptyOrNull(tradePassword))
            throw new DybRuntimeException("二级密码不能为空");
        if (!DybUtils.verifyPassword(tradePassword,account.getTradePassword()))
            throw new DybRuntimeException("二级密码输入错误");
        //TODO 直捐余额判断
        Donation donation=new Donation();
        donation.setDonationType(messengerBeanType);
        donation.setDonationMessengerBean(donationMessengerBean);
        Donation temp=this.createDonation(donation);
        return temp!=null?true:false;
    }

    /**
     * 添加直捐记录
     * @param donation
     * @return
     */
    @Override
    public Donation createDonation(Donation donation) {
        if (donation==null)
            throw new DybRuntimeException("新增直捐记录时,donation不能为空");
        if (donation.getDonationMessengerBean()<=0)
            throw new DybRuntimeException("新增直捐记录时,直捐数量必须大于零");
        if (donation.getDonationType()==null)
            throw new DybRuntimeException("新增直捐记录时,直捐类型不能为空");
        if (DybUtils.isEmptyOrNull(donation.getDonationAccount()))
            throw new DybRuntimeException("新增直捐记录时,直捐人不能为空");
        Account account=accountService.getAccountByCode(donation.getDonationAccount(),false);
        if (account==null)
            throw new DybRuntimeException("新增直捐记录时,找不到此直捐人信息");
        donation.setDonationCode(codeBuilder.getDonationCode());
        donation.setDonationTime(new Date());
        donation.setCreateTime(new Date());
        int info=donationDao.insertObject(donation);
        return info>0?donation:null;
    }

}
