package com.dyb.platforms.fixfunds.common.controller.permissions;

import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/permissions")
public class PermissionsController extends BaseController {

    public Logger log = Logger.getLogger(PermissionsController.class);//日志

    @RequestMapping(value = "/getPermissionsList")
    public Object getPermissionsList(int pageIndex,int pageSize){
        log.info("获取权限列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("sequence",true);
        return result("");
    }

}
