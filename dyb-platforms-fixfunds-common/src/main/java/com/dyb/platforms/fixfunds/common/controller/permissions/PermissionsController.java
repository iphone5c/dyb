package com.dyb.platforms.fixfunds.common.controller.permissions;

import com.dyb.platforms.fixfunds.services.business.user.entity.Permissions;
import com.dyb.platforms.fixfunds.services.business.user.service.IPermissionsService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
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

    @RequestMapping(value = "/getPermissionsByCode")
    public Object getPermissionsByCode(String permissionsCode){
        if(DybUtils.isEmptyOrNull(permissionsCode))
            return validationResult(1001,"获取权限时,permissionsCode不能为空或null");
        Permissions permissions=permissionsService.getPermissionsByCode(permissionsCode);
        if (permissions==null)
            return validationResult(1001,"获取权限时，找不到此权限信息，code："+permissionsCode);
        return result(permissions);
    }

    @RequestMapping(value = "/createPermissions")
    public Object createPermissions(Permissions permissions){
        if (permissions==null)
            return validationResult(1001,"新建权限时，权限对象不能为空或null");
        if(DybUtils.isEmptyOrNull(permissions.getPermissionsName()))
            return validationResult(1001,"新建权限时，权限名称不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsUrl()))
            return validationResult(1001,"新建权限时，权限访问地址不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getParentCode()))
            return validationResult(1001,"新建权限时，必须选择一项父级权限");
        Permissions temp=permissionsService.createPermissions(permissions);
        if (temp==null)
            return validationResult(1001,"新建失败");
        else
            return result(temp);
    }

    @RequestMapping(value = "/updatePermissions")
    public Object updatePermissions(Permissions permissions){
        if (permissions==null)
            return validationResult(1001,"修改权限时，权限对象不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsCode()))
            return validationResult(1001,"修改权限时，必须选择一项需要修改的数据");
        if(DybUtils.isEmptyOrNull(permissions.getPermissionsName()))
            return validationResult(1001,"修改权限时，权限名称不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsUrl()))
            return validationResult(1001,"修改权限时，权限访问地址不能为空或null");
        Permissions updatePermissions=permissionsService.getPermissionsByCode(permissions.getPermissionsCode());
        if (updatePermissions==null)
            return validationResult(1001,"修改权限时，找不到此权限信息，code："+permissions.getPermissionsCode());
        updatePermissions.setPermissionsName(permissions.getPermissionsName());
        updatePermissions.setPermissionsUrl(permissions.getPermissionsUrl());
        updatePermissions.setDescription(permissions.getDescription());
        Permissions temp=permissionsService.modifyPermissions(updatePermissions);
        if (temp==null)
            return validationResult(1001,"新建失败");
        else
            return result(temp);
    }

}
