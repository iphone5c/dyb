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
}
