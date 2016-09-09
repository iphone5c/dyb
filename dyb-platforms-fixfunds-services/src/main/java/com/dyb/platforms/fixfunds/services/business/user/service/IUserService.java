package com.dyb.platforms.fixfunds.services.business.user.service;


import com.dyb.platforms.fixfunds.services.business.user.entity.User;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IUserService {

    /**
     * 新增用户
     * @param user 用户对象
     * @return 用户对象
     */
    public User createUser(User user);
}
