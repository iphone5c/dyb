package com.dyb.platforms.fixfunds.services.business.account.service;

import com.dyb.platforms.fixfunds.services.business.account.dao.IAccountDao;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.business.member.service.IMemberService;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.service.IServiceProvidersService;
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

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("accountService")
public class AccountService extends BaseService implements IAccountService {

    public Logger log = Logger.getLogger(AccountService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IServiceProvidersService serviceProvidersService;
    @Autowired
    private IBankAccountService bankAccountService;

    /**
     * 根据账户code查找账户信息
     * @param accountCode 账户code
     * @param detail 是否详情
     * @return 账户信息
     */
    @Override
    public Account getAccountByCode(String accountCode,boolean detail) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("根据code查找账户信息时，code不能为空");
        Account account = accountDao.getObject(accountCode,true);
        if (DybUtils.isEmptyOrNull(account.getAccountForeignKey()))
            throw new DybRuntimeException("根据code查找账户信息时，此账户没有其详情信息,code:"+accountCode);
        if (account.getAccountType()==null)
            throw new DybRuntimeException("根据code查找账户信息时，账户类型不能为空code:"+accountCode);
        if (detail){
            account.setMember(memberService.getMemberByCode(account.getAccountForeignKey()));
            account.setMerchant(merchantService.getMerchantByCode(account.getAccountForeignKey()));
            account.setServiceProviders(serviceProvidersService.getServiceProvidersByCode(account.getAccountForeignKey()));
        }
        return account;
    }

