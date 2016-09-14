package com.dyb.platforms.fixfunds.common.controller.systemlog;

import com.dyb.platforms.fixfunds.services.business.systemlog.service.IUserLogService;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/userlog")
public class UserLogController extends BaseController {

    public Logger log = Logger.getLogger(UserLogController.class);//日志

    @Autowired
    private IUserLogService userLogService;

    @RequestMapping(value = "/getSystemParamsList")
    public Object getUserLogPageList(int pageIndex,int pageSize){
        log.info("获取用户操作日志列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("createTime",false);
        return result(userLogService.getUserLogPageList(queryParams, pageIndex, pageSize, true));
    }

}
