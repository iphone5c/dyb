package com.dyb.platforms.fixfunds.services.utils.core.bean;

import java.util.Date;

/**
 * 基础模块
 * Created by Administrator on 2016/3/7.
 */
public class BaseBean {
    //创建人
    private String createPerson;
    //创建日期
    private Date createTime;
    //修改人
    private String modifyPerson;
    //修改日期
    private Date modifyTime;
    //版本号
    private String version;
    //逻辑删除标记
    private Boolean enable;

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
