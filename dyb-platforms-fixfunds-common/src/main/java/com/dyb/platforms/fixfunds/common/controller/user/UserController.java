package com.dyb.platforms.fixfunds.common.controller.user;

import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.business.user.service.IUserService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/commons/user")
public class UserController extends BaseController {

    public Logger log = Logger.getLogger(UserController.class);//日志

    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getUserPageList")
    public Object getUserPageList(int pageIndex,int pageSize){
        log.info("获取用户列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("sequence",true);
        return result(userService.getUserPageList(queryParams,pageIndex,pageSize,true));
    }

    /**
     * 获取指定用户信息
     * @param userCode 用户code
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserByCode")
    public Object getUserByCode(String userCode){
        if (DybUtils.isEmptyOrNull(userCode))
            return validationResult(1001,"查询指定用户时，code不能为空或null");
        User user=userService.getUserByCode(userCode);
        if (user==null){
            return validationResult(1001,"找不到指定用户信息，code："+userCode);
        }else {
            return result(user);
        }
    }

    /**
     * 新增用户信息
     * @param user
     * @param confirmPassword
     * @return
     */
    @RequestMapping(value = "/createUser")
    public Object createUser(User user,String confirmPassword){
        if (user==null)
            return validationResult(1001,"新建用户信息时，用户对象不能为空或null");
        if (DybUtils.isEmptyOrNull(user.getUserName()))
            return validationResult(1001,"新建用户信息时，用户名不能为空或null");
        if (DybUtils.isEmptyOrNull(user.getUserPassword()))
            return validationResult(1001,"新建用户信息时，用户密码不能为空或null");
        if (DybUtils.isEmptyOrNull(confirmPassword))
            return validationResult(1001,"新建用户信息时，确认密码不能为空");
        if (!user.getUserPassword().equals(confirmPassword))
            return validationResult(1001,"新建用户信息时，两次密码输入不一致");
        if (userService.getUserByUserName(user.getUserName())!=null)
            return validationResult(1001,"新建用户信息时，用户名已存在");
        User temp=userService.createUser(user);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser")
    public Object updateUser(User user){
        if (user==null)
            return validationResult(1001,"修改用户信息时，用户对象不能为空或null");
        if (DybUtils.isEmptyOrNull(user.getUserCode()))
            return validationResult(1001,"修改用户信息时，用户主键不能为空或null");
        User updateUser = userService.getUserByCode(user.getUserCode());
        if (updateUser==null)
            return validationResult(1001,"修改用户信息时，找不到此用户的信息，code："+user.getUserCode());
        if (DybUtils.isEmptyOrNull(user.getUserName()))
            return validationResult(1001,"修改用户信息时，用户名不能为空或null");
        User userNameValidation = userService.getUserByUserName(user.getUserName());
        if (!(userNameValidation==null || userNameValidation.getUserCode().equals(user.getUserCode())))
            return validationResult(1001,"修改用户信息时，用户名已存在");
        updateUser.setSequence(user.getSequence());
        updateUser.setUserName(user.getUserName());
        updateUser.setDescription(user.getDescription());
        User temp=userService.modifyUser(updateUser);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 禁用指定用户
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/disableUser")
    public Object disableUser(String userCode){
        if (DybUtils.isEmptyOrNull(userCode))
            return validationResult(1001,"禁用指定用户时，code不能为空或null");
        boolean flag=userService.disableUser(userCode);
        if (flag){
            return result("禁用成功");
        }else{
            return validationResult(1001,"禁用失败");
        }
    }

    /**
     * 将指定用户解除禁用
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/removeDisableUser")
    public Object removeDisableUser(String userCode){
        if (DybUtils.isEmptyOrNull(userCode))
            return validationResult(1001,"解除指定用户的禁用时，code不能为空或null");
        boolean flag=userService.removeDisableUser(userCode);
        if (flag){
            return result("解除禁用成功");
        }else{
            return validationResult(1001,"解除禁用失败");
        }
    }

    /**
     * 将指定用户修改密码
     * @param userCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return
     */
    @RequestMapping(value = "/updateUserPassword")
    public Object updateUserPassword(String userCode, String oldPassword, String newPassword, String confirmPassword){
        if (DybUtils.isEmptyOrNull(userCode))
            return validationResult(1001,"指定用户修改密码时，code不能为空或null");
        if (DybUtils.isEmptyOrNull(oldPassword))
            return validationResult(1001,"指定用户修改密码时，旧密码不能为空或null");
        if (DybUtils.isEmptyOrNull(newPassword))
            return validationResult(1001,"指定用户修改密码时，新密码不能为空或null");
        if (DybUtils.isEmptyOrNull(confirmPassword))
            return validationResult(1001,"指定用户修改密码时，确认密码不能为空或null");
        if (!newPassword.equals(confirmPassword))
            return validationResult(1001,"指定用户修改密码时，新密码与确认密码输入不一致");
        boolean flag=userService.modifyUserPassword(userCode,oldPassword,newPassword,confirmPassword);
        if (flag){
            return result("修改成功");
        }else{
            return validationResult(1001,"修改失败");
        }
    }

    /**
     * 指定用户重置密码
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/resetUserPassword")
    public Object resetUserPassword(String userCode){
        if (DybUtils.isEmptyOrNull(userCode))
            return validationResult(1001,"指定用户重置密码时，code不能为空或null");
        boolean flag=userService.resetUserPassword(userCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    /**
     * 后台系统登陆验证
     * @param request
     * @param loginName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/loginBack")
    public Object loginBack(HttpServletRequest request ,String loginName,String password){
        log.info("后台系统登陆验证");
        User user=userService.loginUser(loginName, password);
        if (user==null){
            return validationResult(1001,"登陆失败");
        }else {
            request.getSession().setAttribute("CURRENT_USER",user);
            return result("登录成功");
        }
    }

    /**
     * 登录验证
     * @return
     */
    @RequestMapping(value = "/loginStatus")
    public Object loginStatus(HttpServletRequest request){
        User user=DybUtils.getCurrentUser(request);
        if (user!=null){
            return result("已经登录");
        }else{
            return validationResult(1001,"尚未登录");
        }
    }

}
