package com.dyb.platforms.fixfunds.services.business.user.service;


import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

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

    /**
     * 修改用户
     * @param user 用户对象
     * @return 用户对象
     */
    public User modifyUser(User user);

    /**
     *获取用户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<User> getUserPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);
}
