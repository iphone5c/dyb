package com.dyb.platforms.fixfunds.services.business.merchant.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.merchant.dao.IMerchantDao;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.Merchant;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
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

    /**
     * 添加商家信息
     * @param merchant 商家账户对象
     * @return 商家账户对象
     */
    @Override
    public Merchant createMerchant(Merchant merchant) {
        //添加商户信息
        if (merchant==null)
            throw new DybRuntimeException("商户添加时，merchant对象不能为空或null");
        if (merchant.getMerchantType()==null)
            throw new DybRuntimeException("商户添加时，必须选择商户类型");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantName()))
            throw new DybRuntimeException("商户添加时，商家名称不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getShopName()))
            throw new DybRuntimeException("商户添加时，店铺名称不能为空");
        if (merchant.getIncentiveMode()==null || merchant.getIncentiveMode()<=0)
            throw new DybRuntimeException("商户添加时，激励模式必须是大于零的整数");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantAddress()))
            throw new DybRuntimeException("商户添加时，商家地址不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getProvince()))
            throw new DybRuntimeException("商户添加时，商家地址省级代码不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getProvince()))
            throw new DybRuntimeException("商户添加时，商家地址市级代码不能为空");
        if (merchant.getIndustry()==null)
            throw new DybRuntimeException("商户添加时，行业不能为空");
        if (merchant.getIndustryType()==null)
            throw new DybRuntimeException("商户添加时，行业类别不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getMainBusiness()))
            throw new DybRuntimeException("商户添加时，主营业务不能为空");
        if (merchant.getScale()==null)
            throw new DybRuntimeException("商户添加时，商家规模不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getCountryPhone()))
            throw new DybRuntimeException("商户添加时，企业电话不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessStartTime()))
            throw new DybRuntimeException("商户添加时，营业开始时间不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessEndTime()))
            throw new DybRuntimeException("商户添加时，营业结束时间不能为空");
//        if (!this.isValidation(merchant.getBusinessStartTime()))
//            throw new DybRuntimeException("商户添加时，营业开始时间的格式不正确");
//        if (!this.isValidation(merchant.getBusinessEndTime()))
//            throw new DybRuntimeException("商户添加时，营业结束时间的格式不正确");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantDescription()))
            throw new DybRuntimeException("商户添加时，商家简介不能为空");
//        if (DybUtils.isEmptyOrNull(merchant.getLongitude()))
//            throw new DybRuntimeException("商户添加时，经度不能为空");
//        if (DybUtils.isEmptyOrNull(merchant.getLatitude()))
//            throw new DybRuntimeException("商户添加时，纬度不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getAccountCode()))
            throw new DybRuntimeException("商户添加时，关联的账户不能为空");

        //负责人信息验证
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalName()))
            throw new DybRuntimeException("商户添加时，负责人姓名不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalJobs()))
            throw new DybRuntimeException("商户添加时，负责人岗位不能为空");
        if (merchant.getPrincipalSex()==null)
            throw new DybRuntimeException("商户添加时，负责人性别必须选择");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalIdCard()))
            throw new DybRuntimeException("商户添加时，负责人身份证号码不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalEmail()))
            throw new DybRuntimeException("商户添加时，负责人邮箱地址不能为空");
        if (merchant.getBirthday()==null)
            throw new DybRuntimeException("商户添加时，负责人出生日期不能为空");

        //添加新商家
        merchant.setMerchantCode(UUID.randomUUID().toString());
        int info=merchantDao.insertObject(merchant);
        return info>0?merchant:null;
    }

    /**
     * 验证时间格式——>  01:30
     * @param time 验证时间
     * @return true表示格式正确 false表示格式不正确
     */
    private boolean isValidation(String time){
        //TODO 验证时间
        Pattern p = Pattern.compile("");
        Matcher m = p.matcher(time);
        boolean flg = m.matches();
        return flg;
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

    /**
     * 根据商家code更新商家地理位置
     * @param merchantCode 商家code
     * @param address 地址
     * @param longitude 经度
     * @param latitude 纬度
     * @return 商家信息
     */
    @Override
    public Merchant updateMerchantAddressByCode(String merchantCode, String address, String longitude, String latitude) {
        if (DybUtils.isEmptyOrNull(merchantCode))
            throw new DybRuntimeException("根据商家code更新商家地理位置时，code不能为空");
        if (DybUtils.isEmptyOrNull(address))
            throw new DybRuntimeException("根据商家code更新商家地理位置时，地址不能为空");
        if (DybUtils.isEmptyOrNull(longitude))
            throw new DybRuntimeException("根据商家code更新商家地理位置时，经度不能为空");
        if (DybUtils.isEmptyOrNull(latitude))
            throw new DybRuntimeException("根据商家code更新商家地理位置时，纬度不能为空");
        Merchant merchant = merchantDao.getObject(merchantCode,true);
        if (merchant==null)
            throw new DybRuntimeException("根据商家code更新商家地理位置时，找不到此商家信息");
        merchant.setMerchantAddress(address);
        merchant.setLongitude(longitude);
        merchant.setLatitude(latitude);
        int info = merchantDao.updateObject(merchant);
        return info>0?merchant:null;
    }

    /**
     * 根据商家code 修改商家信息
     * @param merchant 商家对象
     * @return 商家对象
     */
    @Override
    public Merchant updateMerchantByCode(Merchant merchant) {
        if (merchant==null)
            throw new DybRuntimeException("商户资料修改时，merchant对象不能为空或null");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantCode()))
            throw new DybRuntimeException("商户资料修改时，merchantCode不能为空或null");
        if (merchant.getIndustry()==null)
            throw new DybRuntimeException("商户资料修改时，行业不能为空");
        if (merchant.getScale()==null)
            throw new DybRuntimeException("商户资料修改时，商家规模不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getMainBusiness()))
            throw new DybRuntimeException("商户资料修改时，主营业务不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getCountryPhone()))
            throw new DybRuntimeException("商户资料修改时，企业电话不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessStartTime()))
            throw new DybRuntimeException("商户资料修改时，营业开始时间不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getBusinessEndTime()))
            throw new DybRuntimeException("商户资料修改时，营业结束时间不能为空");
//        if (!this.isValidation(merchant.getBusinessStartTime()))
//            throw new DybRuntimeException("商户资料修改时，营业开始时间的格式不正确");
//        if (!this.isValidation(merchant.getBusinessEndTime()))
//            throw new DybRuntimeException("商户资料修改时，营业结束时间的格式不正确");
        if (DybUtils.isEmptyOrNull(merchant.getMerchantDescription()))
            throw new DybRuntimeException("商户资料修改时，商家简介不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getAccountCode()))
            throw new DybRuntimeException("商户资料修改时，关联的账户不能为空");

        //负责人信息验证
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalName()))
            throw new DybRuntimeException("商户资料修改时，负责人姓名不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalJobs()))
            throw new DybRuntimeException("商户资料修改时，负责人岗位不能为空");
        if (merchant.getPrincipalSex()==null)
            throw new DybRuntimeException("商户资料修改时，负责人性别必须选择");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalIdCard()))
            throw new DybRuntimeException("商户资料修改时，负责人身份证号码不能为空");
        if (DybUtils.isEmptyOrNull(merchant.getPrincipalEmail()))
            throw new DybRuntimeException("商户资料修改时，负责人邮箱地址不能为空");

        Merchant updateMerchant=merchantDao.getObject(merchant.getMerchantCode(),true);
        if (updateMerchant==null)
            throw new DybRuntimeException("商户资料修改时，找不到此商家信息");
        int info = merchantDao.updateObject(merchant);
        return info>0?merchant:null;
    }
}
