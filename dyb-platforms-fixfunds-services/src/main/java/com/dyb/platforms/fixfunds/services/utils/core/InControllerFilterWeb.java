package com.dyb.platforms.fixfunds.services.utils.core;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseDto;
import com.dyb.platforms.fixfunds.services.utils.core.controller.Dto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/10/10.
 */
public class InControllerFilterWeb implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);
        if (
                requestPath.startsWith("/web/commons")||
                requestPath.startsWith("/web/merchant/registerMerchantAccount")||
                requestPath.startsWith("/web/member/registerMemberAccount")||
                requestPath.startsWith("/web/serviceproviders/registerServiceProvidersAccount")
            ){
            return true;
        }
        Account account=DybUtils.getCurrentAccount(request);
        if (account==null){
            validationResultJSONP(request,response,9999,"账户未登录");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

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
