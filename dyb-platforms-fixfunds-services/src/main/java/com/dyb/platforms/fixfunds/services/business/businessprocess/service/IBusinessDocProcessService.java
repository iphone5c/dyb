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

}
