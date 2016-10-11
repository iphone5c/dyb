package com.dyb.platforms.fixfunds.merchant.controller.client.model;

import com.dyb.platforms.fixfunds.services.business.turnover.entity.Turnover;

/**
 * Created by lenovo on 2016/10/11.
 */
public class TurnoverModel {
    private Integer incentiveMode;
    private Turnover turnover;

    public Integer getIncentiveMode() {
        return incentiveMode;
    }

    public void setIncentiveMode(Integer incentiveMode) {
        this.incentiveMode = incentiveMode;
    }

    public Turnover getTurnover() {
        return turnover;
    }

    public void setTurnover(Turnover turnover) {
        this.turnover = turnover;
    }
}
