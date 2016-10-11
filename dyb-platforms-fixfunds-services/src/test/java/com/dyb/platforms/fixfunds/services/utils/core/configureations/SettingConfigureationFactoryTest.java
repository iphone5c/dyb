package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class SettingConfigureationFactoryTest {
    @Autowired
    private ITurnoverService turnoverService;
    @Autowired
    private IAccountService accountService;

    @Test
    public void testGetModelConfigList() throws Exception {
//        System.out.println(DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(),-7),DybConvert.DATEFORMAT_DATA_EN_LONG)+" 00:00:00",DybConvert.DATEFORMAT_DATETIME_EN_LONG));
//        System.out.println(DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(), -1), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG));
//        System.out.println(DybConvert.dateToString(DybUtils.dateAddMonty(new Date(),-10)));

        int status=2;
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode", accountService.getAccountByCode("DF201610082014000001",false).getAccountCode());
        if (status==1){
            Date min = DybConvert.toDate(DybConvert.dateToString(DybUtils.dateAddDay(new Date(), -7), DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
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
        System.out.println(turnoverList);
    }

    @Test
    public void testGetMenuList() throws Exception {

    }

    @Test
    public void testGetBusinessDefinedList() throws Exception {
        List<Menu> menus= SettingConfigureationFactory.getMenuList();
        System.out.println(menus);
    }

    @Test
    public void testGetBusinessDefinedByBusinessType() throws Exception {
//        System.out.println(SettingConfigureationFactory.getAdminLogMap());
        System.out.println(DybUtils.getRandomLetter(2));
    }
}