package com.dyb.platforms.fixfunds.services.business.timeservice;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.accountincentive.entity.AccountIncentive;
import com.dyb.platforms.fixfunds.services.business.accountincentive.service.IAccountIncentiveService;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.business.order.entity.Order;
import com.dyb.platforms.fixfunds.services.business.order.entity.em.OrderStatus;
import com.dyb.platforms.fixfunds.services.business.order.service.IOrderService;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.entity.RecommendIncentive;
import com.dyb.platforms.fixfunds.services.business.recommendincentive.service.IRecommendIncentiveService;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;
import com.dyb.platforms.fixfunds.services.business.turnover.entity.em.BenefitPriceStatus;
import com.dyb.platforms.fixfunds.services.business.turnover.service.ITurnoverService;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 魏源 on 2015/7/22 0022.
 */
@Transactional
@Service("timeService")
public class TimeService implements ITimeService {

    public Logger log = Logger.getLogger(TimeService.class);//日志

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private ITurnoverService turnoverService;
    @Autowired
    private IAccountIncentiveService accountIncentiveService;
    @Autowired
    private IMessengerBeanService messengerBeanService;
    @Autowired
    private ISystemParamsService systemParamsService;
    @Autowired
    private IRecommendIncentiveService recommendIncentiveService;


    /**
     * 启动
     *
     */
    @Scheduled(cron= "0 35 19 * * ?" )
    @Override
    public void runMinute() {
        System.out.println("11111");
//        try {
//            Date nowTime=YcUtils.dateAddMinute(new Date(),1/60);
//            log.info("每五分钟执行跑批开始");
//            //处理预挂牌产品
//            preListingProduct(nowTime);
//            //处理挂牌中产品
//            listingProduct(nowTime);
//            log.info("每五分钟执行跑批结束");
//        }catch (Exception e){
//            log.error("错误信息："+e.getMessage());
//            e.printStackTrace();
//        }
    }

    /**
     * 启动(每天)
     *
     */
    @Scheduled(cron= "0 0 0 * * ?" )
    @Override
    public void runDay(){
//        try {
//            Date nowTime=YcUtils.dateAddMinute(new Date(),1/60);
//            log.info("每天执行跑批开始");
//            log.info("处理预发行产品");
//            //处理预发行产品
//            preIssueProduct(nowTime);
//            log.info("处理发行中产品");
//            //处理发行中产品
//            issueProduct(nowTime);
//            log.info("处理兑付中的产品（改变产品状态）");
//            //处理兑付中的产品（改变产品状态）
//            autodedtProductAfter();
//            log.info("初始化标志位开始");
//            QueryParams queryParams=new QueryParams();
//            queryParams.addParameter("productstatus", ProductStatus.挂牌中);
//            queryParams.addParameter("parentproductid","root");
//            List<Product> productList=productService.getProductList(queryParams,0,-1,true);
//            for (Product product:productList){
//                if (product.getSaleschannels()==SalesChannel.兴业银行||product.getSaleschannels()==SalesChannel.网金社||product.getSaleschannels()==SalesChannel.招财宝)
//                    continue;
//                product.setFlag(0);
//                productService.modifyProduct(product);
//            }
//            log.info("初始化标志位结束");
//            log.info("每天执行跑批结束");
//        }catch (Exception e){
//            log.error("错误信息："+e.getMessage());
//            e.printStackTrace();
//        }

    }

