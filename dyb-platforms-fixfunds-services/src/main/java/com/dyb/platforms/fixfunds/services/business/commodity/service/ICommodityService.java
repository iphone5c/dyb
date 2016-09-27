package com.dyb.platforms.fixfunds.services.business.commodity.service;


import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ICommodityService {

    /**
     * 新增商品
     * @param commodity 商品对象
     * @return 商品对象
     */
    public Commodity createCommodity(Commodity commodity);

    /**
     * 修改商品
     * @param commodity 商品对象
     * @return 商品对象
     */
    public Commodity modifyCommodity(Commodity commodity);

    /**
     * 根据code删除商品
     * @param commodityCode 商品code
     * @return true表示成功 false表示失败
     */
    public Boolean deleteCommodity(String commodityCode);

    /**
     * 批量删除商品信息
     * @param commodityCodeList 商品code集合
     * @return true表示成功 false表示失败
     */
    public Boolean deleteCommodityList(String[] commodityCodeList);

    /**
     * 根据code查询商品信息
     * @param commodityCode 商品Code
     * @return 商品信息
     */
    public Commodity getCommodityByCode(String commodityCode);

    /**
     *获取商品分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Commodity> getCommodityPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

}
