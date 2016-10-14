package com.dyb.platforms.fixfunds.services.business.sendaddress.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
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
        Account account=accountService.getAccountByCode(sendAddress.getAccountCode(), false);
        if (account==null)
            throw new DybRuntimeException("新增寄送地址时，找不到此账户的信息");

        sendAddress.setSendAddressCode(UUID.randomUUID().toString());
        sendAddress.setCreateTime(new Date());
        int info=sendAddressDao.insertObject(sendAddress);
        if (info>0){
            if (sendAddress.isDefaultChecked()){
                this.setSendAddressByDefaultChecked(account.getAccountCode(),sendAddress.getSendAddressCode());
            }
            return sendAddress;
        }
        return null;
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

    /**
     * 根据账户code获取默认的寄送地址信息
     * @param accountCode 账户code
     * @return 寄送地址
     */
    @Override
    public SendAddress getSendAddressByDefaultChecked(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("根据账户code获取默认的寄送地址信息时，账户code不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        queryParams.addParameter("defaultChecked",true);
        List<SendAddress> sendAddresses=sendAddressDao.queryList(queryParams,0,-1,true);
        return (sendAddresses!=null&&sendAddresses.size()>0)?sendAddresses.get(0):null;
    }

    /**
     * 根据code获取寄送地址信息
     * @param sendAddressCode 寄送地址code
     * @return 寄送地址
     */
    @Override
    public SendAddress getSendAddressByCode(String sendAddressCode) {
        if (DybUtils.isEmptyOrNull(sendAddressCode))
            throw new DybRuntimeException("根据code获取寄送地址信息时，sendAddressCode不能为空");
        return sendAddressDao.getObject(sendAddressCode,true);
    }

    /**
     * 给指定用户设置默认寄送地址
     * @param accountCode 账户code
     * @param sendAddressCode 寄送地址code
     * @return true表示操作成功 false表示操作成功
     */
    @Override
    public boolean setSendAddressByDefaultChecked(String accountCode, String sendAddressCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("账户code不能为空");
        if (DybUtils.isEmptyOrNull(sendAddressCode))
            throw new DybRuntimeException("设置默认选中的寄送地址code不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        List<SendAddress> sendAddressList=sendAddressDao.queryList(queryParams,0,-1,true);
        if (sendAddressList==null||sendAddressList.size()<=0)
            throw new DybRuntimeException("此账户目前尚未添加寄送地址信息，请添加至少一张寄送地址信息");
        boolean flag=false;
        SendAddress[] updateSendAddress=new SendAddress[sendAddressList.size()];
        int i=0;
        for (SendAddress sendAddress:sendAddressList){
            updateSendAddress[i]=sendAddress;
            i++;
            if (sendAddress.getSendAddressCode().equals(sendAddressCode)){
                sendAddress.setDefaultChecked(true);
                flag=true;
            }else {
                sendAddress.setDefaultChecked(false);
            }
        }
        if (!flag)
            throw new DybRuntimeException("没有找到此卡信息");
        int info=sendAddressDao.updateList(updateSendAddress);
        return info>0?true:false;
    }

}
