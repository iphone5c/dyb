package com.dyb.platforms.fixfunds.services.business.serviceproviders.service;


import com.dyb.platforms.fixfunds.services.business.serviceproviders.entity.ServiceProviders;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IServiceProvidersService {

    /**
     * 根据服务商code查找指定服务商信息
     * @param serviceProvidersCode 服务商code
     * @return 服务商信息
     */
    public ServiceProviders getServiceProvidersByCode(String serviceProvidersCode);

    /**
     * 添加服务商信息
     * @param serviceProviders 服务商对象
     * @return 服务商对象
     */
    public ServiceProviders createServiceProviders(ServiceProviders serviceProviders);
}
