package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class Menu {
    //标题
    private String text;
    //模块全名
    private String module;
    //url
    private String url;
    //是否展开
    private Boolean expanded;
    //是否叶子节点
    private Boolean leaf;
    //子菜单
    private List<Menu> children;

    @Override
    public String toString() {
        return "Menu{" +
                "text='" + text + '\'' +
                ", module='" + module + '\'' +
                ", url='" + url + '\'' +
                ", expanded=" + expanded +
                ", leaf=" + leaf +
                ", children=" + children +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
