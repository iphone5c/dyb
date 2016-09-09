package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class SettingConfigureationItem {
    private String key;
    private String attrValue;
    private String nodeValue;
    private List<SettingConfigureationItem> childs;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public List<SettingConfigureationItem> getChilds() {
        return childs;
    }

    public void setChilds(List<SettingConfigureationItem> childs) {
        this.childs = childs;
    }
}
