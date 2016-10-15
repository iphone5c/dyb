package com.dyb.platforms.fixfunds.services.business.bankaccount.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.bankaccount.dao.IBankAccountDao;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
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
@Service("bankAccountService")
public class BankAccountService extends BaseService implements IBankAccountService {

    public Logger log = Logger.getLogger(BankAccountService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IBankAccountDao bankAccountDao;
    @Autowired
    private IAccountService accountService;

    /**
     * 添加一张银行卡
     * @param bankAccount 银行卡对象信息
     * @return 银行卡对象信息
     */
    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        if (bankAccount==null)
            throw new DybRuntimeException("添加新的银行卡，bankAccount对象不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankName()))
            throw new DybRuntimeException("添加新的银行卡，开户行必须选择");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankBranch()))
            throw new DybRuntimeException("添加新的银行卡，开户支行不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankAccountName()))
            throw new DybRuntimeException("添加新的银行卡，开户名不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankNum()))
            throw new DybRuntimeException("添加新的银行卡，银行账号不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getAccountCode()))
            throw new DybRuntimeException("添加新的银行卡，持卡账户不能为空");
        bankAccount.setBankAccountCode(UUID.randomUUID().toString());
        bankAccount.setCreateTime(new Date());
        int info = bankAccountDao.insertObject(bankAccount);
        return info>0?bankAccount:null;
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
    public List<BankAccount> getBankAccountList(QueryParams wheres, int skip, int size, boolean detail) {
        return bankAccountDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 根据账户code获取默认的银行卡信息
     * @param accountCode 账户code
     * @return 银行卡信息
     */
    @Override
    public BankAccount getBankAccountByDefaultChecked(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("根据账户code获取默认的银行卡信息时，账户code不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        queryParams.addParameter("defaultChecked",true);
        List<BankAccount> bankAccountList=bankAccountDao.queryList(queryParams,0,-1,true);
        return (bankAccountList!=null&&bankAccountList.size()>0)?bankAccountList.get(0):null;
    }

    /**
     * 修改银行卡新
     * @param bankAccount 银行卡对象
     * @return银行卡对象
     */
    @Override
    public BankAccount updateBankAccount(BankAccount bankAccount) {
        if (bankAccount==null)
            throw new DybRuntimeException("修改银行卡信息时，bankAccount对象不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankAccountCode()))
            throw new DybRuntimeException("修改银行卡信息时，bankAccountCode不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankName()))
            throw new DybRuntimeException("修改银行卡信息时，开户行必须选择");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankBranch()))
            throw new DybRuntimeException("修改银行卡信息时，开户支行不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankAccountName()))
            throw new DybRuntimeException("修改银行卡信息时，开户名不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getBankNum()))
            throw new DybRuntimeException("修改银行卡信息时，银行账号不能为空");
        if (DybUtils.isEmptyOrNull(bankAccount.getAccountCode()))
            throw new DybRuntimeException("修改银行卡信息时，持卡账户不能为空");
        if (bankAccountDao.getObject(bankAccount.getBankAccountCode(),true)==null)
            throw new DybRuntimeException("修改银行卡信息时，找不到此银行卡信息");
        int info = bankAccountDao.updateObject(bankAccount);
        return info>0?bankAccount:null;
    }

    /**
     * 根据code查找银行卡信息
     * @param bankAccountCode 银行卡code
     * @return 银行卡对象
     */
    @Override
    public BankAccount getBankAccountByCode(String bankAccountCode) {
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            throw new DybRuntimeException("根据code查找银行卡信息时，bankAccountCode不能为空");
        return bankAccountDao.getObject(bankAccountCode,true);
    }

    /**
     * 根据code删除银行卡信息
     * @param bankAccountCode 银行卡code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean deleteBankAccount(String bankAccountCode) {
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            throw new DybRuntimeException("删除银行卡信息时，code不能为空");
        int info=bankAccountDao.deleteObject(bankAccountCode);
        return info>0?true:false;
    }

    /**
     * 设置指定账户code的默认选中银行卡
     * @param accountCode 账户code
     * @param bankAccountCode 银行卡code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean setDefaultBankAccountByCode(String accountCode, String bankAccountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("账户code不能为空");
        if (DybUtils.isEmptyOrNull(bankAccountCode))
            throw new DybRuntimeException("设置默认选中的银行卡code不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        List<BankAccount> bankAccountList=bankAccountDao.queryList(queryParams,0,-1,true);
        if (bankAccountList==null||bankAccountList.size()<=0)
            throw new DybRuntimeException("此账户目前尚未添加银行卡信息，请添加至少一张银行卡信息");
        boolean flag=false;
        BankAccount[] bankAccounts=new BankAccount[bankAccountList.size()];
        int i=0;
        for (BankAccount bankAccount:bankAccountList){
            if (bankAccount.getBankAccountCode().equals(bankAccountCode)){
                bankAccount.setDefaultChecked(true);
                flag=true;
            }else {
                bankAccount.setDefaultChecked(false);
            }
            bankAccounts[i]=bankAccount;
            i++;
        }
        if (!flag)
            throw new DybRuntimeException("没有找到此卡信息");
        int info=bankAccountDao.updateList(bankAccounts);
        return info>0?true:false;
    }
}
