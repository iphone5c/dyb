package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.services.business.donation.service.IDonationService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/client/merchant/donation")
public class ClientDonationController extends BaseController {

    public Logger log = Logger.getLogger(ClientDonationController.class);//日志

    @Autowired
    private IDonationService donationService;

    /**
     * 获取直捐记录表分页
     * @param status 1:近一周 2：近一个月  3：近一个季度  4：近半年
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getDonationPageList")
    public Object getDonationPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,@RequestParam(required=false,defaultValue="1")int status) throws ParseException {
        log.info("移动端获取直捐记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("donationAccount",DybUtils.getCurrentAccount(request).getAccountCode());
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(),-7),DybConvert.DATEFORMAT_DATA_EN_LONG)+" 00:00:00",DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("donationTime",min,new Date());
        }else if (status==2){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("donationTime",min,new Date());
        }else if (status==3){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -3), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("donationTime",min,new Date());
        }else if (status==4){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -6), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("donationTime",min,new Date());
        }
        queryParams.addOrderBy("donationTime",false);
        return result(donationService.getDonationPageList(queryParams, pageIndex, pageSize, true));
    }

}
