package com.dyb.platforms.fixfunds.services.business.account.entity;

/**
 * Created by lenovo on 2016/10/15.
 */
public class Qrcode {
    //推荐地址
    private String url;
    //二维码地址
    private String imagePath;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
