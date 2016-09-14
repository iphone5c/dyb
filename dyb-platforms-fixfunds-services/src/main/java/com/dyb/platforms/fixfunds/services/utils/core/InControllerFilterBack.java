package com.dyb.platforms.fixfunds.services.utils.core;

import com.dyb.platforms.fixfunds.services.business.systemlog.entity.UserLog;
import com.dyb.platforms.fixfunds.services.business.systemlog.service.IUserLogService;
import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.business.user.service.IUserService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后端拦截器（登陆、权限、管理员日志）
 * Created by lenovo on 2016/9/14.
 */
public class InControllerFilterBack implements HandlerInterceptor {

    @Autowired
    private IUserLogService userLogService;
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);

        User currentUser=userService.getUserByCode("201609131603100001");
        request.getSession().setAttribute("CURRENT_USER",currentUser);

        User user=DybUtils.getCurrentUser(request);
        //判断是否登陆
        if (user==null){
            //未登录用户
        }else {
            //日志录入
            String logTypeName= SettingConfigureationFactory.getAdminLogNameByKey(requestPath);
            if (!DybUtils.isEmptyOrNull(logTypeName)){
                //日志录入操作
                UserLog userLog=new UserLog();
                userLog.setUserCode(user.getUserCode());
                userLog.setUserName(user.getUserName());
                userLog.setOperationType(logTypeName);
                userLog.setDescription(logTypeName);
                userLog.setUserLoginIp(DybUtils.getIpAddr(request));
                userLogService.createUserLog(userLog);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
