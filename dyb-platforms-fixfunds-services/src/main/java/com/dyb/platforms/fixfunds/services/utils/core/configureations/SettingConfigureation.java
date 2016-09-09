package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class SettingConfigureation {
    private String moduleName;
    private List<SettingConfigureationItem> settings;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<SettingConfigureationItem> getSettings() {
        return settings;
    }

    public void setSettings(List<SettingConfigureationItem> settings) {
        this.settings = settings;
    }
}
