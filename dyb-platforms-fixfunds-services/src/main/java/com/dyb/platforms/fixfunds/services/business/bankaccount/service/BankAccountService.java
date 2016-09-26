package com.dyb.platforms.fixfunds.services.business.bankaccount.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.dao.IBankAccountDao;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
}
