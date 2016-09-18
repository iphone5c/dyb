package com.dyb.platforms.fixfunds.services.business.businessprocess.service;

import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDoc;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDocHead;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessPaper;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessRunRecord;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.em.BusinessDocStatus;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * 业务单管理接口
 * Created by Administrator on 2016/3/8.
 */
public interface IBusinessDocSerice {

    /**
     *新建一个业务单
     * @param businessType 业务单类型
     * @param summary 摘要
     * @param remark 备注
     * @param createPerson 创建人
     * @param linkman 联系人
     * @param linkManTel 联系电话
     * @param docBody 业务单主体
     * @return 业务单对象
     */
     BusinessDoc addBusinessDoc(int businessType, String summary, String remark, String createPerson, String linkman, String linkManTel, Object docBody);

    /**
     * 修改业务单
     *
     * @param docCode      业务单编号
     * @param summary      摘要
     * @param linkMan      联系人
     * @param linkManTel   联系人电话
     * @param remark       备注
     * @param runner       执行人
     * @return 业务单头对象
     */
     BusinessDocHead modifyBusinessDoc(String docCode, String summary, String linkMan, String linkManTel, String remark, String runner);

    /**
     * 设置业务单主体
     *
     * @param docCode 业务单编号
     * @param docBody 业务单主体
     * @param runner  执行人
     * @return 业务单头对象
     */
     BusinessDocHead setBusinessDocBody(String docCode, Object docBody, String runner);

    /**
     * 设置业务单要件
     *
     * @param docCode   业务单编号
     * @param docPapers 业务单要件列表
     * @param runner    执行人
     * @return 业务单头对象
     */
     BusinessDocHead setBusinessDocPapers(String docCode, List<BusinessPaper> docPapers, String runner);

    /**
     * 将指定业务单设置为已执行
     *
     * @param docCode 业务单编号
     * @param runner  执行者
     */
     void setRunned(String docCode, String runner);

    /**
     * 将指定业务单设置为已确认
     *
     * @param docCode 业务单编号
     * @param runner  执行者
     */
     void setConfirmed(String docCode, String runner);

    /**
     * 获取业务单
     *
     * @param docCode 业务单号
     * @param detail  是否返回明细属性
     * @return 业务单对象
     */
     BusinessDoc getBusinessDoc(String docCode, boolean detail);

    /**
     * 根据工作流程实例ID获取业务单
     *
     * @param processId 工作流程ID
     * @param detail    是否返回明细属性
     * @return 业务单对象
     */
     BusinessDoc getBusinessDocByProcessId(String processId, boolean detail);

    /**
     * 获取业务单数目(仅包含del=false)
     *
     * @param param 查询参数
     * @return 业务单数目
     */
     int getBusinessDocCount(QueryParams param);

    /**
     * 获取业务单列表(仅包含del=false)
     *
     * @param param  查询参数(参数的映射对为BusinessDocHead)
     * @param skip   跳过的数目
     * @param size   返回的最大数目
     * @param detail 是否返回明细属性
     * @return 业务单列表
     */
     List<BusinessDoc> getBusinessDocList(QueryParams param, int skip, int size, boolean detail);

    /**
     * 获取业务单分页列表(仅包含del=false)
     *
     * @param param     查询参数(参数的映射对为BusinessDocHead)
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @param detail    是否返回明细属性
     * @return 业务单分页列表
     */
     PageList<BusinessDoc> getBusinessDocPagedList(QueryParams param, int pageIndex, int pageSize, boolean detail);

    /**
     * 获取所有业务单数目（查询范围包括del=true）
     *
     * @param param 查询参数(参数的映射对为BusinessDocHead)
     * @return 业务单数目
     */
     int getAllBusinessDocCount(QueryParams param);

    /**
     * 获取所有业务单列表（查询范围包括del=true）
     *
     * @param param  查询参数(参数的映射对为BusinessDocHead)
     * @param skip   跳过的数目
     * @param size   返回的最大数目
     * @param detail 是否返回明细属性
     * @return 业务单列表
     */
     List<BusinessDoc> getAllBusinessDocList(QueryParams param, int skip, int size, boolean detail);

    /**
     * 获取所有业务单分页列表（查询范围包括del=true）
     *
     * @param param     查询参数(参数的映射对为BusinessDocHead)
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @param detail    是否返回明细属性
     * @return 业务单分页列表
     */
    PageList<BusinessDoc> getAllBusinessDocPagedList(QueryParams param, int pageIndex, int pageSize, boolean detail);

    /**
     * 删除业务单(设置del=true)
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead deleteBusinessDoc(String docCode, String runner);

    /**
     * 设置业务的工作流程实例ID
     *
     * @param docCode   业务单编号
     * @param processId 流程实例ID
     */
    void setBusinessProcessId(String docCode, String processId);

    /**
     * 提交业务单，将业务单状态置为“审核中”
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead submitBusinessDoc(String docCode, String runner);

    /**
     * 受理业务，如果受理不通过将撤销业务
     *
     * @param docCode 业务单编号
     * @param papers  要件列表
     * @param pass    受理是否通过
     * @param opinion 意见
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead acceptBusinessDoc(String docCode, List<BusinessPaper> papers, boolean pass, String opinion, String runner);

    /**
     * 办理业务，将业务单状态置为归档中
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead attendBusinessDoc(String docCode, String runner);

    /**
     * 归档业务单,并将状态置为"已完成"
     *
     * @param docCode 业务单编号
     * @param papers  档案列表
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead archiveBusinessDoc(String docCode, List<BusinessPaper> papers, String runner);

    /**
     * 撤销业务单，将业务单状态置为“已撤销”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead cancelBusinessDoc(String docCode, String reason, String runner);

    /**
     * 暂停业务单，将业务单状态置为“已暂停”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead suspendBusinessDoc(String docCode, String reason, String runner);

    /**
     * 恢复暂停的业务单，将业务单状态置为“办理中”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    BusinessDocHead activateBusinessDoc(String docCode, String reason, String runner);

    /**
     * 获取业务单操作记录
     *
     * @param keyId 主键
     * @return 业务单操作记录对象
     */
    BusinessRunRecord getBusinessRunRecord(String keyId);

    /**
     * 获取业务单操作记录数目
     *
     * @param param 查询参数
     * @return 业务单操作记录数目
     */
    int getBusinessRunRecordCount(QueryParams param);

    /**
     * 获取业务单操作记录列表
     *
     * @param param 查询参数
     * @param skip  跳过的数目
     * @param size  返回的最大数目
     * @return 业务单操作记录列表
     */
    List<BusinessRunRecord> getBusinessRunRecordList(QueryParams param, int skip, int size);

    /**
     * 获取业务单操作记录分页列表
     *
     * @param param     查询参数
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @return 业务单操作记录分页列表
     */
    PageList<BusinessRunRecord> getBusinessRunRecordPagedList(QueryParams param, int pageIndex, int pageSize);

    /**
     * 写业务操作记录
     *
     * @param docCode        业务单编号
     * @param runner         执行人
     * @param content        内容
     * @param taskId         任务ID
     * @param activityId     活动ID
     * @param businessStatus 业务状态
     * @return 业务操作记录对象
     */
    BusinessRunRecord writeRunRecord(String docCode, String runner, String content, String taskId, String activityId, BusinessDocStatus businessStatus);

    /**
     * 获取已提交的业务单要件列表
     *
     * @param docCode 业务单号
     * @return 业务单要件列表
     */
    List<BusinessPaper> getBusinessPaperList(String docCode);
}
