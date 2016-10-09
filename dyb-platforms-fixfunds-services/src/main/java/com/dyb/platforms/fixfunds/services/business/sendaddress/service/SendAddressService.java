package com.dyb.platforms.fixfunds.services.business.sendaddress.service;

import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.sendaddress.dao.ISendAddressDao;
import com.dyb.platforms.fixfunds.services.business.sendaddress.entity.SendAddress;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("sendAddressService")
public class SendAddressService extends BaseService implements ISendAddressService {

    public Logger log = Logger.getLogger(SendAddressService.class);//日志

    @Autowired
    private ISendAddressDao sendAddressDao;
    @Autowired
    private IAccountService accountService;
    /**
     * 新增寄送地址
     * @param sendAddress 寄送地址对象
     * @return 寄送地址对象
     */
    @Override
    public SendAddress createSendAddress(SendAddress sendAddress) {
        if (sendAddress==null)
            throw new DybRuntimeException("新增寄送地址时，sendAddress对象不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getReceiver()))
            throw new DybRuntimeException("新增寄送地址时，收件人不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getProvince()))
            throw new DybRuntimeException("新增寄送地址时，省级代码不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getCity()))
            throw new DybRuntimeException("新增寄送地址时，市级代码不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getAddress()))
            throw new DybRuntimeException("新增寄送地址时，地址不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getPostalcode()))
            throw new DybRuntimeException("新增寄送地址时，邮政编码不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getPhone()))
            throw new DybRuntimeException("新增寄送地址时，联系电话不能为空");
        if (DybUtils.isEmptyOrNull(sendAddress.getAccountCode()))
            throw new DybRuntimeException("新增寄送地址时，关联账户code不能为空");
        if (accountService.getAccountByCode(sendAddress.getAccountCode(), false)==null)
            throw new DybRuntimeException("新增寄送地址时，找不到此账户的信息");
        sendAddress.setSendAddressCode(UUID.randomUUID().toString());
        sendAddress.setCreateTime(new Date());
        int info=sendAddressDao.insertObject(sendAddress);
        return info>0?sendAddress:null;
    }

    /**
     * 根据code删除寄送地址
     * @param sendAddressCode 寄送地址code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteSendAddress(String sendAddressCode) {
        if (DybUtils.isEmptyOrNull(sendAddressCode))
            throw new DybRuntimeException("删除寄送地址时，code不能为空或null");
        if (sendAddressDao.getObject(sendAddressCode,true)==null)
            return true;
        int info=sendAddressDao.deleteObject(sendAddressCode);
        return info>0?true:false;
    }

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
    public List<SendAddress> getSendAddressList(QueryParams wheres, int skip, int size, boolean detail) {
        return sendAddressDao.queryList(wheres,skip,size,detail);
    }

}