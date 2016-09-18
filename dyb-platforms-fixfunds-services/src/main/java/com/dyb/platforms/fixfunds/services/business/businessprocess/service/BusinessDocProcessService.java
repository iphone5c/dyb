package com.dyb.platforms.fixfunds.services.business.businessprocess.service;

import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDoc;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDocHead;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.BusinessDefined;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/3/11.
 */
@Service
@Transactional
public class BusinessDocProcessService implements IBusinessDocProcessService {

    @Autowired
    private IBusinessDocSerice businessDocSerice;

    /**
     * 提交业务单，创建审核工作流，将业务单状态置为“审核中”
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead submitBusinessDocProcess(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDoc doc = businessDocSerice.getBusinessDoc(docCode, false);
        if (doc == null || doc.getHead().getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");

        BusinessDefined defined = SettingConfigureationFactory.getBusinessDefinedByBusinessType(doc.getHead().getBusinessType());
        if (defined == null)
            throw new DybRuntimeException("未找到指定的业务类型[" + doc.getHead().getBusinessType() + "]定义.");
        businessDocSerice.submitBusinessDoc(docCode, runner);
        String processId = "";
        if (!DybUtils.isEmptyOrNull(defined.getFlowFile()) || !DybUtils.isEmptyOrNull(defined.getFlowKey())) {
            if (DybUtils.isEmptyOrNull(defined.getFlowFile()) || DybUtils.isEmptyOrNull(defined.getFlowKey()))
                throw new DybRuntimeException("业务配置[" + defined.getBusinessType() + "]中未配置flowFile或flowKey.");
            //TODO 改变工作流，拿到工作流ID
//            processId = workProcessMage.startProcess(FLOWTYPE_FIX_BUSINESS, defined.getFlowFile(), defined.getFlowKey(), runner, docCode, null);
        }
        businessDocSerice.setBusinessProcessId(docCode, processId);
        return businessDocSerice.getBusinessDoc(docCode, false).getHead();
    }

    @Override
    public BusinessDocHead cancelBusinessDocProcess(String processId, String reason, String runner) {
        return null;
    }

    @Override
    public BusinessDocHead deleteBusinessDocProcess(String processId, String reason, String runner) {
        return null;
    }

    @Override
    public BusinessDocHead suspendBusinessDocProcess(String processId, String reason, String runner) {
        return null;
    }

    @Override
    public BusinessDocHead activateBusinessDocProcess(String processId, String reason, String runner) {
        return null;
    }
}
