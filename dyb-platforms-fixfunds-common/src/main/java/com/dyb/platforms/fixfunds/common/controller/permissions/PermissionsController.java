package com.dyb.platforms.fixfunds.common.controller.permissions;

import com.dyb.platforms.fixfunds.services.business.user.entity.Permissions;
import com.dyb.platforms.fixfunds.services.business.user.service.IPermissionsService;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/permissions")
public class PermissionsController extends BaseController {

    public Logger log = Logger.getLogger(PermissionsController.class);//日志

    @Autowired
    private IPermissionsService permissionsService;

    @RequestMapping(value = "/getPermissionsList")
    public Object getPermissionsList(){
        log.info("获取所有权限列表");
        Permissions permissions=permissionsService.getPermissions();

        return result(permissions);
    }

}
