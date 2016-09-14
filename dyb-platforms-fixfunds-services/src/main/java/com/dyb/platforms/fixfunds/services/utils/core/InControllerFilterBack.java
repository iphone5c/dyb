package com.dyb.platforms.fixfunds.services.utils.core;

import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后端拦截器（登陆、权限、管理员日志）
 * Created by lenovo on 2016/9/14.
 */
public class InControllerFilterBack implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);
        //日志录入
        String logTypeName= SettingConfigureationFactory.getAdminLogNameByKey(requestPath);
        if (!DybUtils.isEmptyOrNull(logTypeName)){
            //日志录入操作
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
