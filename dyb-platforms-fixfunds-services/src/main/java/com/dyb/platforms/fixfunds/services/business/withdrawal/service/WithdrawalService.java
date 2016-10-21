package com.dyb.platforms.fixfunds.services.business.withdrawal.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.MessengerBean;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Invoice;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;
import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.em.ApplyStatus;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("withdrawalService")
public class WithdrawalService extends BaseService implements IWithdrawalService {

    public Logger log = Logger.getLogger(WithdrawalService.class);//日志

    @Autowired
    private IWithdrawalDao withdrawalDao;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ISystemParamsService systemParamsService;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private IMessengerBeanService messengerBeanService;

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
    public List<Withdrawal> getWithdrawalList(QueryParams wheres, int skip, int size, boolean detail) {
        return withdrawalDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的回购记录信息
     * @param withdrawalCode 回购记录code
     * @return 回购记录信息
     */
    @Override
    public Withdrawal getWithdrawalByCode(String withdrawalCode) {
        if (DybUtils.isEmptyOrNull(withdrawalCode))
            throw new DybRuntimeException("根据回购记录code获取回购记录信息时，回购记录code不能为空");
        return withdrawalDao.getObject(withdrawalCode,true);
    }

    /**
     *获取回购记录分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Withdrawal> getWithdrawalPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return withdrawalDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 回购申请
     * @param withdrawalType 回购类型
     * @param merssengerBean 回购数量
     * @param tradePassword 二级密码
     * @param withdrawalAccount 回购人code
     * @param invoiceList 发票明细
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean withdrawalMessengerBean(MessengerBeanType withdrawalType, Double merssengerBean, String tradePassword, String withdrawalAccount, List<Invoice> invoiceList) {
        if (withdrawalType==null)
            throw new DybRuntimeException("回购类型不能为空");
        if (merssengerBean%100!=0)
            throw new DybRuntimeException("回购数量必须是100的整倍数");
        if (DybUtils.isEmptyOrNull(tradePassword))
            throw new DybRuntimeException("二级密码不能为空");
        if (DybUtils.isEmptyOrNull(withdrawalAccount))
            throw new DybRuntimeException("回购人不能为空");
        Account account=accountService.getAccountByCode(withdrawalAccount,false);
        if (account==null)
            throw new DybRuntimeException("没找有点此回购人信息");
        if (!DybUtils.verifyPassword(tradePassword,account.getTradePassword()))
            throw new DybRuntimeException("二级密码输入错误");
        BankAccount bankAccount=bankAccountService.getBankAccountByDefaultChecked(account.getAccountCode());
        if (bankAccount==null)
            throw new DybRuntimeException("此回购账户没有设置默认银行卡信息");
        SystemParams poundageParam=systemParamsService.getSystemParamsByKey("poundage");
        if (poundageParam==null)
            throw new DybRuntimeException("手续费参数未指定");
        Double poundage=Double.parseDouble(poundageParam.getSystemParamsValue());

        Withdrawal withdrawal=new Withdrawal();
        Double deductions=0d;
        if (withdrawalType==MessengerBeanType.普通信使豆){
            deductions=0d;
            withdrawal.setInvoice(null);
        }else if (withdrawalType==MessengerBeanType.待缴税){
            SystemParams systemParams=systemParamsService.getSystemParamsByKey("conversionTaxNoInvoice");
            if (systemParams==null)
                throw new DybRuntimeException("扣税比例尚未设置");
            deductions=Double.parseDouble(systemParams.getSystemParamsValue())*merssengerBean;
            withdrawal.setInvoice(null);
        }else if (withdrawalType==MessengerBeanType.待提供发票){
            SystemParams systemParams=systemParamsService.getSystemParamsByKey("conversionTaxInvoice");
            if (systemParams==null)
                throw new DybRuntimeException("扣税比例尚未设置");
            deductions=Double.parseDouble(systemParams.getSystemParamsValue())*merssengerBean;
            withdrawal.setInvoice(DybUtils.getJsonSerialize(invoiceList));
        }else {
            throw new DybRuntimeException("回购类型超出指定范围值");
        }
        withdrawal.setWithdrawalType(withdrawalType);
        withdrawal.setApplyWithdrawalNum(merssengerBean);
        withdrawal.setPoundage(poundage);
        withdrawal.setDeductions(deductions);
        withdrawal.setWithdrawalNum(merssengerBean-poundage-deductions);
        withdrawal.setBankName(bankAccount.getBankName());
        withdrawal.setBankBranch(bankAccount.getBankBranch());
        withdrawal.setBankNum(bankAccount.getBankNum());
        withdrawal.setAccountName(bankAccount.getBankAccountName());
        withdrawal.setWithdrawalAccount(account.getAccountCode());
        //账户信使豆处理
        MessengerBean messengerBeanAccount=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(account.getAccountCode(), withdrawalType);
        if (messengerBeanAccount==null)
            throw new DybRuntimeException("没有找到此账户的信使豆类型信息");
        //可用余额
        Double mBean=messengerBeanAccount.getMessengerBean();
        //冻结的信使豆
        Double fBean=messengerBeanAccount.getFreeze();
        if (mBean<merssengerBean)
            throw new DybRuntimeException("可回购信使豆余额不足");
        messengerBeanAccount.setMessengerBean(mBean-merssengerBean);
        messengerBeanAccount.setFreeze(fBean+merssengerBean);
        MessengerBean updateMessengerBean=messengerBeanService.updateMessengerBean(messengerBeanAccount);
        if (updateMessengerBean==null)
            throw new DybRuntimeException("账户信使豆处理失败");
        Withdrawal temp=this.createWithdrawal(withdrawal);
        return temp==null?false:true;
    }

    /**
     * 新增回购信息
     * @param withdrawal
     * @return
     */
    @Override
    public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        if (withdrawal==null)
            throw new DybRuntimeException("新增回购信息时，withdrawal不能为空");
        if (withdrawal.getWithdrawalType()==null)
            throw new DybRuntimeException("新增回购信息时，回购类型不能为空");
        if (withdrawal.getApplyWithdrawalNum()<0)
            throw new DybRuntimeException("新增回购信息时，申请回购数量必须大于等于零");
        if (withdrawal.getDeductions()<0)
            throw new DybRuntimeException("新增回购信息时，代扣税必须大于等于零");
        if (withdrawal.getPoundage()<0)
            throw new DybRuntimeException("新增回购信息时，手续费数量必须大于等于零");
        if (DybUtils.isEmptyOrNull(withdrawal.getBankName()))
            throw new DybRuntimeException("新增回购信息时，开户行不能为空");
        if (DybUtils.isEmptyOrNull(withdrawal.getBankBranch()))
            throw new DybRuntimeException("新增回购信息时，开户支行不能为空");
        if (DybUtils.isEmptyOrNull(withdrawal.getBankNum()))
            throw new DybRuntimeException("新增回购信息时，银行账号不能为空");
        if (DybUtils.isEmptyOrNull(withdrawal.getAccountName()))
            throw new DybRuntimeException("新增回购信息时，开户人姓名不能为空");
        if (DybUtils.isEmptyOrNull(withdrawal.getWithdrawalAccount()))
            throw new DybRuntimeException("新增回购信息时，回购人不能为空");
        Account account=accountService.getAccountByCode(withdrawal.getWithdrawalAccount(),false);
        if (account==null)
            throw new DybRuntimeException("新增回购信息时，找不到此回购人信息");
        withdrawal.setWithdrawalCode(codeBuilder.getWithdrawalCode());
        withdrawal.setApplyTime(new Date());
        withdrawal.setCreateTime(new Date());
        withdrawal.setApplyStatus(ApplyStatus.未审核);
        int info=withdrawalDao.insertObject(withdrawal);
        return info>0?withdrawal:null;
    }

