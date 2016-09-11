package com.dyb.platforms.fixfunds.common.controller.user;

import com.dyb.platforms.fixfunds.services.business.user.service.IUserService;
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
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    public Logger log = Logger.getLogger(UserController.class);//日志

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUserList")
    public Object getUserList(int pageIndex,int pageSize){
        log.info("获取用户列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("sequence",true);
        return result(userService.getUserPageList(queryParams,pageIndex,pageSize,true));
    }

}
