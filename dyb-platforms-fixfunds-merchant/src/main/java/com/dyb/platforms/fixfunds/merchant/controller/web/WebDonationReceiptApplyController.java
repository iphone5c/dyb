package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.donationreceiptapply.service.IDonationReceiptApplyService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/donationreceiptapply")
public class WebDonationReceiptApplyController extends BaseController {

    public Logger log = Logger.getLogger(WebDonationReceiptApplyController.class);//日志

    @Autowired
    private IDonationReceiptApplyService donationReceiptApplyService;

    /**
     * 获取当前登陆用户捐赠收据申请表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getDonationReceiptApplyPageList")
    public Object getDonationReceiptApplyPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取当前登陆用户捐赠收据申请表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount", DybUtils.getCurrentAccount(request).getAccountCode());
        return result(donationReceiptApplyService.getDonationReceiptApplyPageList(queryParams,pageIndex,pageSize,true));
    }

    /**
     * 捐赠收据申请
     * @param request
     * @param receiptMoney 收据金额
     * @param isCertificate 是否包含证书
     * @return
     */
    @RequestMapping(value = "/donationReceiptApply")
    public Object donationReceiptApply(HttpServletRequest request,Double receiptMoney,Boolean isCertificate){
        log.info("捐赠收据申请");
        if (receiptMoney<=50000)
            return validationResult(1001,"收据金额不小于50000");
        if (isCertificate==null)
            return validationResult(1001,"是否含捐赠证书不能为空");
        boolean flag=donationReceiptApplyService.donationReceiptApply(DybUtils.getCurrentAccount(request).getAccountCode(),receiptMoney,isCertificate);
        if (!flag)
            return validationResult(1001,"捐赠收据申请失败");
        return result("捐赠收据申请成功");
    }

}
