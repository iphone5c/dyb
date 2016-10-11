package com.dyb.platforms.fixfunds.merchant.controller.client;

import com.dyb.platforms.fixfunds.merchant.controller.client.model.TurnoverModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
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
 * Created by lenovo on 2016/10/10.
 */
@RestController
@RequestMapping(value = "/client/merchant")
public class ClientTurnoverController extends BaseController {

    public Logger log = Logger.getLogger(ClientMerchantController.class);//日志

    @Autowired
    private ITurnoverService turnoverService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取当前登陆账户营业额列表
     * @param request
     * @param status 1:近7天 2：近一个月  2：近一个季度  3：近半年
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getTurnoverList")
    public Object getTurnoverList(HttpServletRequest request,@RequestParam(required=false,defaultValue="1")int status) throws ParseException {
        log.info("移动端获取当前登陆账户营业额列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", getCurrentAccountClient(request).getAccountCode());
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(),-7),DybConvert.DATEFORMAT_DATA_EN_LONG)+" 00:00:00",DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("turnoverTime",min,new Date());
        }else if (status==2){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("turnoverTime",min,new Date());
        }else if (status==3){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -3), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("turnoverTime",min,new Date());
        }else if (status==4){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -6), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
            queryParams.addParameterByRange("turnoverTime",min,new Date());
        }
        queryParams.addOrderBy("turnoverTime",false);
        List<Turnover> turnoverList=turnoverService.getTurnoverList(queryParams);
        return result(getDatasToTurnover(getCurrentAccountClient(request),turnoverList));
    }

    /**
     * 获取当前登陆用户今日营业额
     * @return
     */
    @RequestMapping(value = "/getTurnoverListToday")
    public Object getTurnoverListToday(HttpServletRequest request) throws ParseException {
        log.info("移动端获取当前登陆用户今日营业额");
        Date min = DybConvert.toDate(DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATA_EN_LONG),DybConvert.DATEFORMAT_DATA_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(),1),DybConvert.DATEFORMAT_DATA_EN_LONG),DybConvert.DATEFORMAT_DATA_EN_LONG);
        List<Order> orderList=orderService.getOrderByMerchantAccount(getCurrentAccountClient(request).getAccountCode(),min,max);
        return result(orderList);
    }

    private Map<String,Object> getDatasToTurnover(Account currentAccount,List<Turnover> turnoverList){
        if (currentAccount.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("不是商家用户");
        Account temp=accountService.getAccountByCode(currentAccount.getAccountCode(),true);

        Double totalMoney=0d;
        Map<String,Object> result=new HashMap<>();
        Map<String,List<TurnoverModel>> turnoverModelMap=new LinkedHashMap<>();
        for (Turnover turnover:turnoverList){
            totalMoney+=turnover.getTurnoverPrice();
            TurnoverModel turnoverModel=new TurnoverModel();
            String date=DybConvert.dateToString(turnover.getTurnoverTime(),DybConvert.DATEFORMAT_DATA_EN_SHORT);
            turnoverModel.setTurnover(turnover);
            turnoverModel.setIncentiveMode(temp.getMerchant().getIncentiveMode());
            List<TurnoverModel> turnoverModels=turnoverModelMap.get(date);
            if (turnoverModels==null){
                List<TurnoverModel> turnoverModelList=new ArrayList<>();
                turnoverModelList.add(turnoverModel);
                turnoverModelMap.put(date,turnoverModelList);
            }else {
                turnoverModels.add(turnoverModel);
            }

        }
        result.put("datas",turnoverModelMap);
        result.put("totalMoney",totalMoney);
        return result;
    }
}
