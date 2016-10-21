package com.dyb.platforms.fixfunds.services.business.salesman.service;

import com.dyb.platforms.fixfunds.services.business.salesman.entity.Salesman;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ISalesmanService {

    /**
     * 根据业务员code查找业务员信息
     * @param salesmanCode 业务员code
     * @return 业务员信息
     */
    public Salesman getSalesmanByCode(String salesmanCode);

    /**
     * 添加业务员详情新
     * @param salesman 业务员对象
     * @return 业务员对象
     */
    public Salesman createSalesman(Salesman salesman);

}
