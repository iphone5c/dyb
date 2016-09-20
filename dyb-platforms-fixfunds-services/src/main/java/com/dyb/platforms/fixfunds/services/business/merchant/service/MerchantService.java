package com.dyb.platforms.fixfunds.services.business.merchant.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.entity.BankAccount;
import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.merchant.dao.IMerchantDao;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.MerchantStatus;
import com.dyb.platforms.fixfunds.services.business.recommended.entity.Recommended;
import com.dyb.platforms.fixfunds.services.business.recommended.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.recommended.servcie.IRecommendedService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("merchantService")
public class MerchantService extends BaseService implements IMerchantService {

    public Logger log = Logger.getLogger(MerchantService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IMerchantDao merchantDao;
    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private IRecommendedService recommendedService;

    /**
     * 商户信息链接注册
     * @param merchant 商家账户对象
     * @param bankAccount 银行账号信息
     * @param tjrCode 推荐人的code
     * @param tjrType 推荐人类型
     * @return 商家账户对象
     */
    @Override
    public Merchant createMerchantRegistered(Merchant merchant,BankAccount bankAccount,String tjrCode,AccountType tjrType) {
        //注册验证
        if (merchant==null)
            throw new DybRuntimeException("商户注册时，merchant对象不能为空或null");
        if (merchant.getMerchantType()==null)
            throw new DybRuntimeException("商户注册时，必须选择商户类型");
        if (DybUtils.isEmptyOrNull(merchant.getAccountName()))
            throw new DybRuntimeException("商户注册时，用户名不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPassword()))
            throw new DybRuntimeException("商户注册时，密码不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalPhone()))
            throw new DybRuntimeException("商户注册时，手机号码不能为空");
        if (this.isExistLoginPhone(merchant.getPrincipalPhone()))
            throw new DybRuntimeException("商户注册时，手机号已经存在");
        //商家基本信息验证
        if (DybUtils.isEmptyOrNull(merchant.getMerchantName()))
            throw new DybRuntimeException("商户注册时，商家名称不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getShopName()))
            throw new DybRuntimeException("商户注册时，店铺名称不能为空");
        if (merchant.getIncentiveMode()==null || merchant.getIncentiveMode()<=0)
            throw new DybRuntimeException("商户注册时，激励模式必须是大于零的整数");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantAddress()))
            throw new DybRuntimeException("商户注册时，商家地址不能为空");
        if (merchant.getIndustry()==null)
            throw new DybRuntimeException("商户注册时，行业不能为空");
        if (merchant.getIndustryType()==null)
            throw new DybRuntimeException("商户注册时，行业类别不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getMainBusiness()))
            throw new DybRuntimeException("商户注册时，主营业务不能为空");
        if (merchant.getScale()==null)
            throw new DybRuntimeException("商户注册时，商家规模不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPhone()))
            throw new DybRuntimeException("商户注册时，企业电话不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessStartTime()))
            throw new DybRuntimeException("商户注册时，营业开始时间不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessEndTime()))
            throw new DybRuntimeException("商户注册时，营业结束时间不能为空");
        if (!this.isValidation(merchant.getBusinessStartTime()))
            throw new DybRuntimeException("商户注册时，营业开始时间的格式不正确");
        if (!this.isValidation(merchant.getBusinessEndTime()))
            throw new DybRuntimeException("商户注册时，营业结束时间的格式不正确");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantDescription()))
            throw new DybRuntimeException("商户注册时，商家简介不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getTradePassword()))
            throw new DybRuntimeException("商户注册时，二级密码不能为空");

        //负责人信息验证
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalName()))
            throw new DybRuntimeException("商户注册时，负责人姓名不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalJobs()))
            throw new DybRuntimeException("商户注册时，负责人岗位不能为空");
        if (merchant.getPrincipalSex()==null)
            throw new DybRuntimeException("商户注册时，负责人性别必须选择");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalIdCard()))
            throw new DybRuntimeException("商户注册时，负责人身份证号码不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalEmail()))
            throw new DybRuntimeException("商户注册时，负责人邮箱地址不能为空");
        if (merchant.getPrincipalBirthday()==null)
            throw new DybRuntimeException("商户注册时，负责人出生日期不能为空");

        //添加新商家
        String merchantCode=codeBuilder.getMerchantCode();
        merchant.setMerchantCode(merchantCode);
        merchant.setMerchantStatus(MerchantStatus.待提交审核);
        merchant.setPassword(DybUtils.encryptPassword(merchant.getPassword()));
        int info=merchantDao.insertObject(merchant);

        //绑定银行卡
        bankAccount.setBankAccountPerson(merchantCode);
        bankAccount.setBankAccountType(AccountType.商家);
        bankAccountService.createBankAccount(bankAccount);

        //添加推荐信息
        Recommended recommended=new Recommended();
        recommended.setReferrerCode(tjrCode);
        recommended.setReferrerType(tjrType);
        recommended.setBtjrCode(merchantCode);
        recommended.setBtjrType(AccountType.商家);
        recommended.setIncentiveMessengerBean(10d);
        recommendedService.createRecommended(recommended);
        return info>0?merchant:null;
    }

    /**
     * 验证时间格式——>  01:30
     * @param time 验证时间
     * @return true表示格式正确 false表示格式不正确
     */
    private boolean isValidation(String time){
        Pattern p = Pattern.compile("");
        Matcher m = p.matcher(time);
        boolean flg = m.matches();
        return flg;
    }

    /**
     * 判断指定手机号是否已经注册
     * @param phone 注册手机号
     * @return true表示存在 false表示不存在
     */
    @Override
    public Boolean isExistLoginPhone(String phone) {
        if (DybUtils.isEmptyOrNull(phone))
            throw new DybRuntimeException("验证的手机码不能为空。");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("principalPhone",phone);
        List<Merchant> merchantList= merchantDao.queryList(queryParams, 0, -1, true);
        return (merchantList==null||merchantList.size()<=0)?true:false;
    }

    /**
     * 根据商家code查找指定商家信息
     * @param merchantCode 商家code
     * @return 商家信息
     */
    @Override
    public Merchant getMerchantByCode(String merchantCode) {
        if (DybUtils.isEmptyOrNull(merchantCode))
            throw new DybRuntimeException("根据code查找商家信息时，code不能为空");
        return merchantDao.getObject(merchantCode,true);
    }
}
