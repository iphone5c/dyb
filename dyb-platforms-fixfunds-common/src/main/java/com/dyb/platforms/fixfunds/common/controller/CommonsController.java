package com.dyb.platforms.fixfunds.common.controller;

import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/commons")
public class CommonsController extends BaseController {

    public Logger log = Logger.getLogger(CommonsController.class);//日志

    @RequestMapping(value = "/getMenuList")
    public Object getMenuList() {
        log.info("获取所有菜单列表");
        return result(SettingConfigureationFactory.getMenuList());
    }
}
