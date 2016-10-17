package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.common.controller.gyxm.model.BlacklistModel;
import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.blacklist.entity.Blacklist;
import com.dyb.platforms.fixfunds.services.business.blacklist.service.IBlacklistService;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/blacklist")
public class WebBlacklistController extends BaseController {

    public Logger log = Logger.getLogger(WebBlacklistController.class);//日志

    @Autowired
    private IBlacklistService blacklistService;
    @Autowired
    private IAccountService accountService;

    /**
     * 获取黑名单列表分页
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "/getBlacklistPageList")
    public Object getBlacklistPageList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int pageIndex,@RequestParam(required=false,defaultValue="20")int pageSize){
        log.info("获取黑名单列表分页");
        PageList<BlacklistModel> blacklistModelPageList=new PageList<>();
        List<BlacklistModel> blacklistModelList=new ArrayList<>();
        PageList<Blacklist> blacklistPageList=blacklistService.getBlacklistPageList(new QueryParams(),pageIndex,pageSize,true);
        for (Blacklist blacklist:blacklistPageList.getList()){
            Account merchant=accountService.getAccountByCode(blacklist.getMerchantCode(),true);
            if (merchant==null)
                return validationResult(1001,"找不到此商家信息");
            BlacklistModel blacklistModel=new BlacklistModel();
            blacklistModel.setBlacklist(blacklist);
            blacklistModel.setMerchant(merchant.getMerchant());
            blacklistModelList.add(blacklistModel);
        }
        blacklistModelPageList.setPageCount(blacklistPageList.getPageCount());
        blacklistModelPageList.setPageIndex(blacklistPageList.getPageIndex());
        blacklistModelPageList.setPageSize(blacklistPageList.getPageSize());
        blacklistModelPageList.setTotalSize(blacklistPageList.getTotalSize());
        blacklistModelPageList.setList(blacklistModelList);
        return result(blacklistModelPageList);
    }

}
