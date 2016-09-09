package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.user.dao.IUserDao;
import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("userService")
public class UserService implements IUserService {

    public Logger log = Logger.getLogger(UserService.class);//日志

    @Autowired
    private IUserDao userDao;

    /**
     * 新增用户
     * @param user 用户对象
     * @return 用户对象
     */
    @Override
    public User createUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
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
        int info=userDao.updateObject(user);
        return info>0?user:null;
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
}
