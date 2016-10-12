package com.dyb.platforms.fixfunds.services.utils.core;

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
public class InControllerFilterClient implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);
        if (
                requestPath.startsWith("/client/commons")||
                requestPath.startsWith("/client/member/registerMemberAccount")||
                requestPath.startsWith("/client/merchant/registerMerchantAccount")||
                requestPath.startsWith("/client/serviceproviders/registerServiceProvidersAccount")
            ){
            return true;
        }
        String token=request.getParameter("token");
        if (DybUtils.isEmptyOrNull(token)){
            validationResultJSONP(request,response,1001,"当前会话是非法请求");
            return false;
        }
        if (DybUtils.getCurrentAccountClient(token)==null){
            validationResultJSONP(request,response,9999,"当前会话无效");
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
            result.put("result", null);
            result.put("errorMessage", info);
            result.put("statusCode", statusCode);
            response.getWriter().write(DybUtils.getJsonSerialize(result)); //返回json数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
