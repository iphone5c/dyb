package com.dyb.platforms.fixfunds.services.business.user.service;


import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.business.user.entity.em.UserStatus;
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
     * 根据用户登录名获取用户
     * @param username
     * @return
     */
    public User getUserByUserName(String username);

    /**
     * 根据code查询用户信息
     * @param userCode 用户Code
     * @return 用户信息
     */
    public User getUserByCode(String userCode);

    /**
     *获取用户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<User> getUserPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 禁用指定用户
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableUser(String userCode);

    /**
     * 将指定用户解除禁用
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean removeDisableUser(String userCode);

    /**
     * 操作指定用户额状态
     * @param userCode 用户code
     * @param userStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationUserStatus(String userCode,UserStatus userStatus);

    /**
     * 将指定用户重置密码
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean resetUserPassword(String userCode);

    /**
     * 指定用户修改密码
     * @param userCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    public boolean modifyUserPassword(String userCode,String oldPassword,String newPassword,String confirmPassword);
}
