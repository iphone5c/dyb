package com.dyb.platforms.fixfunds.services.business.businessprocess.service;

import com.dyb.platforms.fixfunds.services.business.binarydata.service.IBinaryDataService;
import com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessDocHeadDao;
import com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessPaperDao;
import com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDoc;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessDocHead;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessPaper;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessRunRecord;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.em.BusinessDocStatus;
import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.em.BusinessPaperType;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.BusinessDefined;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/3/9.
 */
@Service
@Transactional
public class BusinessDocService implements IBusinessDocSerice {

    @Autowired
    private IBusinessDocHeadDao businessDocHeadDao;
    @Autowired
    private IBusinessPaperDao businessPaperDao;
    @Autowired
    private IBusinessRunRecordDao businessRunRecordDao;
    @Autowired
    private IBinaryDataService binaryDataService;

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
    @Override
    public BusinessDoc addBusinessDoc(int businessType, String summary, String remark, String createPerson, String linkman, String linkManTel, Object docBody) {
        if (businessType<1||businessType>99)
            throw new DybRuntimeException("businessType参数的取值范围必须是[1,99].");
        if (DybUtils.isEmptyOrNull(summary))
            throw new DybRuntimeException("summary参数不能为null或empty.");
        if (docBody == null)
            throw new DybRuntimeException("docBody参数不能为null.");
        if (DybUtils.isEmptyOrNull(createPerson))
            throw new DybRuntimeException("createPerson参数不能为null.");
        //查询业务定义
        BusinessDefined businessDefined= SettingConfigureationFactory.getBusinessDefinedByBusinessType(businessType);
        if (businessDefined == null)
            throw new DybRuntimeException("未找到指定的业务类型[" + businessType + "]定义.");

        BusinessDocHead docHead=new BusinessDocHead();
        docHead.setDocCode(UUID.randomUUID().toString());
        docHead.setBusinessType(businessType);
        docHead.setSummary(summary);
        docHead.setRemark(remark);
        docHead.setLinkMan(linkman);
        docHead.setLinkManTel(linkManTel);
        docHead.setBusinessDocStatus(BusinessDocStatus.未提交);
        docHead.setRunned(false);
        docHead.setConfirmed(false);
        docHead.setEnable(false);
        docHead.setBodyKeyCode(binaryDataService.addBinaryDataFromString(DybUtils.getJsonSerialize(docBody)));
        docHead.setBodyClassName(docBody.getClass().getName());
        docHead.setCreatePerson(createPerson);
        docHead.setCreateTime(new Date());
        businessDocHeadDao.insertObject(docHead);

        writeRunRecord(docHead.getDocCode(), createPerson, "创建业务单", docHead.getBusinessDocStatus());

        BusinessDoc result = new BusinessDoc();
        result.setDocCode(docHead.getDocCode());
        result.setHead(docHead);
        result.setBody(docBody);

        return result;
    }

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
    @Override
    public BusinessDocHead modifyBusinessDoc(String docCode, String summary, String linkMan, String linkManTel, String remark, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(summary))
            throw new DybRuntimeException("summary参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");

        checkedBusinessStatus(docHead, true, BusinessDocStatus.未提交);

        docHead.setSummary(summary);
        docHead.setRemark(remark);
        docHead.setLinkMan(linkMan);
        docHead.setLinkManTel(linkManTel);

        businessDocHeadDao.updateObject(docHead);

