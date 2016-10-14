package com.dyb.platforms.fixfunds.services.business.donationreceiptapply.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.donationreceiptapply.dao.IDonationReceiptApplyDao;
import com.dyb.platforms.fixfunds.services.business.donationreceiptapply.entity.DonationReceiptApply;
import com.dyb.platforms.fixfunds.services.business.donationreceiptapply.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;
import com.dyb.platforms.fixfunds.services.business.sendaddress.service.ISendAddressService;
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

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("donationReceiptApplyService")
public class DonationReceiptApplyService extends BaseService implements IDonationReceiptApplyService {

    public Logger log = Logger.getLogger(DonationReceiptApplyService.class);//日志

    @Autowired
    private IDonationReceiptApplyDao donationReceiptApplyDao;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ISendAddressService sendAddressService;

    /**
     * 根据code查询捐赠收据申请信息
     * @param donationReceiptApplyCode 捐赠收据申请Code
     * @return 捐赠收据申请信息
     */
    @Override
    public DonationReceiptApply getDonationReceiptApplyByCode(String donationReceiptApplyCode) {
        if (DybUtils.isEmptyOrNull(donationReceiptApplyCode))
            throw new DybRuntimeException("查询捐赠收据申请信息时，code不能为空或null");
        return donationReceiptApplyDao.getObject(donationReceiptApplyCode,true);
    }

    /**
     *获取捐赠收据申请分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<DonationReceiptApply> getDonationReceiptApplyPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return donationReceiptApplyDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 申请捐赠收据
     * @param accountCode 捐赠人code
     * @param receiptMoney 收据金额
     * @param isCertificate 是否附带捐赠证书 true表示是，false表示否
     * @return true 表示操作成功 false表示操作失败
     */
    @Override
    public boolean donationReceiptApply(String accountCode, Double receiptMoney, Boolean isCertificate) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("捐赠人不能为空");
        if (receiptMoney<=50000)
            throw new DybRuntimeException("收据金额不小于50000");
        if (isCertificate==null)
            throw new DybRuntimeException("是否含捐赠证书不能为空");
        Account account=accountService.getAccountByCode(accountCode,false);
        if (account==null)
            throw new DybRuntimeException("找不到此捐赠人信息");
        SendAddress sendAddress=sendAddressService.getSendAddressByDefaultChecked(accountCode);
        if (sendAddress==null)
            throw new DybRuntimeException("此捐款人的收件地址不能为空");
        DonationReceiptApply donationReceiptApply=new DonationReceiptApply();
        donationReceiptApply.setAddress(sendAddress.getAddress());
        donationReceiptApply.setPhone(sendAddress.getPhone());
        donationReceiptApply.setReceiver(sendAddress.getReceiver());
        donationReceiptApply.setPhone(sendAddress.getPhone());
        donationReceiptApply.setIsCertificate(isCertificate);
        donationReceiptApply.setReceiptMoney(receiptMoney);
        donationReceiptApply.setAccountCode(accountCode);
        DonationReceiptApply temp=this.createDonationReceiptApply(donationReceiptApply);
        return temp==null?false:true;
    }

    /**
     * 新增捐赠收据申请
     * @param donationReceiptApply
     * @return
     */
    @Override
    public DonationReceiptApply createDonationReceiptApply(DonationReceiptApply donationReceiptApply) {
        if (donationReceiptApply==null)
            throw new DybRuntimeException("新增捐赠收据申请时，donationReceiptApply不能为空");
        if (DybUtils.isEmptyOrNull(donationReceiptApply.getAddress()))
            throw new DybRuntimeException("新增捐赠收据申请时，收件人地址不能为空");
        if (DybUtils.isEmptyOrNull(donationReceiptApply.getPhone()))
            throw new DybRuntimeException("新增捐赠收据申请时，收件人电话不能为空");
        if (DybUtils.isEmptyOrNull(donationReceiptApply.getReceiver()))
            throw new DybRuntimeException("新增捐赠收据申请时，收件人不能为空");
        if (DybUtils.isEmptyOrNull(donationReceiptApply.getAccountCode()))
            throw new DybRuntimeException("新增捐赠收据申请时，捐赠人不能为空");
        if (donationReceiptApply.getReceiptMoney()<=50000)
            throw new DybRuntimeException("新增捐赠收据申请时，收据金额不小于50000");
        if (donationReceiptApply.isIsCertificate()==null)
            throw new DybRuntimeException("新增捐赠收据申请时，是否含捐赠证书不能为空");
        Account account=accountService.getAccountByCode(donationReceiptApply.getAccountCode(),false);
        if (account==null)
            throw new DybRuntimeException("新增捐赠收据申请时，找不到此捐赠人信息");
        donationReceiptApply.setDonationReceiptApplyCode(codeBuilder.getDonationReceiptApplyCode());
        donationReceiptApply.setApplyStatus(ApplyStatus.待初审);
        donationReceiptApply.setApplyTime(new Date());
        int info=donationReceiptApplyDao.insertObject(donationReceiptApply);
        return info>0?donationReceiptApply:null;
    }

}
