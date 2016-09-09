package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import com.alibaba.fastjson.JSON;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/3.
 */
public class SettingConfigureationFactory {
    /**
     * 获取模块列表
     * @return 模块列表
     */
    public static List<ModelConfig> getModelConfigList() {
        try {
            String info="";
            List<SettingConfigureation> settingConfigureationList= ConfigureationFactory.getSettingConfigureation("classpath*:**/settings-config.xml");
            for (SettingConfigureation settingConfigureation:settingConfigureationList){
                if (settingConfigureation.getModuleName().equals("SYS_SETTING")){
                    for (SettingConfigureationItem settingConfigureationItem:settingConfigureation.getSettings()){
                        if (settingConfigureationItem.getKey().equals("MODEL_CONFIG")){
                            info=settingConfigureationItem.getNodeValue();
                        }
                    }
                }
            }
            Map temp= (Map) DybUtils.getJsonDeserialize(info, Map.class);
            List result= DybUtils.getJsonDeserializeListT(JSON.toJSONString(temp.get("list")),Class.forName((String) temp.get("type")));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取菜单列表
     * @return 菜单列表
     */
    public static List<Menu> getMenuList()  {
        try {
            String info="";
            List<SettingConfigureation> settingConfigureationList= ConfigureationFactory.getSettingConfigureation("classpath*:**/settings-config.xml");
            for (SettingConfigureation settingConfigureation:settingConfigureationList){
                if (settingConfigureation.getModuleName().equals("SYS_SETTING")){
                    for (SettingConfigureationItem settingConfigureationItem:settingConfigureation.getSettings()){
                        if (settingConfigureationItem.getKey().equals("CLIENT_MENU")){
                            info=settingConfigureationItem.getNodeValue();
                        }
                    }
                }
            }
            Map temp= (Map) DybUtils.getJsonDeserialize(info,Map.class);
            List result= DybUtils.getJsonDeserializeListT(JSON.toJSONString(temp.get("list")),Class.forName((String) temp.get("type")));
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取业务定义列表
     * @return 业务定义列表
     */
    public static List<BusinessDefined> getBusinessDefinedList()  {
        try {
            List result=new ArrayList();
            String info="";
            List<SettingConfigureation> settingConfigureationList= ConfigureationFactory.getSettingConfigureation("classpath*:**/settings-config.xml");
            List<ModelConfig> modelConfigList=SettingConfigureationFactory.getModelConfigList();
            String modelName="SYS_SETTING,";
            for (ModelConfig modelConfig:modelConfigList)
                modelName+=modelConfig.getModelAliasName()+",";
            for (SettingConfigureation settingConfigureation:settingConfigureationList){
                if (modelName.indexOf(settingConfigureation.getModuleName())>=0){
                    for (SettingConfigureationItem settingConfigureationItem:settingConfigureation.getSettings()){
                        if (settingConfigureationItem.getKey().equals("BUSINESS_DEFINEDS")){
                            Map temp= (Map) DybUtils.getJsonDeserialize(settingConfigureationItem.getNodeValue(),Map.class);
                            result.addAll(DybUtils.getJsonDeserializeListT(JSON.toJSONString(temp.get("list")),Class.forName((String) temp.get("type"))));
                            break;
                        }
                    }
                }
            }
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定的业务定义
     *
     * @param businessType 业务类型
     * @return 业务定义对象
     */
    public static BusinessDefined getBusinessDefinedByBusinessType(int businessType) {
        List<BusinessDefined> businessDefinedList=SettingConfigureationFactory.getBusinessDefinedList();
        for (BusinessDefined defined : businessDefinedList) {
            if (defined.getBusinessType() == businessType)
                return defined;
        }
        return null;
    }

}
