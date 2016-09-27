package com.dyb.platforms.fixfunds.services.business.commodity.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.commodity.dao.ICommodityDao;
import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
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
@Service("commodityService")
public class CommodityService extends BaseService implements ICommodityService {

    public Logger log = Logger.getLogger(CommodityService.class);//日志

    @Autowired
    private ICommodityDao commodityDao;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICodeBuilder codeBuilder;
    /**
     * 新增商品
     * @param commodity 商品对象
     * @return 商品对象
     */
    @Override
    public Commodity createCommodity(Commodity commodity) {
        if (commodity==null)
            throw new DybRuntimeException("新增商品时，commodity对象不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getName()))
            throw new DybRuntimeException("新增商品时，商品名不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getCommodityNum()))
            throw new DybRuntimeException("新增商品时，商品编号不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getSpecifications()))
            throw new DybRuntimeException("新增商品时，规格型号不能为空");
        if (commodity.getPrice()<0)
            throw new DybRuntimeException("新增商品时，商品单价必须大于零");
        if (DybUtils.isEmptyOrNull(commodity.getAccountCode()))
            throw new DybRuntimeException("新增商品时，商品关联的商家账户不能为空");
        Account account=accountService.getAccountByCode(commodity.getAccountCode(), false);
        if (account==null||account.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("新增商品时，找不到此商家的信息");
        commodity.setCommodityCode(UUID.randomUUID().toString());
        commodity.setCreateTime(new Date());
        int info=commodityDao.insertObject(commodity);
        return info>0?commodity:null;
    }

    /**
     * 修改商品
     * @param commodity 商品对象
     * @return 商品对象
     */
    @Override
    public Commodity modifyCommodity(Commodity commodity) {
        if (commodity==null)
            throw new DybRuntimeException("修改商品时，commodity对象不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getCommodityCode()))
            throw new DybRuntimeException("修改商品时，商品主键不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getName()))
            throw new DybRuntimeException("修改商品时，商品名不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getCommodityNum()))
            throw new DybRuntimeException("修改商品时，商品编号不能为空");
        if (DybUtils.isEmptyOrNull(commodity.getSpecifications()))
            throw new DybRuntimeException("修改商品时，规格型号不能为空");
        if (commodity.getPrice()<0)
            throw new DybRuntimeException("修改商品时，商品单价必须大于零");
        if (DybUtils.isEmptyOrNull(commodity.getAccountCode()))
            throw new DybRuntimeException("修改商品时，商品关联的商家账户不能为空");
        Account account=accountService.getAccountByCode(commodity.getAccountCode(), false);
        if (account==null||account.getAccountType()!= AccountType.商家)
            throw new DybRuntimeException("修改商品时，找不到此商家的信息");
        int info=commodityDao.updateObject(commodity);
        return info>0?commodity:null;
    }

    /**
     * 根据code删除商品
     * @param commodityCode 商品code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteCommodity(String commodityCode) {
        if (DybUtils.isEmptyOrNull(commodityCode))
            throw new DybRuntimeException("删除商品时，code不能为空或null");
        if (commodityDao.getObject(commodityCode,true)==null)
            return true;
        int info=commodityDao.deleteObject(commodityCode);
        return info>0?true:false;
    }

    /**
     * 批量删除商品信息
     * @param commodityCodeList 商品code集合
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteCommodityList(String[] commodityCodeList) {
        if (commodityCodeList==null||commodityCodeList.length<=0)
            throw new DybRuntimeException("批量删除商品时，商品code集合不能为空或null");
        commodityDao.deleteList(commodityCodeList);
        return true;
    }

    /**
     * 根据code查询商品信息
     * @param commodityCode 商品Code
     * @return 商品信息
     */
    @Override
    public Commodity getCommodityByCode(String commodityCode) {
        if (DybUtils.isEmptyOrNull(commodityCode))
            throw new DybRuntimeException("查询商品信息时，code不能为空或null");
        return commodityDao.getObject(commodityCode,true);
    }

    /**
     *获取商品分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Commodity> getCommodityPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return commodityDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

}
