package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.business.withdrawal.service.IWithdrawalService;
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
@RequestMapping(value = "/client/merchant/withdrawal")
public class ClientWithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(ClientWithdrawalController.class);//日志

    @Autowired
    private IWithdrawalService withdrawalService;

    /**
     * 移动端获取当前登陆用户回购记录表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getWithdrawalPageList")
    public Object getWithdrawalPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize,@RequestParam(required=false,defaultValue="1")int status,String applyStatus) throws ParseException {
        log.info("移动端获取当前登陆用户回购记录表分页");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("withdrawalAccount", getCurrentAccountClient(request).getAccountCode());
        if (!DybUtils.isEmptyOrNull(applyStatus)){
            ApplyStatus flag=null;
            for (ApplyStatus temp:ApplyStatus.values()){
                if (temp.name().equals(applyStatus)){
                    flag=temp;
                    break;
                }
            }
            if (flag==null)
                return validationResult(1001,"申请状态超出规定范围值");
            queryParams.addParameter("applyStatus",flag.name());
        }
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(), -7), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("applyTime",min,new Date());
        }else if (status==2){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("applyTime",min,new Date());
        }else if (status==3){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -3), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("applyTime",min,new Date());
        }else if (status==4){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -6), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("applyTime",min,new Date());
        }
        queryParams.addOrderBy("applyTime",false);
        return result(withdrawalService.getWithdrawalPageList(queryParams,pageIndex,pageSize,true));
    }

}