    /**
     * 我的营业额计算
     * @throws Exception
     */
    @Override
    public void merchantTurnover(Date date) throws Exception {
        Date min = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 23:59:59", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountType", AccountType.商家);
        queryParams.addParameter("accountStatus", AccountStatus.正常);
        List<Account> accountList=accountService.getAccountList(queryParams,0,-1,true);
        Turnover[] turnovers=new Turnover[accountList.size()];
        int i=0;
        for (Account account:accountList){
            Merchant merchant=merchantService.getMerchantByCode(account.getAccountCode());
            if (merchant==null)
                throw new DybRuntimeException("未找到此商家信息");
            queryParams=new QueryParams();
            queryParams.addParameter("merchantCode",account.getAccountCode());
            queryParams.addParameter("status", OrderStatus.已结算);
            queryParams.addParameterByRange("tradeTime",min,max);
            List<Order> orderList=orderService.getOrderList(queryParams,0,-1,true);
            if (orderList==null||orderList.size()<=0)
                continue;
            Turnover turnover=new Turnover();
            // 营业总额
            Double turnoverPrice=0d;
            // 应交让利款
            Double benefitPrice=0d;
            // 已交让利款
            Double yetBenefitPrice=0d;
            // 剩余待交让利款
            Double residueBenefitPrice=0d;
            for (Order order:orderList){
                turnoverPrice+=order.getPrice();
                benefitPrice+=order.getPrice()*merchant.getIncentiveMode()/100;
            }
            residueBenefitPrice=benefitPrice-yetBenefitPrice;

            turnover.setTurnoverTime(date);
            turnover.setTurnoverPrice(turnoverPrice);
            turnover.setBenefitPrice(benefitPrice);
            turnover.setYetBenefitPrice(yetBenefitPrice);
            turnover.setResidueBenefitPrice(residueBenefitPrice);
            turnover.setBenefitPriceStatus(BenefitPriceStatus.未让利);
            turnover.setAccountCode(account.getAccountCode());
            turnovers[i]=turnover;
            i++;
        }
        boolean flag=turnoverService.createTurnoverList(turnovers);
        if (!flag)
            throw new DybRuntimeException("营业额批量添加失败");

    }

    /**
     * 商家激励寄送
     * @param date
     * @throws Exception
     */
    @Override
    public void merchantIncentive(Date date) throws Exception {
        Date min = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 23:59:59", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
        for (int i=1;i<=30;i++){
            //营业总额
            Double turnoverPrice=0d;
            //爱心数量
            Double loveNum=0d;
            //爱心单价
            Double lovePrice=0d;
            QueryParams queryParams=new QueryParams();
            queryParams.addParameter("incentiveMode",i);
            List<Merchant> merchantList=merchantService.getMerchantList(queryParams,0,-1,true);
            if (merchantList==null||merchantList.size()<=0)
                continue;
            //计算营业总额和爱心数量
            for (Merchant merchant:merchantList){
                queryParams=new QueryParams();
                queryParams.addParameter("accountCode",merchant.getAccountCode());
                queryParams.addParameterByRange("turnoverTime",min,max);
                List<Turnover> turnoverList=turnoverService.getTurnoverList(queryParams);
                if (turnoverList!=null&&turnoverList.size()>0){
                    Turnover turnover=turnoverList.get(0);
                    turnoverPrice+=turnover.getTurnoverPrice();
                    loveNum+=turnover.getTurnoverPrice()/500;
                }
            }
            SystemParams systemParams=systemParamsService.getSystemParamsByKey(String.valueOf(i));
            if (systemParams==null)
                throw new DybRuntimeException("二次分配比例系统参数没有配置，激励模型："+i+"%");
            String[] computerParam=systemParams.getSystemParamsValue().split(",");
            Double proportion=Double.parseDouble(computerParam[2]);
            //计算爱心单价
            lovePrice=(turnoverPrice*proportion/100)/loveNum;

            AccountIncentive[] accountIncentives=new AccountIncentive[merchantList.size()];
            MessengerBean[] messengerBeans=new MessengerBean[merchantList.size()];
            int j=0;
            for (Merchant merchant:merchantList){
                queryParams=new QueryParams();
                queryParams.addParameter("accountCode",merchant.getAccountCode());
                queryParams.addParameterByRange("turnoverTime",min,max);
                List<Turnover> turnoverList=turnoverService.getTurnoverList(queryParams);
                if (turnoverList!=null&&turnoverList.size()>0){
                    Turnover turnover=turnoverList.get(0);
                    AccountIncentive accountIncentive=new AccountIncentive();
                    Double loveNumTemp=turnover.getTurnoverPrice()/500;
                    accountIncentive.setAccountIncentiveTime(date);
                    accountIncentive.setLoveNum(loveNumTemp);
                    accountIncentive.setLovePrice(lovePrice);
                    accountIncentive.setMessengerBean(loveNumTemp * lovePrice);
                    accountIncentive.setAccountCode(merchant.getAccountCode());
                    accountIncentive.setIncentiveMode(merchant.getIncentiveMode());

                    MessengerBean messengerBean=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(merchant.getAccountCode(), MessengerBeanType.待提供发票);
                    if (messengerBean==null)
                        throw new DybRuntimeException("此商家账户待提供发票的信使豆信息没有找到，accountCode:"+merchant.getAccountCode());
                    //信使豆数量
                    Double mBean=messengerBean.getMessengerBean();
                    messengerBean.setMessengerBean(mBean+loveNumTemp * lovePrice);
                    accountIncentives[j]=accountIncentive;
                    messengerBeans[j]=messengerBean;
                    j++;
                }
            }

            boolean mBeanFlag=messengerBeanService.updateMessengerBeanList(messengerBeans);
            boolean accountIncentiveflag=accountIncentiveService.createAccountIncentiveList(accountIncentives);
            if (!(mBeanFlag&&accountIncentiveflag))
                throw new DybRuntimeException("商家账户激励批量操作失败");

        }

    }

    /**
     * 推荐激励计算
     * @param date
     * @throws Exception
     */
    @Override
    public void recommendIncentive(Date date) throws Exception {
        Date min = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 00:00:00", DybConvert.DATEFORMAT_DATETIME_EN_LONG);
        Date max = DybConvert.toDate(DybConvert.dateToString(date, DybConvert.DATEFORMAT_DATA_EN_LONG) + " 23:59:59", DybConvert.DATEFORMAT_DATETIME_EN_LONG);

        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("status", OrderStatus.已结算);
        queryParams.addParameterByRange("tradeTime",min,max);
        List<Order> orderList=orderService.getOrderList(queryParams,0,-1,true);
        RecommendIncentive[] recommendMembers=new RecommendIncentive[orderList.size()];
        RecommendIncentive[] recommendMerchants=new RecommendIncentive[orderList.size()];
        int i=0;
        for (Order order:orderList){
            //第一步  推荐信使
            Account member=accountService.getAccountByCode(order.getMemberCode(),false);
            if (member==null)
                throw new DybRuntimeException("找不到此信使信息");
            SystemParams systemParams=systemParamsService.getSystemParamsByKey(order.getIncentiveMode().toString());
            if (systemParams==null)
                throw new DybRuntimeException("二次分配比例系统参数没有配置，激励模型："+order.getIncentiveMode()+"%");
            String[] computerParam=systemParams.getSystemParamsValue().split(",");
            Double proportion=Double.parseDouble(computerParam[3]);
            RecommendIncentive recommendMember=new RecommendIncentive();
            recommendMember.setRecommendIncentiveTime(date);
            recommendMember.setRecommendAccountCode(member.getAccountCode());
            recommendMember.setMessengerBean(order.getPrice()*proportion);
            recommendMember.setIncentiveType(order.getIncentiveMode());
            recommendMember.setIncentiveSources(AccountType.信使);
            recommendMember.setAccountCode(member.getReferrerCode());

            //第二部  推荐商家
            Account merchant=accountService.getAccountByCode(order.getMerchantCode(),false);
            if (member==null)
                throw new DybRuntimeException("找不到此商家信息");
            proportion=Double.parseDouble(computerParam[5]);
            RecommendIncentive recommendMerchant=new RecommendIncentive();
            recommendMerchant.setRecommendIncentiveTime(date);
            recommendMerchant.setRecommendAccountCode(merchant.getAccountCode());
            recommendMerchant.setMessengerBean(order.getPrice()*proportion);
            recommendMerchant.setIncentiveType(order.getIncentiveMode());
            recommendMerchant.setIncentiveSources(AccountType.商家);
            recommendMerchant.setAccountCode(merchant.getReferrerCode());

            recommendMembers[i]=recommendMember;
            recommendMerchants[i]=recommendMerchant;
            i++;
        }
        boolean memberFlag=recommendIncentiveService.createRecommendIncentiveList(recommendMembers);
        if (!memberFlag)
            throw new DybRuntimeException("推荐信使激励记录添加失败");
        boolean merchantFlag=recommendIncentiveService.createRecommendIncentiveList(recommendMerchants);
        if (!memberFlag)
            throw new DybRuntimeException("推荐商家激励记录添加失败");
    }


}
