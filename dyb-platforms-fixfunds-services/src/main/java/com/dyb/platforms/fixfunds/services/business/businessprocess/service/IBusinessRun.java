package com.dyb.platforms.fixfunds.services.business.businessprocess.service;

/**
 * 业务单执行接口
 * Created by Administrator on 2016/3/8.
 */
public interface IBusinessRun  {
    /**
     * 获取当前业务执行服务可执行的业务类型
     *
     * @return 当前业务执行服务可执行的业务类型
     */
    int getBusinessType();

    /**
     * 执行业务单
     *
     * @param docCode 业务单编号
     * @param runner  执行者
     */
    void run(String docCode, String runner);

    /**
     * 确认业务单(仅在异步业务时使用)
     *
     * @param docCode 业务单编号
     * @param args    确认参数对象
     * @param runner  确认者
     */
    void confirm(String docCode, Object args, String runner);
}
