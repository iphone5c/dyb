package com.dyb.platforms.fixfunds.services.utils.core;

import com.dyb.platforms.fixfunds.services.business.systemlog.entity.UserLog;
import com.dyb.platforms.fixfunds.services.business.systemlog.service.IUserLogService;
import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.business.user.service.IUserService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseDto;
import com.dyb.platforms.fixfunds.services.utils.core.controller.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);

        if (
                requestPath.startsWith("/back/commons/user/loginStatus")||
                requestPath.startsWith("/back/commons/user/loginBack")
            ){
            return true;
        }

        User user=DybUtils.getCurrentUser(request);
        //判断是否登陆
        if (user==null){
            //未登录用户
            validationResultJSONP(request,response,9999,"账户未登录");
            return false;
        }
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    protected void validationResultJSONP(HttpServletRequest request,HttpServletResponse response,int statusCode,String info){
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            Dto result = new BaseDto();
            result.put("result", "");
            result.put("errorMessage", info);
            result.put("statusCode", statusCode);
            response.getWriter().write(DybUtils.getJsonSerialize(result)); //返回json数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
