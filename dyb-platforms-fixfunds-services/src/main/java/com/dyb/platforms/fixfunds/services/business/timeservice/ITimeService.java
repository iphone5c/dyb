package com.dyb.platforms.fixfunds.services.business.timeservice;

import java.util.Date;

/**
 * Created by 魏源 on 2015/7/22 0022.
 */
public interface ITimeService {

    /**
     * 启动(每分钟)
     *
     */
    public void runMinute();

    /**
     * 启动(每天00:00)
     *
     */
    public void runDay() throws Exception;

    /**
     * 商家营业额计算
     * @param date
     * @throws Exception
     */
    public void merchantTurnover(Date date) throws Exception;

    /**
     * 商家激励寄送
     * @param date
     * @throws Exception
     */
    public void merchantIncentive(Date date) throws Exception;

    /**
     * 推荐激励计算
     * @param date
     * @throws Exception
     */
    public void recommendIncentive(Date date) throws Exception;
}
