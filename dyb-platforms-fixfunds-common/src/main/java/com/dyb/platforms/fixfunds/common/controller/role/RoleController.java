package com.dyb.platforms.fixfunds.common.controller.role;

import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    public Logger log = Logger.getLogger(RoleController.class);//日志

    @RequestMapping(value = "/getRoleList")
    public Object getRoleList(int pageIndex,int pageSize){
        log.info("获取角色列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("sequence",true);
        return result("");
    }

}
