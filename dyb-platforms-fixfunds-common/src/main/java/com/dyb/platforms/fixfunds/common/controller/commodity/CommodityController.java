package com.dyb.platforms.fixfunds.common.controller.commodity;

import com.dyb.platforms.fixfunds.common.controller.commodity.model.CommodityModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.commodity.entity.Commodity;
import com.dyb.platforms.fixfunds.services.business.commodity.service.ICommodityService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/commodity")
public class CommodityController extends BaseController {

    public Logger log = Logger.getLogger(CommodityController.class);//日志

    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private IAccountService accountService;

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
     * 获取分页商品列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCommodityPageList")
    public Object getCommodityPageList(HttpServletRequest request,HttpServletResponse response, @RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取分页商品列表");
        PageList<CommodityModel> commodityModelPageList=new PageList<>();
        List<CommodityModel> commodityModelList=new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime",true);
        PageList<Commodity> commodityPageList=commodityService.getCommodityPageList(queryParams,pageIndex,pageSize,true);
        for (Commodity commodity:commodityPageList.getList()){
            CommodityModel commodityModel=new CommodityModel();
            Account account=accountService.getAccountByCode(commodity.getAccountCode(),true);
            if (account==null)
                return validationResult(1001,"找不到此商家信息");
            commodityModel.setAccount(account);
            commodityModel.setCommodity(commodity);
            commodityModelList.add(commodityModel);
        }
        commodityModelPageList.setPageIndex(commodityPageList.getPageIndex());
        commodityModelPageList.setPageSize(commodityPageList.getPageSize());
        commodityModelPageList.setPageCount(commodityPageList.getPageCount());
        commodityModelPageList.setTotalSize(commodityPageList.getTotalSize());
        commodityModelPageList.setList(commodityModelList);
        return result(commodityModelPageList);
    }

}
