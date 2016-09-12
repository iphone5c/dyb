package com.dyb.platforms.fixfunds.common.controller.systemparams;

import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/systemparams")
public class SystemParamsController extends BaseController {

    public Logger log = Logger.getLogger(SystemParamsController.class);//日志

    @Autowired
    private ISystemParamsService systemParamsService;

    @RequestMapping(value = "/getSystemParamsList")
    public Object getSystemParamsList(int pageIndex,int pageSize){
        log.info("获取系统参数配置列表");
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("systemParamsCode",true);
        return result(systemParamsService.getSystemParamsPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getSystemParamsByCode")
    public Object getSystemParamsByCode(String systemParamsCode){
        log.info("获取主键为"+systemParamsCode+"的系统参数配置信息");
        SystemParams systemParams=systemParamsService.getSystemParamsByCode(systemParamsCode);
        if (systemParams==null)
            return validationResult(1001,"找不到此"+systemParamsCode+"的系统参数配置信息");
        return result(systemParams);
    }

    @RequestMapping(value = "/updateSystemParams")
    public Object updateSystemParams(SystemParams systemParams){
        log.info("更新系统配置参数信息，更新code："+systemParams.getSystemParamsCode());
        if (systemParams==null)
            return validationResult(1001,"更新系统配置参数信息，systemParams对象不能为空或null");
        if (DybUtils.isEmptyOrNull(systemParams.getSystemParamsCode()))
            return validationResult(1001,"更新系统配置参数信息，code对象不能为空或null");
        SystemParams updateSystemParams=systemParamsService.getSystemParamsByCode(systemParams.getSystemParamsCode());
        if (updateSystemParams==null)
            return validationResult(1001,"更新系统配置参数信息，找不到此系统参数配置信息，code："+systemParams.getSystemParamsCode());
        updateSystemParams.setSystemParamsValue(systemParams.getSystemParamsValue());
        updateSystemParams.setDescription(systemParams.getDescription());
        SystemParams temp=systemParamsService.updateSystemParams(updateSystemParams);
        if (temp==null)
            return validationResult(1001,"更新失败");
        else
            return result(temp);
    }

    @RequestMapping(value = "/createSystemParams")
    public Object createSystemParams(SystemParams systemParams){
        log.info("新增系统配置参数信息");
        if (systemParams==null)
            return validationResult(1001,"新增系统配置参数信息，systemParams对象不能为空或null");
        SystemParams temp=systemParamsService.createSystemParams(systemParams);
        if (temp==null)
            return validationResult(1001,"新增失败");
        else
            return result(temp);
    }

}