    /**
     * 操作指定回购状态
     * @param withdrawalCode 回购code
     * @param applyStatus 申请状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationWithdrawalStatus(String withdrawalCode, ApplyStatus applyStatus) {
        if (DybUtils.isEmptyOrNull(withdrawalCode))
            throw new DybRuntimeException("操作指定回购状态时，code不能为空或null");
        if (applyStatus==null)
            throw new DybRuntimeException("操作指定回购状态时，修改的申请状态不能为空");
        Withdrawal withdrawal=withdrawalDao.getObject(withdrawalCode,true);
        if (withdrawal==null)
            throw new DybRuntimeException("操作指定回购状态时，找不到此回购信息，code："+withdrawal);
        withdrawal.setApplyStatus(applyStatus);
        int info=withdrawalDao.updateObject(withdrawal);
        return info>0?true:false;
    }

    /**
     * 审核通过回购
     * @param withdrawalCode 回购编号
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean approvedWithdrawal(String withdrawalCode) {
        if (DybUtils.isEmptyOrNull(withdrawalCode))
            throw new DybRuntimeException("审核通过回购时，code不能为空或null");
        Withdrawal withdrawal=withdrawalDao.getObject(withdrawalCode,true);
        if (withdrawal==null)
            throw new DybRuntimeException("没有找到此回购订单信息");
        MessengerBean messengerBean=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(withdrawal.getWithdrawalAccount(),withdrawal.getWithdrawalType());
        if (messengerBean==null)
            throw new DybRuntimeException("沒有找到此信使豆类型");
        //冻结的信使豆
        Double fBean=messengerBean.getFreeze();
        messengerBean.setFreeze(fBean-withdrawal.getApplyWithdrawalNum());
        MessengerBean updateMessengerBean=messengerBeanService.updateMessengerBean(messengerBean);
        if (updateMessengerBean==null)
            throw new DybRuntimeException("账户信使豆处理失败");
        return this.operationWithdrawalStatus(withdrawalCode,ApplyStatus.审批通过);
    }

    /**
     * 审核不通过
     * @param withdrawalCode 回购编号
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean cancelWithdrawal(String withdrawalCode) {
        if (DybUtils.isEmptyOrNull(withdrawalCode))
            throw new DybRuntimeException("审核不通过回购时，code不能为空或null");
        Withdrawal withdrawal=withdrawalDao.getObject(withdrawalCode,true);
        if (withdrawal==null)
            throw new DybRuntimeException("没有找到此回购订单信息");
        MessengerBean messengerBean=messengerBeanService.getMessengerBeanByAccountCodeAndMessengerType(withdrawal.getWithdrawalAccount(),withdrawal.getWithdrawalType());
        if (messengerBean==null)
            throw new DybRuntimeException("沒有找到此信使豆类型");
        //可用余额
        Double mBean=messengerBean.getMessengerBean();
        //冻结的信使豆
        Double fBean=messengerBean.getFreeze();
        messengerBean.setMessengerBean(mBean+withdrawal.getApplyWithdrawalNum());
        messengerBean.setFreeze(fBean-withdrawal.getApplyWithdrawalNum());
        MessengerBean updateMessengerBean=messengerBeanService.updateMessengerBean(messengerBean);
        if (updateMessengerBean==null)
            throw new DybRuntimeException("账户信使豆处理失败");
        return this.operationWithdrawalStatus(withdrawalCode,ApplyStatus.审批不通过);
    }

}
