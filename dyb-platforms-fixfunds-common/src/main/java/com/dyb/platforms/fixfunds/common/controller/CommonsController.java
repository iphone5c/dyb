package com.dyb.platforms.fixfunds.common.controller;

import com.dyb.platforms.fixfunds.services.business.user.service.IUserService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.ModelConfig;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/commons")
public class CommonsController extends BaseController {

    public Logger log = Logger.getLogger(CommonsController.class);//日志

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getMenuList")
    public Object getMenuList() {
        log.info("获取所有菜单列表");
        return result(SettingConfigureationFactory.getMenuList());
    }

    @RequestMapping(value = "/getUserList")
    public Object getUserList(int pageIndex,int pageSize){
        log.info("获取用户列表");
        QueryParams queryParams=new QueryParams();
        return result(userService.getUserPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/refreshExtjs")
    public Object refreshExtjs(HttpServletRequest request){
        List<ModelConfig> modelConfigList= SettingConfigureationFactory.getModelConfigList();
        String rootPath=request.getServletContext().getRealPath("/");
        for (ModelConfig modelConfig:modelConfigList){
            String resourcesPath=rootPath+"WEB-INF/classes/"+modelConfig.getModelName();
            String targetPath=rootPath+ DybUtils.MODEL_TARGET_PATH+"/"+modelConfig.getModelName();
            log.info("模块加载："+modelConfig.getModelName()+"，模块实际地址："+targetPath);
            try {
                DybUtils.copyDirectiory(resourcesPath, targetPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result("已经成功刷新Extjs缓存");
    }

}
