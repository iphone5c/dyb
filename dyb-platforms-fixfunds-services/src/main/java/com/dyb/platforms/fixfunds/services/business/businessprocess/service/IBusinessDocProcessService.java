package com.dyb.platforms.fixfunds.services.business.businessprocess.service;

import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDocHead;

/**
 * 业务单流程管理接口
 * Created by Administrator on 2016/3/8.
 */
public interface IBusinessDocProcessService {

    /**
     * 提交业务单，创建审核工作流，将业务单状态置为“审核中”
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead submitBusinessDocProcess(String docCode, String runner);

    /**
     * 撤销业务单，将业务单状态置为“已撤销”,终止对应的工作流程
     *
     * @param processId 工作流程ID
     * @param reason    原因
     * @param runner    执行人
     * @return 业务单头对象
     */
    BusinessDocHead cancelBusinessDocProcess(String processId, String reason, String runner);

    /**
     * 删除业务单(设置del=true),终止对应的工作流程
     *
     * @param processId 工作流程ID
     * @param reason    原因
     * @param runner    执行人
     * @return 业务单头对象
     */
    BusinessDocHead deleteBusinessDocProcess(String processId, String reason, String runner);

    /**
     * 暂停业务单，将业务单状态置为“已暂停”,暂停对应的工作流程
     *
     * @param processId 流程实例ID,为null或empty则不使用此条件
     * @param reason    原因
     * @param runner    执行人
     * @return 业务单头对象
     */
    BusinessDocHead suspendBusinessDocProcess(String processId, String reason, String runner);

    /**
     * 恢复暂停的业务单，将业务单状态置为“办理中”,恢复对应的工作流程
     *
     * @param processId 流程实例ID,为null或empty则不使用此条件
     * @param reason    原因
     * @param runner    执行人
     * @return 业务单头对象
     */
    BusinessDocHead activateBusinessDocProcess(String processId, String reason, String runner);
}
