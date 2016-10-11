package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.transfer.entity.Transfer;
import com.dyb.platforms.fixfunds.services.business.transfer.service.ITransferService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/client/merchant/transfer")
public class ClientTransferController extends BaseController {

    public Logger log = Logger.getLogger(ClientTransferController.class);//日志

    @Autowired
    private ITransferService transferService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取获赠信使豆列表
     * @param status 1:今天 2：近一周  3：近一个月  4：近一个季度
     * @return
     */
    @RequestMapping(value = "/getTransferList")
    public Object getTransferList(HttpServletRequest request,@RequestParam(required=false,defaultValue="1")int status) throws ParseException {
        log.info("移动端获取获赠信使豆列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("gainAccount", getCurrentAccountClient(request).getAccountCode());
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(new Date(), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("transferTime",min,new Date());
        }else if (status==2){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(), -7), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("transferTime",min,new Date());
        }else if (status==3){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("transferTime",min,new Date());
        }else if (status==4){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -3), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("transferTime",min,new Date());
        }
        queryParams.addOrderBy("transferTime",false);
        List<Transfer> transferList=transferService.getTransferList(queryParams, 0, -1, true);
        return result(getDatasToTransfer(getCurrentAccountClient(request), transferList));
    }

    private Map<String,Object> getDatasToTransfer(Account currentAccount,List<Transfer> transferList){
        if (currentAccount.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("不是商家用户");
        Account temp=accountService.getAccountByCode(currentAccount.getAccountCode(),true);

        Double messengerBean=0d;
        Map<String,Object> result=new HashMap<>();
        Map<String,List<Transfer>> transferMap=new LinkedHashMap<>();
        for (Transfer transfer:transferList){
            messengerBean+=transfer.getMessengerBean();
            String date=DybConvert.dateToString(transfer.getTransferTime(),DybConvert.DATEFORMAT_DATA_EN_SHORT);
            List<Transfer> transfers=transferMap.get(date);
            if (transfers==null){
                List<Transfer> transferArrayList=new ArrayList<>();
                transferArrayList.add(transfer);
                transferMap.put(date,transferArrayList);
            }else {
                transfers.add(transfer);
            }

        }
        result.put("datas",transferMap);
        result.put("messengerBean",messengerBean);
        return result;
    }

}
