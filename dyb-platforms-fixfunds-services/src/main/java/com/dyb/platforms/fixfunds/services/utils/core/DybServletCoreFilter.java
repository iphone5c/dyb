package com.dyb.platforms.fixfunds.services.utils.core;

import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.ModelConfig;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class DybServletCoreFilter implements Filter {

    public Logger log = Logger.getLogger(DybServletCoreFilter.class);//日志

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("系统初始化开始，请稍后。");
        try {
            handleModularExtjs(filterConfig);
        } catch (Exception e) {
            log.error("系统初始化错误信息："+e.getMessage());
        }
        log.info("系统初始化完成。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
//        String requestPath = request.getRequestURI().toString();
//        System.out.println("=======被调用=========调用路径："+requestPath);
//        String params=request.getParameter("params");
//        Map p1 = new HashMap();
//        Map p;
//        if (!YcUtils.isEmptyOrNull(params)){
//            System.out.println(params);
//            p=(Map)JSON.parseObject(params, HashMap.class, new Feature[] { Feature.AllowUnQuotedFieldNames, Feature.AllowSingleQuotes, Feature.DisableCircularReferenceDetect });
//            for (Object key:p.keySet()){
//                Object obj = p.get(key);
//                System.out.println(key+"--->"+obj);
//                if (YcUtils.isBasisClass(obj.getClass()))
//                    p1.put(key, YcConvert.basisToString(obj));
//                else {
//                    p1.put(key, YcUtils.getJsonSerialize(obj));
//                }
//            }
//        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("系统正在停止运行，请稍后。");


        log.info("系统已停止运行。");
    }

    /**
     * 处理模块的extjs文件
     */
    public void handleModularExtjs(FilterConfig filterConfig)throws Exception{
        List<ModelConfig> modelConfigList= SettingConfigureationFactory.getModelConfigList();
        String rootPath=filterConfig.getServletContext().getRealPath("/");
        for (ModelConfig modelConfig:modelConfigList){
            String resourcesPath=rootPath+"WEB-INF/classes/"+modelConfig.getModelName();
            String targetPath=rootPath+ DybUtils.MODEL_TARGET_PATH+"/"+modelConfig.getModelName();
            log.info("模块加载："+modelConfig.getModelName()+"，模块实际地址："+targetPath);
            DybUtils.copyDirectiory(resourcesPath, targetPath);
        }
    }
}
