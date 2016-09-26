package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
     * 获取菜单列表
     * @return 菜单列表
     */
    public static List<Menu> getMenuListByKey(String key)  {
        try {
            String info="";
            List<SettingConfigureation> settingConfigureationList= ConfigureationFactory.getSettingConfigureation("classpath*:**/settings-config.xml");
            for (SettingConfigureation settingConfigureation:settingConfigureationList){
                if (settingConfigureation.getModuleName().equals("SYS_SETTING")){
                    for (SettingConfigureationItem settingConfigureationItem:settingConfigureation.getSettings()){
                        if (settingConfigureationItem.getKey().equals(key)){
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

    /**
     * 获取管理员日志类型定义列表
     * @return
     */
    public static Map<String,String> getAdminLogMap(){
        Map<String,String> logDefined=new HashMap<>();
        try {
            String info="";
            List<SettingConfigureation> settingConfigureationList= ConfigureationFactory.getSettingConfigureation("classpath*:**/settings-config.xml");
            for (SettingConfigureation settingConfigureation:settingConfigureationList){
                if (settingConfigureation.getModuleName().equals("SYS_SETTING")){
                    for (SettingConfigureationItem settingConfigureationItem:settingConfigureation.getSettings()){
                        if (settingConfigureationItem.getKey().equals("ADMIN_LOG")){
                            info=settingConfigureationItem.getNodeValue();
                        }
                    }
                }
            }
            Map temp= (Map) DybUtils.getJsonDeserialize(info,Map.class);

            JSONArray list= (JSONArray) temp.get("list");
            for (int i=0;i<list.size();i++){
                JSONObject jsonObject= (JSONObject) list.get(i);
                for (Map.Entry<String, Object> entry :jsonObject.entrySet()){
                    logDefined.put(entry.getKey(),entry.getValue().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logDefined;
    }

    /**
     * 根据key获取日志类型定义名字
     * @param key
     * @return
     */
    public static String getAdminLogNameByKey(String key){
        return SettingConfigureationFactory.getAdminLogMap().get(key);
    }

}
