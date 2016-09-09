package com.dyb.platforms.fixfunds.services.utils.core.configureations;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ModelConfig {
    //模块名称（唯一）
    private String modelName;

    //模块别名
    private String modelAliasName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelAliasName() {
        return modelAliasName;
    }

    public void setModelAliasName(String modelAliasName) {
        this.modelAliasName = modelAliasName;
    }
}
