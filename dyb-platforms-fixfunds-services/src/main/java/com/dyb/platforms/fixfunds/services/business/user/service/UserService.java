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
