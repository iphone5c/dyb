package com.dyb.platforms.fixfunds.services.business.donationreceiptapply.service;


import com.dyb.platforms.fixfunds.services.business.donationreceiptapply.entity.DonationReceiptApply;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IDonationReceiptApplyService {

    /**
     * 根据code查询捐赠收据申请信息
     * @param donationReceiptApplyCode 捐赠收据申请Code
     * @return 捐赠收据申请信息
     */
    public DonationReceiptApply getDonationReceiptApplyByCode(String donationReceiptApplyCode);

    /**
     *获取捐赠收据申请分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<DonationReceiptApply> getDonationReceiptApplyPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 申请捐赠收据
     * @param accountCode 捐赠人code
     * @param receiptMoney 收据金额
     * @param isCertificate 是否附带捐赠证书 true表示是，false表示否
     * @return true 表示操作成功 false表示操作失败
     */
    public boolean donationReceiptApply(String accountCode,Double receiptMoney,Boolean isCertificate);

    /**
     * 新增捐赠收据申请
     * @param donationReceiptApply
     * @return
     */
    public DonationReceiptApply createDonationReceiptApply(DonationReceiptApply donationReceiptApply);

}
