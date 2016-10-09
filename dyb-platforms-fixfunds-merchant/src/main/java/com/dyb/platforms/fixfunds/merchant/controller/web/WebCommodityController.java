package com.dyb.platforms.fixfunds.merchant.controller.web;

import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;
import com.dyb.platforms.fixfunds.services.business.commodity.service.ICommodityService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/merchant/commodity")
public class WebCommodityController extends BaseController {

    public Logger log = Logger.getLogger(WebCommodityController.class);//日志

    @Autowired
    private ICommodityService commodityService;

    /**
     * 商品添加
     * @param request
     * @param response
     * @param commodity 商品信息
     */
    @RequestMapping(value = "/addCommodity")
    public Object addCommodity(HttpServletRequest request,HttpServletResponse response,Commodity commodity) {
        log.info("商品添加");
        if (commodity==null)
            return validationResult(1001,"商品信息不能为空");
        commodity.setAccountCode(DybUtils.getCurrentAccount(request).getAccountCode());
        Commodity temp=commodityService.createCommodity(commodity);
        if (temp==null){
            return validationResult(1001,"添加失败");
        }else {
            return result("添加成功");
        }
    }

    /**
     * 修改商品信息
     * @param request
     * @param response
     * @param commodity 修改
     */
    @RequestMapping(value = "/updateCommodity")
    public Object updateCommodity(HttpServletRequest request,HttpServletResponse response,Commodity commodity){
        log.info("商品修改");
        if (commodity==null)
            return validationResult(1001,"商品信息不能为空");
        Commodity updateCommodity = commodityService.getCommodityByCode(commodity.getCommodityCode());
        if (updateCommodity==null)
            return validationResult(1001,"没有找到此商品信息code:"+commodity.getCommodityCode());
        updateCommodity.setName(commodity.getName());
        updateCommodity.setCommodityNum(commodity.getCommodityNum());
        updateCommodity.setSpecifications(commodity.getSpecifications());
        updateCommodity.setPrice(commodity.getPrice());
        Commodity temp=commodityService.modifyCommodity(updateCommodity);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result("修改成功");
        }
    }

    /**
     * 根据code删除商品信息
     * @param request
     * @param response
     * @param commodityCode 商品code
     */
    @RequestMapping(value = "/deleteCommodity")
    public Object deleteCommodity(HttpServletRequest request,HttpServletResponse response,String commodityCode){
        log.info("商品删除");
        if (DybUtils.isEmptyOrNull(commodityCode))
            return validationResult(1001,"商品删除时，code不能为空");
        boolean flag=commodityService.deleteCommodity(commodityCode);
        if (flag)
            return result("删除成功");
        else
            return validationResult(1001,"删除失败");
    }

    /**
     * 批量删除商品信息
     * @param request
     * @param response
     * @param commodityCodeList 商品code集合（格式：20108196524,2100254565,20132645....）
     */
    @RequestMapping(value = "/deleteCommodityList")
    public Object deleteCommodityList(HttpServletRequest request,HttpServletResponse response,String commodityCodeList){
        log.info("批量删除商品信息");
        if (DybUtils.isEmptyOrNull(commodityCodeList))
            return validationResult(1001,"批量删除商品信息时，commodityCodeList不能为空");

        boolean flag=commodityService.deleteCommodityList(commodityCodeList.split(","));
        if (flag)
            return result("删除成功");
        else
            return validationResult(1001,"删除失败");
    }

    /**
     * 根据code查询商品信息
     * @param request
     * @param response
     * @param commodityCode 商品code
     */
    @RequestMapping(value = "/getCommodityByCode")
    public Object getCommodityByCode(HttpServletRequest request,HttpServletResponse response,String commodityCode){
        log.info("根据code查询商品信息");
        if (DybUtils.isEmptyOrNull(commodityCode))
            return validationResult(1001,"根据code查询商品信息时，code不能为空");
        Commodity commodity=commodityService.getCommodityByCode(commodityCode);
        if (commodity==null)
            return validationResult(1001,"找不到此商品信息");
        else
            return result(commodity);
    }

    /**
     * 根据当前登陆商家获取分页商品列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCommodityPageList")
    public Object getCommodityPageList(HttpServletRequest request,HttpServletResponse response, int pageIndex, int pageSize){
        log.info("根据当前登陆商家获取分页商品列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",DybUtils.getCurrentAccount(request).getAccountCode());
        return result(commodityService.getCommodityPageList(queryParams,pageIndex,pageSize,true));
    }

}
