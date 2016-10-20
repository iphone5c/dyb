package com.dyb.platforms.fixfunds.services.business.salesman.service;

import com.dyb.platforms.fixfunds.services.business.salesman.dao.ISalesmanDao;
import com.dyb.platforms.fixfunds.services.business.salesman.entity.Salesman;
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
@Service("salesmanService")
public class SalesmanService extends BaseService implements ISalesmanService {

    public Logger log = Logger.getLogger(SalesmanService.class);//日志

    @Autowired
    private ISalesmanDao salesmanDao;

    /**
     * 根据业务员code查找业务员信息
     * @param salesmanCode 业务员code
     * @return 业务员信息
     */
    @Override
    public Salesman getSalesmanByCode(String salesmanCode) {
        if (DybUtils.isEmptyOrNull(salesmanCode))
            throw new DybRuntimeException("根据code查找业务员信息时，code不能为空");
        return salesmanDao.getObject(salesmanCode,true);
    }

    /**
     * 添加业务员详情新
     * @param salesman 业务员对象
     * @return 业务员对象
     */
    @Override
    public Salesman createSalesman(Salesman salesman) {
        if (salesman==null)
            throw new DybRuntimeException("业务员添加时，salesman对象不能为空或null");
        if (DybUtils.isEmptyOrNull(salesman.getRealName()))
            throw new DybRuntimeException("业务员添加时，真实姓名不能为空");
        if (salesman.getSex()==null)
            throw new DybRuntimeException("业务员添加时，性别不能为空");
        if (salesman.getBirthday()==null)
            throw new DybRuntimeException("业务员添加时，出生日期不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getNativePlace()))
            throw new DybRuntimeException("业务员添加时，籍贯不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getProvince()))
            throw new DybRuntimeException("业务员添加时，省级代码不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getCity()))
            throw new DybRuntimeException("业务员添加时，市级代码不能为空");
        if (salesman.getCertificate()==null)
            throw new DybRuntimeException("业务员添加时，证件类型不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getCertificateNumber()))
            throw new DybRuntimeException("业务员添加时，证件号码不能为空");
        if (salesman.getIndustry()==null)
            throw new DybRuntimeException("业务员添加时，所属行业不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getSalesmanEmail()))
            throw new DybRuntimeException("业务员添加时，邮箱地址不能为空");
        if (DybUtils.isEmptyOrNull(salesman.getAccountCode()))
            throw new DybRuntimeException("业务员添加时，业务员所属账户不能为空");
        salesman.setSalesmanCode(UUID.randomUUID().toString());
        int info = salesmanDao.insertObject(salesman);
        return info>0?salesman:null;
    }
}
