package com.dyb.platforms.fixfunds.services.business.user.service;


import com.dyb.platforms.fixfunds.services.business.user.entity.Permissions;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IPermissionsService {

    /**
     * 获取所有权限
     * @return
     */
    public Permissions getPermissions();

    /**
     * 根据code获取权限信息
     * @param permissionsCode 权限主键
     * @return 权限信息
     */
    public Permissions getPermissionsByCode(String permissionsCode);

    /**
     * 新增权限信息
     * @param permissions 权限对象
     * @return 权限对象
     */
    public Permissions createPermissions(Permissions permissions);

    /**
     * 修改权限信息
     * @param permissions 权限对象
     * @return 权限对象
     */
    public Permissions updatePermissions(Permissions permissions);
}
