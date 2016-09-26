package com.dyb.platforms.fixfunds.services.business.serviceproviders.service;

import com.dyb.platforms.fixfunds.services.business.bankaccount.service.IBankAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.dao.IServiceProvidersDao;
import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("serviceProvidersService")
public class ServiceProvidersService extends BaseService implements IServiceProvidersService {

    public Logger log = Logger.getLogger(ServiceProvidersService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IServiceProvidersDao serviceProvidersDao;
    @Autowired
    private IBankAccountService bankAccountService;

    /**
     * 根据服务商code查找指定服务商信息
     * @param serviceProvidersCode 服务商code
     * @return 服务商信息
     */
    @Override
    public ServiceProviders getServiceProvidersByCode(String serviceProvidersCode) {
        if (DybUtils.isEmptyOrNull(serviceProvidersCode))
            throw new DybRuntimeException("根据code查找服务商信息时，code不能为空");
        return serviceProvidersDao.getObject(serviceProvidersCode,true);
    }

    /**
     * 添加服务商信息
     * @param serviceProviders 服务商对象
     * @return 服务商对象
     */
    @Override
    public ServiceProviders createServiceProviders(ServiceProviders serviceProviders) {
        if (serviceProviders==null)
            throw new DybRuntimeException("服务商添加时，serviceProviders对象不能为空或null");
        if (DybUtils.isEmptyOrNull(serviceProviders.getServiceProviderName()))
            throw new DybRuntimeException("服务商添加时，姓名不能为空");
        if (serviceProviders.getSex()==null)
            throw new DybRuntimeException("服务商添加时，性别不能为空");
        if (serviceProviders.getBirthday()==null)
            throw new DybRuntimeException("服务商添加时，出生日期不能为空");
        if (serviceProviders.getIndustry()==null)
            throw new DybRuntimeException("服务商添加时，行业不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getIdCard()))
            throw new DybRuntimeException("服务商添加时，身份证号不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getEmail()))
            throw new DybRuntimeException("服务商添加时，邮箱不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getAddress()))
            throw new DybRuntimeException("服务商添加时，地址不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getProvince()))
            throw new DybRuntimeException("服务商添加时，省级代码不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getCity()))
            throw new DybRuntimeException("服务商添加时，市级代码不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getCertificateFile()))
            throw new DybRuntimeException("服务商添加时，证件资料不能为空");
        if (DybUtils.isEmptyOrNull(serviceProviders.getAccountCode()))
            throw new DybRuntimeException("服务商添加时，账户code不能为空");
        serviceProviders.setServiceProviderCode(UUID.randomUUID().toString());
        int info = serviceProvidersDao.insertObject(serviceProviders);
        return info>0?serviceProviders:null;
    }
}
