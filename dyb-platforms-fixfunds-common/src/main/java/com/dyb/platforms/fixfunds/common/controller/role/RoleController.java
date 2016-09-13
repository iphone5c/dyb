package com.dyb.platforms.fixfunds.common.controller.role;

import com.dyb.platforms.fixfunds.services.business.user.entity.Role;
import com.dyb.platforms.fixfunds.services.business.user.service.IRoleService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
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
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    public Logger log = Logger.getLogger(RoleController.class);//日志

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/getRolePageList")
    public Object getRolePageList(int pageIndex,int pageSize){
        log.info("获取角色列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("sequence",true);
        return result(roleService.getRolePageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getRoleByCode")
    public Object getRoleByCode(String roleCode){
        log.info("根据角色code获取角色信息，code：" + roleCode);
        if (DybUtils.isEmptyOrNull(roleCode))
            return validationResult(1001,"获取指定角色信息，code不能为空");
        Role role = roleService.getRoleByCode(roleCode);
        if (role==null)
            return validationResult(1001,"找不到此角色的信息，code："+roleCode);
        else
            return result(role);
    }

    @RequestMapping(value = "/createRole")
    public Object createRole(Role role){
        log.info("新建角色信息" );
        if (role==null)
            return validationResult(1001,"新建角色信息时，角色对象不能为空或null");
        if (DybUtils.isEmptyOrNull(role.getRoleName()))
            return validationResult(1001,"新建角色信息时，角色名称不能为空或null");
        Role temp = roleService.createRole(role);
        if (temp==null)
            return validationResult(1001,"新建角色失败");
        else
            return result(role);
    }

    @RequestMapping(value = "/updateRole")
    public Object updateRole(Role role){
        log.info("修改角色信息" );
        if (role==null)
            return validationResult(1001,"修改角色信息时，角色对象不能为空或null");
        if (DybUtils.isEmptyOrNull(role.getRoleCode()))
            return validationResult(1001,"修改角色信息时，必须选择一个需要修改的角色");
        if (DybUtils.isEmptyOrNull(role.getRoleName()))
            return validationResult(1001,"修改角色信息时，角色名称不能为空或null");
        Role updateRole=roleService.getRoleByCode(role.getRoleCode());
        if (updateRole==null)
            return validationResult(1001,"找不到此角色信息，code："+role.getRoleCode());
        updateRole.setRoleName(role.getRoleName());
        updateRole.setSequence(role.getSequence());
        updateRole.setDescription(role.getDescription());
        Role temp = roleService.modifyRole(updateRole);
        if (temp==null)
            return validationResult(1001,"修改角色失败");
        else
            return result(role);
    }

}