    /**
     * 添加账户信息
     * @param account 账户对象
     * @return 账户信息
     */
    @Override
    public Account createAccount(Account account) {
        if (account==null)
            throw new DybRuntimeException("账户添加时，account对象不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getAccountName()))
            throw new DybRuntimeException("账户添加时，账户名不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getPassword()))
            throw new DybRuntimeException("账户添加时，账户密码不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getTradePassword()))
            throw new DybRuntimeException("账户添加时，二级密码不能为空或null");
        if (account.getPassword().equals(account.getTradePassword()))
            throw new DybRuntimeException("账户添加时，二级密码与登陆密码不能相同");
        if (account.getAccountType()==null)
            throw new DybRuntimeException("账户添加时，账户类别不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getAccountPhone()))
            throw new DybRuntimeException("账户添加时，手机号不能为空或null");
        if (this.isExistPhone(account.getAccountPhone(),account.getAccountType()))
            throw new DybRuntimeException("账户添加时，手机号已存在");
        if (account.getAccountStatus()==null)
            throw new DybRuntimeException("账户添加时，账户状态不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getAccountForeignKey()))
            throw new DybRuntimeException("账户添加时，账户详情外键不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getReferrerCode()))
            throw new DybRuntimeException("账户添加时，推荐人code不能为空或null");
        String accountCode=account.getAccountCode();
        if (DybUtils.isEmptyOrNull(account.getAccountCode())){
            accountCode=codeBuilder.getAccountCode();
        }
        account.setAccountCode(accountCode);
        account.setPassword(DybUtils.encryptPassword(account.getPassword()));
        account.setTradePassword(DybUtils.encryptPassword(account.getTradePassword()));
        account.setApplyRegistrationTime(new Date());
        account.setCreateTime(new Date());
        account.setReferrerLocation(account.getReferrerCode() + accountCode);
        int info = accountDao.insertObject(account);
        return info>0?account:null;
    }

    /**
     * 注册信使
     * @param account 账户对象
     * @param member 信使对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人Code
     * @return 账户信息
     */
    @Override
    public Account registerMember(Account account, Member member,BankAccount bankAccount, String referrerCode) {
        if (account==null)
            throw new DybRuntimeException("信使注册时，account对象不能为空或null");
        if (member==null)
            throw new DybRuntimeException("信使注册时，member对象不能为空或null");
        if (bankAccount==null)
            throw new DybRuntimeException("信使注册时，bankAccount对象不能为空或null");
        if (DybUtils.isEmptyOrNull(referrerCode))
            throw new DybRuntimeException("信使注册时，推荐人不能为空或null");
        String accountCode=codeBuilder.getAccountCode();
        //添加信息详情
        member.setAccountCode(accountCode);
        Member tempMember= memberService.createMember(member);
        if (tempMember==null)
            throw new DybRuntimeException("信使注册时，信使详情信息注册失败");

        //添加账户银行信息
        bankAccount.setAccountCode(accountCode);
        BankAccount tempBankAccount=bankAccountService.createBankAccount(bankAccount);
        if (tempBankAccount==null)
            throw new DybRuntimeException("信使注册时，银行账号信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.信使);
        account.setAccountStatus(AccountStatus.正常);
        account.setAccountForeignKey(tempMember.getMemberCode());
        Account tempAccount=this.createAccount(account);
        if (tempAccount==null)
            throw new DybRuntimeException("信使注册时，账户注册失败");
        account.setMember(tempMember);
        return account;
    }

    /**
     * 注册商家
     * @param account 账户对象
     * @param merchant 商家对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    @Override
    public Account registerMerchant(Account account, Merchant merchant,BankAccount bankAccount, String referrerCode) {
        if (account==null)
            throw new DybRuntimeException("商家注册时，account对象不能为空或null");
        if (merchant==null)
            throw new DybRuntimeException("商家注册时，merchant对象不能为空或null");
        if (bankAccount==null)
            throw new DybRuntimeException("商家注册时，bankAccount对象不能为空或null");
        if (DybUtils.isEmptyOrNull(referrerCode))
            throw new DybRuntimeException("商家注册时，推荐人不能为空或null");
        String accountCode=codeBuilder.getAccountCode();
        //添加商家详情
        merchant.setAccountCode(accountCode);
        Merchant tempMerchant= merchantService.createMerchant(merchant);
        if (tempMerchant==null)
            throw new DybRuntimeException("商家注册时，商家详情信息注册失败");

        //添加账户银行信息
        bankAccount.setAccountCode(accountCode);
        BankAccount tempBankAccount=bankAccountService.createBankAccount(bankAccount);
        if (tempBankAccount==null)
            throw new DybRuntimeException("信使注册时，银行账号信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.商家);
        account.setAccountStatus(AccountStatus.待提交审核);
        account.setAccountForeignKey(tempMerchant.getMerchantCode());
        Account tempAccount=this.createAccount(account);
        if (tempAccount==null)
            throw new DybRuntimeException("商家注册时，账户注册失败");
        account.setMerchant(tempMerchant);
        return account;
    }

    /**
     * 注册服务商
     * @param account 账户对象
     * @param serviceProviders 服务商对象
     * @param bankAccount 银行账号
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    @Override
    public Account registerServiceProviders(Account account, ServiceProviders serviceProviders,BankAccount bankAccount, String referrerCode) {
        if (account==null)
            throw new DybRuntimeException("服务商注册时，account对象不能为空或null");
        if (serviceProviders==null)
            throw new DybRuntimeException("服务商注册时，serviceProviders对象不能为空或null");
        if (bankAccount==null)
            throw new DybRuntimeException("服务商注册时，bankAccount对象不能为空或null");
        if (DybUtils.isEmptyOrNull(referrerCode))
            throw new DybRuntimeException("服务商注册时，推荐人不能为空或null");
        String accountCode=codeBuilder.getAccountCode();
        //添加服务商详情
        serviceProviders.setAccountCode(accountCode);
        ServiceProviders tempServiceProviders= serviceProvidersService.createServiceProviders(serviceProviders);
        if (tempServiceProviders==null)
            throw new DybRuntimeException("服务商注册时，服务商详情信息注册失败");

        //添加账户银行信息
        bankAccount.setAccountCode(accountCode);
        BankAccount tempBankAccount=bankAccountService.createBankAccount(bankAccount);
        if (tempBankAccount==null)
            throw new DybRuntimeException("信使注册时，银行账号信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.服务商);
        account.setAccountStatus(AccountStatus.待提交审核);
        account.setAccountForeignKey(tempServiceProviders.getServiceProviderCode());
        Account tempAccount=this.createAccount(account);
        if (tempAccount==null)
            throw new DybRuntimeException("服务商注册时，服务商注册失败");
        account.setServiceProviders(tempServiceProviders);
        return account;
    }

    /**
     * 验证绑定手机号是否存在
     * @param phone 手机号
     * @param accountType 账户类型
     * @return true表示存在 false表示不存在
     */
    @Override
    public boolean isExistPhone(String phone,AccountType accountType) {
        if (DybUtils.isEmptyOrNull(phone))
            throw new DybRuntimeException("验证手机不能为空");
        QueryParams queryParams = new QueryParams();
        queryParams.addParameter("accountPhone",phone);
        queryParams.addParameter("accountType",accountType.name());
        List<Account> accountList=accountDao.queryList(queryParams,0,-1,true);
        return (accountList!=null&&accountList.size()>0)?true:false;
    }

    /**
     * 移动端登陆
     * @param accountCode 账户Code或者绑定手机
     * @param password 账户密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    @Override
    public Account loginAccountForClient(String accountCode, String password, AccountType accountType) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("账户不能为空");
        if (DybUtils.isEmptyOrNull(password))
            throw new DybRuntimeException("账户密码不能为空");
        if (accountType==null)
            throw new DybRuntimeException("账户类型不能为空");
        QueryParams queryParams = new QueryParams();
        queryParams.addMulAttrParameter("accountCode",accountCode);
        queryParams.addMulAttrParameter("accountPhone",accountCode);
        queryParams.addParameter("accountType",accountType.name());
        List<Account> accountList = accountDao.queryList(queryParams,0,-1,true);
        Account account=(accountList!=null&&accountList.size()>0)?accountList.get(0):null;
        if (account==null)
            throw new DybRuntimeException("无此用户");
        if (account.getAccountStatus()==AccountStatus.禁用)
            throw new DybRuntimeException("此账户已被禁用，请联系管理员");
        if (!DybUtils.verifyPassword(password,account.getPassword()))
            throw new DybRuntimeException("账户密码输入错误");
        return account;
    }
}
