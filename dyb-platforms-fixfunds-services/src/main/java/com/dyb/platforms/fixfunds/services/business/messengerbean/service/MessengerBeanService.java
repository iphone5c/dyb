package com.dyb.platforms.fixfunds.services.business.messengerbean.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.dao.IMessengerBeanDao;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("messengerBeanService")
public class MessengerBeanService extends BaseService implements IMessengerBeanService {

    public Logger log = Logger.getLogger(MessengerBeanService.class);//日志

    @Autowired
    private IMessengerBeanDao messengerBeanDao;
    @Autowired
    private IAccountService accountService;

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    @Override
    public List<MessengerBean> getMessengerBeanList(QueryParams wheres, int skip, int size, boolean detail) {
        return messengerBeanDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据code获取信使豆
     * @param messengerBeanCode
     * @return
     */
    @Override
    public MessengerBean getMessengerBeanByCode(String messengerBeanCode) {
        if (DybUtils.isEmptyOrNull(messengerBeanCode))
            throw new DybRuntimeException("根据code获取信使豆时，code不能为空");
        return messengerBeanDao.getObject(messengerBeanCode,true);
    }

    /**
     * 查询指定人的所有信使豆
     * @param accountCode 账户code
     * @return
     */
    @Override
    public List<MessengerBean> getMessengerBeanListByAccountCode(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("查询指定人的所有信使豆时，accountCode不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        return messengerBeanDao.queryList(queryParams,0,-1,true);
    }

    /**
     * 查询指定人的指定类型的信使豆信息
     * @param accountCode 账户code
     * @param messengerBeanType 信使豆类型
     * @return
     */
    @Override
    public MessengerBean getMessengerBeanByAccountCodeAndMessengerType(String accountCode, MessengerBeanType messengerBeanType) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("查询指定人的指定类型的信使豆信息时，accountCode不能为空");
        if (messengerBeanType==null)
            throw new DybRuntimeException("查询指定人的指定类型的信使豆信息时，信使豆类型不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        queryParams.addParameter("messengerBeanType",messengerBeanType);
        List<MessengerBean> messengerBeanList=messengerBeanDao.queryList(queryParams,0,-1,true);
        return (messengerBeanList!=null&&messengerBeanList.size()>0)?messengerBeanList.get(0):null;
    }

    /**
     * 新增信使豆信息
     * @param messengerBean
     * @return
     */
    @Override
    public MessengerBean createMessengerBean(MessengerBean messengerBean) {
        if (messengerBean==null)
            throw new DybRuntimeException("新增信使豆信息时，messengerBean对象不能为空");
        if (messengerBean.getMessengerBeanType()==null)
            throw new DybRuntimeException("新增信使豆信息时,信使豆类型不能为空");
        if (DybUtils.isEmptyOrNull(messengerBean.getAccountCode()))
            throw new DybRuntimeException("新增信使豆信息时,所属账户不能为空");
        Account temp=accountService.getAccountByCode(messengerBean.getAccountCode(),false);
        if (temp==null)
            throw new DybRuntimeException("新增信使豆信息时，所属账户信息找不到");
        messengerBean.setMessengerBeanCode(UUID.randomUUID().toString());
        int info=messengerBeanDao.insertObject(messengerBean);
        return info>0?messengerBean:null;
    }

    /**
     * 更新信使豆信息
     * @param messengerBean
     * @return
     */
    @Override
    public MessengerBean updateMessengerBean(MessengerBean messengerBean) {
        if (messengerBean==null)
            throw new DybRuntimeException("新增信使豆信息时，messengerBean对象不能为空");
        if (DybUtils.isEmptyOrNull(messengerBean.getMessengerBeanCode()))
            throw new DybRuntimeException("新增信使豆信息时，主键不能为空");
        if (messengerBean.getMessengerBeanType()==null)
            throw new DybRuntimeException("新增信使豆信息时,信使豆类型不能为空");
        if (DybUtils.isEmptyOrNull(messengerBean.getAccountCode()))
            throw new DybRuntimeException("新增信使豆信息时,所属账户不能为空");
        Account temp=accountService.getAccountByCode(messengerBean.getAccountCode(),false);
        if (temp==null)
            throw new DybRuntimeException("新增信使豆信息时，所属账户信息找不到");
        int info = messengerBeanDao.updateObject(messengerBean);
        return info>0?messengerBean:null;
    }

    /**
     * 批量修改信使豆信息
     * @param messengerBeans
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean updateMessengerBeanList(MessengerBean[] messengerBeans) {
        if (messengerBeans==null||messengerBeans.length<=0)
            throw new DybRuntimeException("批量修改信使豆信息不能为空");
        for (MessengerBean messengerBean:messengerBeans){
            if (DybUtils.isEmptyOrNull(messengerBean.getMessengerBeanCode()))
                throw new DybRuntimeException("信使豆编号不能为空");
            if (messengerBean.getMessengerBeanType()==null)
                throw new DybRuntimeException("新增信使豆信息时,信使豆类型不能为空");
            if (DybUtils.isEmptyOrNull(messengerBean.getAccountCode()))
                throw new DybRuntimeException("新增信使豆信息时,所属账户不能为空");
        }
        int info=messengerBeanDao.updateList(messengerBeans);
        return info>0?true:false;
    }

    /**
     * 新建信使豆信息
     * @param accountCode 账户code
     * @param messengerBeanType 类型列表
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean createMessengerBeanByMessType(String accountCode,MessengerBeanType... messengerBeanType) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("所属账户不能为空");
        MessengerBean[] messengerBeans=null;
        int i=0;
        if (messengerBeanType==null||messengerBeanType.length<=0){
            messengerBeans=new MessengerBean[MessengerBeanType.values().length];
            for (MessengerBeanType temp:MessengerBeanType.values()){
                MessengerBean messengerBean=new MessengerBean();
                messengerBean.setMessengerBeanCode(UUID.randomUUID().toString());
                messengerBean.setAccountCode(accountCode);
                messengerBean.setMessengerBean(0d);
                messengerBean.setMessengerBeanType(temp);
                messengerBeans[i]=messengerBean;
                i++;
            }
        }else {
            messengerBeans=new MessengerBean[messengerBeanType.length];
            for (MessengerBeanType temp:messengerBeanType){
                MessengerBean messengerBean=new MessengerBean();
                messengerBean.setMessengerBeanCode(UUID.randomUUID().toString());
                messengerBean.setAccountCode(accountCode);
                messengerBean.setMessengerBean(0d);
                messengerBean.setMessengerBeanType(temp);
                messengerBeans[i]=messengerBean;
                i++;
            }
        }
        int info=messengerBeanDao.insertList(messengerBeans);
        return info>0?true:false;
    }

    /**
     *获取分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<MessengerBean> getMessengerBeanPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return messengerBeanDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
