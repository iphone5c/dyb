package com.dyb.platforms.fixfunds.services.business.account.service;

import com.dyb.platforms.fixfunds.services.business.account.dao.IAccountDao;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.Qrcode;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.business.member.service.IMemberService;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.service.IMerchantService;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.messengerbean.service.IMessengerBeanService;
import com.dyb.platforms.fixfunds.services.business.salesman.entity.Salesman;
import com.dyb.platforms.fixfunds.services.business.salesman.service.ISalesmanService;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.service.IServiceProvidersService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.QRCodeUtil;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private IMessengerBeanService messengerBeanService;
    @Autowired
    private ISalesmanService salesmanService;

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
        if (account==null)
            throw new DybRuntimeException("根据code查找账户信息时,找不到此账户信息,code:"+accountCode);
        if (DybUtils.isEmptyOrNull(account.getAccountForeignKey()))
            throw new DybRuntimeException("根据code查找账户信息时，此账户没有其详情信息,code:"+accountCode);
        if (account.getAccountType()==null)
            throw new DybRuntimeException("根据code查找账户信息时，账户类型不能为空code:"+accountCode);
        if (detail){
            account.setMember(memberService.getMemberByCode(account.getAccountForeignKey()));
            account.setMerchant(merchantService.getMerchantByCode(account.getAccountForeignKey()));
            account.setServiceProviders(serviceProvidersService.getServiceProvidersByCode(account.getAccountForeignKey()));
            account.setSalesman(salesmanService.getSalesmanByCode(account.getAccountForeignKey()));
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
        try {
            Map<String,Qrcode> qrcodeMap=new HashMap<>();
            StringBuffer url=new StringBuffer("http://").append(DybUtils.SERVICE).append(":").append(DybUtils.PORT);
            if (account.getAccountType()==AccountType.信使){
                StringBuffer sb=new StringBuffer();
                Qrcode qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/xin-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("member",qrcode);
            }else if (account.getAccountType()==AccountType.商家){
                StringBuffer sb=new StringBuffer();
                Qrcode qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/xin-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("member",qrcode);
            }else if (account.getAccountType()==AccountType.服务商){
                StringBuffer sb=new StringBuffer();
                Qrcode qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/xin-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("member",qrcode);

                sb=new StringBuffer();
                qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/fuwu-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("merchant",qrcode);

            }else if (account.getAccountType()==AccountType.业务员){
                StringBuffer sb=new StringBuffer();
                Qrcode qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/xin-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("member",qrcode);

                sb=new StringBuffer();
                qrcode=new Qrcode();
                sb.append(url).append("/webpage/register/fuwu-register.html?referrer=").append(accountCode);
                qrcode.setUrl(sb.toString());
                qrcode.setImagePath(QRCodeUtil.encode(sb.toString(), "/upload"));
                qrcodeMap.put("merchant",qrcode);

            }
            account.setQrcode(DybUtils.getJsonSerialize(qrcodeMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int info = accountDao.insertObject(account);
        return info>0?account:null;
    }

    /**
     * 注册信使
     * @param account 账户对象
     * @param member 信使对象
     * @param referrerCode 推荐人Code
     * @return 账户信息
     */
    @Override
    public Account registerMember(Account account, Member member , String referrerCode) {
        if (account==null)
            throw new DybRuntimeException("信使注册时，account对象不能为空或null");
        if (member==null)
            throw new DybRuntimeException("信使注册时，member对象不能为空或null");
        if (DybUtils.isEmptyOrNull(referrerCode))
            throw new DybRuntimeException("信使注册时，推荐人不能为空或null");
        String accountCode=codeBuilder.getAccountCode();
        //添加信息详情
        member.setAccountCode(accountCode);
        Member tempMember= memberService.createMember(member);
        if (tempMember==null)
            throw new DybRuntimeException("信使注册时，信使详情信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.信使);
        account.setAccountStatus(AccountStatus.正常);
        account.setRegistrationTime(new Date());
        account.setAccountForeignKey(tempMember.getMemberCode());

        //添加信使豆信息
        boolean flag=messengerBeanService.createMessengerBeanByMessType(accountCode, MessengerBeanType.普通信使豆, MessengerBeanType.待缴税, MessengerBeanType.转换中, MessengerBeanType.注册奖励);
        if (!flag)
            throw new DybRuntimeException("信使注册时，添加信使豆信息失败");

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
            throw new DybRuntimeException("商家注册时，银行账号信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.商家);
        account.setAccountStatus(AccountStatus.审核中);
        account.setRegistrationTime(new Date());
        account.setAccountForeignKey(tempMerchant.getMerchantCode());

        //添加信使豆信息
        boolean flag=messengerBeanService.createMessengerBeanByMessType(accountCode, MessengerBeanType.普通信使豆, MessengerBeanType.待提供发票, MessengerBeanType.转换中, MessengerBeanType.注册奖励);
        if (!flag)
            throw new DybRuntimeException("商家注册时，添加信使豆信息失败");

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
        account.setRegistrationTime(new Date());
        account.setAccountForeignKey(tempServiceProviders.getServiceProviderCode());

        //添加信使豆信息
        boolean flag=messengerBeanService.createMessengerBeanByMessType(accountCode, MessengerBeanType.普通信使豆, MessengerBeanType.待缴税, MessengerBeanType.转换中, MessengerBeanType.注册奖励);
        if (!flag)
            throw new DybRuntimeException("服务商注册时，添加信使豆信息失败");

        Account tempAccount=this.createAccount(account);
        if (tempAccount==null)
            throw new DybRuntimeException("服务商注册时，服务商注册失败");
        account.setServiceProviders(tempServiceProviders);
        return account;
    }

    /**
     * 注册业务员
     * @param account 账户对象
     * @param salesman 业务员对象
     * @param referrerCode 推荐人code
     * @return 账户信息
     */
    @Override
    public Account registerSalesman(Account account, Salesman salesman , String referrerCode) {
        if (account==null)
            throw new DybRuntimeException("业务员注册时，account对象不能为空或null");
        if (salesman==null)
            throw new DybRuntimeException("业务员注册时，salesman对象不能为空或null");
        if (DybUtils.isEmptyOrNull(referrerCode))
            throw new DybRuntimeException("业务员注册时，推荐人不能为空或null");
        String accountCode=codeBuilder.getAccountCode();
        //添加服务商详情
        salesman.setAccountCode(accountCode);
        Salesman tempSalesman= salesmanService.createSalesman(salesman);
        if (tempSalesman==null)
            throw new DybRuntimeException("业务员注册时，业务员详情信息注册失败");

        //添加账户信息
        account.setAccountCode(accountCode);
        account.setReferrerCode(referrerCode);
        account.setAccountType(AccountType.业务员);
        account.setAccountStatus(AccountStatus.正常);
        account.setRegistrationTime(new Date());
        account.setAccountForeignKey(tempSalesman.getSalesmanCode());

        //添加信使豆信息
        boolean flag=messengerBeanService.createMessengerBeanByMessType(accountCode, MessengerBeanType.待转赠, MessengerBeanType.转换中, MessengerBeanType.注册奖励);
        if (!flag)
            throw new DybRuntimeException("业务员注册时，添加信使豆信息失败");

        Account tempAccount=this.createAccount(account);
        if (tempAccount==null)
            throw new DybRuntimeException("业务员注册时，业务员注册失败");
        account.setSalesman(tempSalesman);
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

    /**
     * 商家资料修改
     * @param account 账户信息
     * @param merchant 商家详情
     * @param bankAccount 默认银行卡信息
     * @return 账户对象
     */
    @Override
    public Account updateMerchant(Account account, Merchant merchant, BankAccount bankAccount) {
        if (account==null)
            throw new DybRuntimeException("修改商家资料时，account对象不能为空或null");
        if (DybUtils.isEmptyOrNull(account.getAccountCode()))
            throw new DybRuntimeException("修改商家资料时，accountCode不能为空或null");
        if (merchant==null)
            throw new DybRuntimeException("修改商家资料时，merchant对象不能为空或null");
        if (bankAccount==null)
            throw new DybRuntimeException("修改商家资料时，bankAccount对象不能为空或null");
        if (accountDao.getObject(account.getAccountCode(),true)==null)
            throw new DybRuntimeException("修改商家资料时，找不到此账户的信息");

        //更新商家详情
        Merchant tempMerchant= merchantService.updateMerchantByCode(merchant);
        if (tempMerchant==null)
            throw new DybRuntimeException("修改商家资料时，商家详情信息更新失败");

        //添加账户银行信息
        BankAccount tempBankAccount=bankAccountService.updateBankAccount(bankAccount);
        if (tempBankAccount==null)
            throw new DybRuntimeException("修改商家资料时，银行账号信息更新失败");

        account.setMerchant(tempMerchant);
        return account;
    }

    /**
     * 根据账户code或phone查找账户信息
     * @param key 账户code或者phone
     * @param accountType 账户类型
     * @return 账户信息
     */
    @Override
    public Account getAccountByCodeOrPhone(String key, AccountType accountType) {
        if (DybUtils.isEmptyOrNull(key))
            throw new DybRuntimeException("查找账户信息时，key不能为空");
        QueryParams queryParams = new QueryParams();
        queryParams.addMulAttrParameter("accountCode",key);
        queryParams.addMulAttrParameter("accountPhone",key);
        queryParams.addParameter("accountType", accountType.name());
        List<Account> accountList = accountDao.queryList(queryParams,0,-1,true);
        Account account=(accountList!=null&&accountList.size()>0)?accountList.get(0):null;
        if (account==null)
            throw new DybRuntimeException("找不到此账户信息");
        return this.getAccountByCode(account.getAccountCode(),true);
    }

    /**
     *获取账户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Account> getAccountPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return accountDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
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
    public List<Account> getAccountList(QueryParams wheres, int skip, int size, boolean detail) {
        return accountDao.queryList(wheres,skip,size,detail);
    }

    /**
     * 修改用户登录密码
     * @param accountCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean modifyPassword(String accountCode, String oldPassword, String newPassword, String confirmPassword) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("账户code不能为空");
        if (DybUtils.isEmptyOrNull(oldPassword))
            throw new DybRuntimeException("旧密码不能为空");
        if (DybUtils.isEmptyOrNull(newPassword))
            throw new DybRuntimeException("新密码不能为空");
        if (DybUtils.isEmptyOrNull(confirmPassword))
            throw new DybRuntimeException("确认密码不能为空");
        if (!newPassword.equals(confirmPassword))
            throw new DybRuntimeException("新密码与确认密码不一致");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new DybRuntimeException("找不到此账户的信息");
        if (!DybUtils.verifyPassword(oldPassword,account.getPassword()))
            throw new DybRuntimeException("旧密码输入错误");
        account.setPassword(DybUtils.encryptPassword(newPassword));
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableAccount(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("禁用指定用户时，code不能为空或null");
        return this.operationAccountStatus(accountCode,AccountStatus.禁用);
    }

    /**
     * 将指定用户解除禁用
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean removeDisableAccount(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("解除指定用户的禁用状态时，code不能为空或null");
        return this.operationAccountStatus(accountCode,AccountStatus.正常);
    }

    /**
     * 操作指定用户额状态
     * @param accountCode 用户code
     * @param accountStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationAccountStatus(String accountCode, AccountStatus accountStatus) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("操作指定用户的状态时，code不能为空或null");
        if (accountStatus==null)
            throw new DybRuntimeException("操作指定用户的状态时，修改的用户状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new DybRuntimeException("操作指定用户的状态时，找不到此用户信息，code："+accountCode);
        account.setAccountStatus(accountStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 将指定用户重置登录密码
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean resetAccountPassword(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("指定用户重置登录密码时，code不能为空或null");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new DybRuntimeException("指定用户重置登录密码时，找不到此用户信息，code："+accountCode);
        account.setPassword(DybUtils.encryptPassword("ABC123"));
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 将指定用户重置二级密码
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean resetAccountTradePassword(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("指定用户重置二级密码时，code不能为空或null");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new DybRuntimeException("指定用户重置二级密码时，找不到此用户信息，code："+accountCode);
        account.setTradePassword(DybUtils.encryptPassword("ABC123"));
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 指定用户审核通过
     * @param accountCode 账户编号code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean approvedAccount(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("指定用户审核通过时，code不能为空或null");
        return this.operationAccountStatus(accountCode,AccountStatus.正常);
    }

    /**
     * 指定用户审核不通过
     * @param accountCode 账户编号code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean cancelAccount(String accountCode) {
        if (DybUtils.isEmptyOrNull(accountCode))
            throw new DybRuntimeException("指定用户审核不通过时，code不能为空或null");
        return this.operationAccountStatus(accountCode,AccountStatus.审核未通过);
    }
}