        return docHead;
    }

    /**
     * 设置业务单主体
     *
     * @param docCode 业务单编号
     * @param docBody 业务单主体
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead setBusinessDocBody(String docCode, Object docBody, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        if (docBody == null)
            throw new DybRuntimeException("docBody参数不能为null.");
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, false, BusinessDocStatus.已暂停, BusinessDocStatus.归档中,BusinessDocStatus.已撤销, BusinessDocStatus.已完成);

        if (!DybUtils.isEmptyOrNull(docHead.getBodyKeyCode()))
            binaryDataService.deleteBinaryData(docHead.getBodyKeyCode());
        docHead.setBodyClassName(docBody.getClass().getName());
        docHead.setBodyKeyCode(binaryDataService.addBinaryDataFromString(DybUtils.getJsonSerialize(docBody)));
        businessDocHeadDao.updateObject(docHead);
        return docHead;
    }

    /**
     * 设置业务单要件
     *
     * @param docCode   业务单编号
     * @param docPapers 业务单要件列表
     * @param runner    执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead setBusinessDocPapers(String docCode, List<BusinessPaper> docPapers, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        if (docPapers != null) {
            for (BusinessPaper paper : docPapers) {
                if (DybUtils.isEmptyOrNull(paper.getPaperName())) {
                    throw new DybRuntimeException("docPapers.paperName参数不能为null或empty.");
                }
            }
        }
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, false, BusinessDocStatus.已暂停, BusinessDocStatus.已撤销, BusinessDocStatus.已完成);

        QueryParams params = new QueryParams();
        params.addParameter("docCode", docCode);
        businessPaperDao.deleteObjectByWhere(params);
        if (docPapers != null) {
            for (BusinessPaper paper : docPapers) {
                paper.setDocCode(docCode);
                if (paper.getPaperType() == null)
                    paper.setPaperType(BusinessPaperType.用户要件);
                businessPaperDao.insertObject(paper);
            }
        }
        return docHead;
    }

    /**
     * 将指定业务单设置为已执行
     *
     * @param docCode 业务单编号
     * @param runner  执行者
     */
    @Override
    public void setRunned(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.办理中);
        if (docHead.isRunned())
            throw new DybRuntimeException("指定的业务单[" + docCode + "]已经执行.");

        docHead.setRunned(true);
        businessDocHeadDao.updateObject(docHead);
        writeRunRecord(docHead.getDocCode(), runner, "执行业务单",  docHead.getBusinessDocStatus());
    }

    /**
     * 将指定业务单设置为已确认
     *
     * @param docCode 业务单编号
     * @param runner  执行者
     */
    @Override
    public void setConfirmed(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.办理中);
        if (!docHead.isRunned())
            throw new DybRuntimeException("指定的业务单[" + docCode + "]未执行.");
        if (docHead.isConfirmed())
            throw new DybRuntimeException("指定的业务单[" + docCode + "]已经被确认.");

        docHead.setConfirmed(true);
        businessDocHeadDao.updateObject(docHead);
        writeRunRecord(docHead.getDocCode(), runner, "确认业务单",  docHead.getBusinessDocStatus());
    }

    /**
     * 获取业务单
     *
     * @param docCode 业务单号
     * @param detail  是否返回明细属性
     * @return 业务单对象
     */
    @Override
    public BusinessDoc getBusinessDoc(String docCode, boolean detail) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        BusinessDoc result = null;
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, detail);
        if (docHead != null) {
            result = newBusinessDoc(docHead, detail);
        }
        return result;
    }

    /**
     * 获取业务单数目(仅包含del=false)
     *
     * @param param 查询参数
     * @return 业务单数目
     */
    @Override
    public int getBusinessDocCount(QueryParams param) {
        if (param == null)
            param = new QueryParams();
        param.addParameter("del", false);
        return getAllBusinessDocCount(param);
    }

    /**
     * 获取业务单列表(仅包含del=false)
     *
     * @param param  查询参数(参数的映射对为BusinessDocHead)
     * @param skip   跳过的数目
     * @param size   返回的最大数目
     * @param detail 是否返回明细属性
     * @return 业务单列表
     */
    @Override
    public List<BusinessDoc> getBusinessDocList(QueryParams param, int skip, int size, boolean detail) {
        if (param == null)
            param = new QueryParams();
        param.addParameter("enable", false);
        return getAllBusinessDocList(param, skip, size, detail);
    }

    /**
     * 获取业务单分页列表(仅包含del=false)
     *
     * @param param     查询参数(参数的映射对为BusinessDocHead)
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @param detail    是否返回明细属性
     * @return 业务单分页列表
     */
    @Override
    public PageList<BusinessDoc> getBusinessDocPagedList(QueryParams param, int pageIndex, int pageSize, boolean detail) {
        if (param == null)
            param = new QueryParams();
        param.addParameter("enable", false);
        return getAllBusinessDocPagedList(param, pageIndex, pageSize, detail);
    }

    /**
     * 获取所有业务单数目（查询范围包括del=true）
     *
     * @param param 查询参数(参数的映射对为BusinessDocHead)
     * @return 业务单数目
     */
    @Override
    public int getAllBusinessDocCount(QueryParams param) {
        return businessDocHeadDao.queryCount(param);
    }

    /**
     * 获取所有业务单列表（查询范围包括del=true）
     *
     * @param param  查询参数(参数的映射对为BusinessDocHead)
     * @param skip   跳过的数目
     * @param size   返回的最大数目
     * @param detail 是否返回明细属性
     * @return 业务单列表
     */
    @Override
    public List<BusinessDoc> getAllBusinessDocList(QueryParams param, int skip, int size, boolean detail) {
        List<BusinessDocHead> list = businessDocHeadDao.queryList(param, skip, size, detail);
        List<BusinessDoc> result = new ArrayList<>();
        for (BusinessDocHead docHead : list)
            result.add(newBusinessDoc(docHead, detail));
        return result;
    }

    /**
     * 获取所有业务单分页列表（查询范围包括del=true）
     *
     * @param param     查询参数(参数的映射对为BusinessDocHead)
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @param detail    是否返回明细属性
     * @return 业务单分页列表
     */
    @Override
    public PageList<BusinessDoc> getAllBusinessDocPagedList(QueryParams param, int pageIndex, int pageSize, boolean detail) {
        PageList<BusinessDocHead> paged = businessDocHeadDao.queryListForPaged(param, pageIndex, pageSize, detail);

        List<BusinessDoc> list = new ArrayList<>();
        for (BusinessDocHead docHead : paged.getList())
            list.add(newBusinessDoc(docHead, detail));

        PageList<BusinessDoc> result = new PageList<>();
        result.setPageCount(paged.getPageCount());
        result.setPageIndex(paged.getPageIndex());
        result.setPageSize(paged.getPageSize());
        result.setTotalSize(paged.getTotalSize());
        result.setList(list);

        return result;
    }

    /**
     * 删除业务单(设置del=true)
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     */
    @Override
    public BusinessDocHead deleteBusinessDoc(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.未提交);

        docHead.setEnable(true);
        businessDocHeadDao.updateObject(docHead);
        //TODO: 删除与业务单关联的其它对象
        writeRunRecord(docCode, runner, "删除业务单", docHead.getBusinessDocStatus());
        return docHead;
    }

    /**
     * 提交业务单，将业务单状态置为“审核中”
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead submitBusinessDoc(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.未提交);

        docHead.setBusinessDocStatus(BusinessDocStatus.受理中);
        businessDocHeadDao.updateObject(docHead);

        writeRunRecord(docCode, runner, "提交业务单", docHead.getBusinessDocStatus());

        return docHead;
    }

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
    @Override
    public BusinessDocHead acceptBusinessDoc(String docCode, List<BusinessPaper> papers, boolean pass, String opinion, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.受理中);

        if (pass) {
            docHead.setBusinessDocStatus(BusinessDocStatus.办理中);
            businessDocHeadDao.updateObject(docHead);
            setBusinessDocPapers(docCode, papers, runner);
            writeRunRecord(docCode, runner, "业务单受理通过：" + opinion, docHead.getBusinessDocStatus());
        } else {
            writeRunRecord(docCode, runner, "业务单受理不通过：" + opinion,  docHead.getBusinessDocStatus());
            businessDocHeadDao.updateObject(docHead);
            setBusinessDocPapers(docCode, papers, runner);
            docHead = cancelBusinessDoc(docCode, "受理未通过", runner);
        }

        return docHead;
    }

    /**
     * 办理业务，将业务单状态置为归档中
     *
     * @param docCode 业务单编号
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead attendBusinessDoc(String docCode, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "]." );
        checkedBusinessStatus(docHead, true, BusinessDocStatus.办理中);
        docHead.setBusinessDocStatus(BusinessDocStatus.归档中);
        businessDocHeadDao.updateObject(docHead);
        writeRunRecord(docCode, runner, "完成业务办理",  docHead.getBusinessDocStatus());
        return docHead;
    }

    /**
     * 归档业务单
     *
     * @param docCode 业务单编号
     * @param papers  档案列表
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead archiveBusinessDoc(String docCode, List<BusinessPaper> papers, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.归档中);
        setBusinessDocPapers(docCode, papers, runner);
        docHead.setBusinessDocStatus(BusinessDocStatus.已完成);
        businessDocHeadDao.updateObject(docHead);
        writeRunRecord(docCode, runner, "业务单归档",docHead.getBusinessDocStatus());
        return docHead;
    }

    /**
     * 撤销业务单，将业务单状态置为“已撤销”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead cancelBusinessDoc(String docCode, String reason, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(reason))
            throw new DybRuntimeException("reason参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, false, BusinessDocStatus.已撤销, BusinessDocStatus.已完成);
        docHead.setBusinessDocStatus(BusinessDocStatus.已撤销);
        //TODO:清除与此业务单相关的事项 caven
        businessDocHeadDao.updateObject(docHead);

        writeRunRecord(docCode, runner, "撤销业务单：" + reason,  docHead.getBusinessDocStatus());

        return docHead;
    }

    /**
     * 暂停业务单，将业务单状态置为“已暂停”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead suspendBusinessDoc(String docCode, String reason, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(reason))
            throw new DybRuntimeException("reason参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "].");
        checkedBusinessStatus(docHead, true, BusinessDocStatus.办理中);

        docHead.setBusinessDocStatus(BusinessDocStatus.已暂停);
        businessDocHeadDao.updateObject(docHead);

        writeRunRecord(docCode, runner, "暂停业务单：" + reason,  docHead.getBusinessDocStatus());

        return docHead;
    }

    /**
     * 恢复暂停的业务单，将业务单状态置为“办理中”
     *
     * @param docCode 业务单编号
     * @param reason  原因
     * @param runner  执行人
     * @return 业务单头对象
     */
    @Override
    public BusinessDocHead activateBusinessDoc(String docCode, String reason, String runner) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(reason))
            throw new DybRuntimeException("reason参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");

        BusinessDocHead docHead = businessDocHeadDao.getObject(docCode, true);
        if (docHead == null || docHead.getEnable())
            throw new DybRuntimeException("未找到指定的业务单[" + docCode + "]." );
        checkedBusinessStatus(docHead, true, BusinessDocStatus.已暂停);

        docHead.setBusinessDocStatus(BusinessDocStatus.办理中);
        businessDocHeadDao.updateObject(docHead);

        writeRunRecord(docCode, runner, "恢复业务单：" + reason, docHead.getBusinessDocStatus());

        return docHead;
    }

    /**
     * 获取业务单操作记录
     *
     * @param keyId 主键
     * @return 业务单操作记录对象
     */
    @Override
    public BusinessRunRecord getBusinessRunRecord(String keyId) {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new DybRuntimeException("keyId参数不能为null或empty.");

        return businessRunRecordDao.getObject(keyId, true);
    }

    /**
     * 获取业务单操作记录数目
     *
     * @param param 查询参数
     * @return 业务单操作记录数目
     */
    @Override
    public int getBusinessRunRecordCount(QueryParams param) {
        return businessRunRecordDao.queryCount(param);
    }

    /**
     * 获取业务单操作记录列表
     *
     * @param param 查询参数
     * @param skip  跳过的数目
     * @param size  返回的最大数目
     * @return 业务单操作记录列表
     */
    @Override
    public List<BusinessRunRecord> getBusinessRunRecordList(QueryParams param, int skip, int size) {
        return businessRunRecordDao.queryList(param, skip, size, true);
    }

    /**
     * 获取业务单操作记录分页列表
     *
     * @param param     查询参数
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @return 业务单操作记录分页列表
     */
    @Override
    public PageList<BusinessRunRecord> getBusinessRunRecordPagedList(QueryParams param, int pageIndex, int pageSize) {
        return businessRunRecordDao.queryListForPaged(param, pageIndex, pageSize, true);
    }

    /**
     * 写业务操作记录
     *
     * @param docCode        业务单编号
     * @param runner         执行人
     * @param content        内容
     * @param businessStatus 业务状态
     * @return 业务操作记录对象
     */
    @Override
    public BusinessRunRecord writeRunRecord(String docCode, String runner, String content, BusinessDocStatus businessStatus) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(runner))
            throw new DybRuntimeException("runner参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(content))
            throw new DybRuntimeException("content参数不能为null或empty.");
        BusinessRunRecord record = new BusinessRunRecord();
        record.setBusinessRunRecordCode(UUID.randomUUID().toString());
        record.setDocCode(docCode);
        record.setRunner(runner);
        record.setContent(content);
        record.setRunTime(new Date());
        record.setBusinessStatus(businessStatus);
        businessRunRecordDao.insertObject(record);
        return record;
    }

    /**
     * 获取已提交的业务单要件列表
     *
     * @param docCode 业务单号
     * @return 业务单要件列表
     */
    @Override
    public List<BusinessPaper> getBusinessPaperList(String docCode) {
        if (DybUtils.isEmptyOrNull(docCode))
            throw new DybRuntimeException("docCode参数不能为null或empty.");
        QueryParams params = new QueryParams();
        params.addParameter("docCode", docCode);
        return businessPaperDao.queryList(params, 0, -1, true);
    }

    private void checkedBusinessStatus(BusinessDocHead docHead, boolean pass, BusinessDocStatus... statuses) {
        if (pass) {
            String msg = "";
            for (BusinessDocStatus status : statuses) {
                msg += status.toString() + ",";
                if (docHead.getBusinessDocStatus().equals(status)) {
                    return;
                }
            }
            if (!msg.equals(""))
                msg = msg.substring(0, msg.length() - 1);
            throw new DybRuntimeException("指定的业务单[" + docHead.getDocCode() + "]未处于[" + msg.substring(1) + "]状态");
        } else {
            for (BusinessDocStatus status : statuses) {
                if (docHead.getBusinessDocStatus().equals(status))
                    throw new DybRuntimeException("指定的业务单[" + docHead.getDocCode() + "]" + status.toString());
            }
        }
    }

    private BusinessDoc newBusinessDoc(BusinessDocHead docHead, boolean detail) {
        BusinessDoc result = new BusinessDoc();
        result.setDocCode(docHead.getDocCode());
        result.setHead(docHead);
        if (detail) {
            if (!DybUtils.isEmptyOrNull(docHead.getBodyKeyCode())) {
                String bodyStr = binaryDataService.getBinaryDataToString(docHead.getBodyKeyCode());
                Object docBody = null;
                try {
                    docBody = DybUtils.getJsonDeserialize(bodyStr, Class.forName(docHead.getBodyClassName()));
                } catch (ClassNotFoundException e) {
                    throw new DybRuntimeException(e);
                }
                result.setBody(docBody);
            }
            QueryParams params = new QueryParams();
            params.addParameter("docCode", docHead.getDocCode());
            result.setPapers(businessPaperDao.queryList(params, 0, -1, true));
        }
        return result;
    }
}
