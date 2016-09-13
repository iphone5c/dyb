package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.user.dao.IUserDao;
import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.business.user.entity.em.UserStatus;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("userService")
public class UserService extends BaseService implements IUserService {

    public Logger log = Logger.getLogger(UserService.class);//日志

    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    /**
     * 新增用户
     * @param user 用户对象
     * @return 用户对象
     */
    @Override
    public User createUser(User user) {
        if (user==null)
            throw new DybRuntimeException("新增用户时，user对象不能为空");
        if (DybUtils.isEmptyOrNull(user.getUserName()))
            throw new DybRuntimeException("新增用户时，用户名不能为空");
        if (this.getUserByUserName(user.getUserName())!=null)
            throw new DybRuntimeException("新增用户时，用户名已存在");
        if (DybUtils.isEmptyOrNull(user.getUserPassword()))
            user.setUserPassword("ABC123");
        user.setUserCode(codeBuilder.getUserCode());
        user.setUserPassword(DybUtils.encryptPassword(user.getUserPassword()));
        user.setStatus(UserStatus.正常);
        user.setCreateTime(new Date());
        int info=userDao.insertObject(user);
        return info>0?user:null;
    }

    /**
     * 修改用户
     * @param user 用户对象
     * @return 用户对象
     */
    @Override
    public User modifyUser(User user) {
        if (user==null)
            throw new DybRuntimeException("修改用户时，user对象不能为空");
        if (DybUtils.isEmptyOrNull(user.getUserCode()))
            throw new DybRuntimeException("修改用户时，用户主键不能为空");
        if (userDao.getObject(user.getUserCode(),true)==null)
            throw new DybRuntimeException("修改用户时，找不到此用户的基本信息，用户主键为："+user.getUserCode());
        if (DybUtils.isEmptyOrNull(user.getUserName()))
            throw new DybRuntimeException("修改用户时，用户名不能为空");
        User temp=this.getUserByUserName(user.getUserName());
        if (!(temp==null||temp.getUserCode().equals(user.getUserCode())))
            throw new DybRuntimeException("修改用户时，用户名已存在");
        int info=userDao.updateObject(user);
        return info>0?user:null;
    }

    /**
     * 根据用户登录名获取用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("userName",username);
        List<User> userList= userDao.queryList(queryParams,0,-1,false);
        return ((userList!=null&&userList.size()>0)?userList.get(0):null);
    }

    /**
     * 根据code查询用户信息
     * @param userCode 用户Code
     * @return 用户信息
     */
    @Override
    public User getUserByCode(String userCode) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("查询用户信息时，code不能为空或null");
        return userDao.getObject(userCode,true);
    }

    /**
     *获取用户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<User> getUserPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return userDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 禁用指定用户
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableUser(String userCode) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("禁用指定用户时，code不能为空或null");
        return this.operationUserStatus(userCode,UserStatus.禁用);
    }

    /**
     * 将指定用户解除禁用
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean removeDisableUser(String userCode) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("解除指定用户的禁用状态时，code不能为空或null");
        return this.operationUserStatus(userCode,UserStatus.正常);
    }

    /**
     * 操作指定用户额状态
     * @param userCode 用户code
     * @param userStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationUserStatus(String userCode, UserStatus userStatus) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("操作指定用户的状态时，code不能为空或null");
        if (userStatus==null)
            throw new DybRuntimeException("操作指定用户的状态时，修改的用户状态不能为空");
        User user=userDao.getObject(userCode,true);
        if (user==null)
            throw new DybRuntimeException("操作指定用户的状态时，找不到此用户信息，code："+userCode);
        user.setStatus(userStatus);
        int info=userDao.updateObject(user);
        return info>0?true:false;
    }

    /**
     * 将指定用户重置密码
     * @param userCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean resetUserPassword(String userCode) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("指定用户重置密码时，code不能为空或null");
        User user=userDao.getObject(userCode,true);
        if (user==null)
            throw new DybRuntimeException("指定用户重置密码时，找不到此用户信息，code："+userCode);
        user.setUserPassword(DybUtils.encryptPassword("ABC123"));
        int info=userDao.updateObject(user);
        return info>0?true:false;
    }

    /**
     * 指定用户修改密码
     * @param userCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean modifyUserPassword(String userCode, String oldPassword, String newPassword, String confirmPassword) {
        if (DybUtils.isEmptyOrNull(userCode))
            throw new DybRuntimeException("指定用户修改密码时，code不能为空或null");
        if (DybUtils.isEmptyOrNull(oldPassword))
            throw new DybRuntimeException("指定用户修改密码时，旧密码不能为空或null");
        if (DybUtils.isEmptyOrNull(newPassword))
            throw new DybRuntimeException("指定用户修改密码时，新密码不能为空或null");
        if (DybUtils.isEmptyOrNull(confirmPassword))
            throw new DybRuntimeException("指定用户修改密码时，确认密码不能为空或null");
        User user=userDao.getObject(userCode,true);
        if (user==null)
            throw new DybRuntimeException("指定用户修改密码时，找不到此用户信息，code："+userCode);
        if (!DybUtils.verifyPassword(oldPassword,user.getUserPassword()))
            throw new DybRuntimeException("指定用户修改密码时，旧密码输出错误");
        if (!newPassword.equals(confirmPassword))
            throw new DybRuntimeException("指定用户修改密码时，新密码与确认密码输入不一致");
        user.setUserPassword(DybUtils.encryptPassword(newPassword));
        int info=userDao.updateObject(user);
        return info>0?true:false;
    }
}
