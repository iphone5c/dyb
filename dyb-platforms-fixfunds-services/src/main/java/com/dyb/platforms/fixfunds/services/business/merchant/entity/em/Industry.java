package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 行业
 * Created by lenovo on 2016/9/20.
 */
public enum Industry {
    /**
     * 计算机/互联网
     */
    JSJ_HLW,
    /**
     * 通信/电子
     */
    TX_DZ,
    /**
     * 金融
     */
    JR,
    /**
     * 贸易/制造
     */
    MY_ZZ,
    /**
     * 医疗
     */
    YL,
    /**
     * 广告/媒体
     */
    GG_MT,
    /**
     * 房地产/建筑
     */
    FDC_JZ,
    /**
     * 游戏
     */
    YX,
    /**
     * 旅游
     */
    LY,
    /**
     * 娱乐
     */
    YLE,
    /**
     * 餐饮
     */
    CY,
    /**
     * 休闲/服务业
     */
    XX_FWY,
    /**
     * 物流/交通/航空
     */
    WL_JT_HK,
    /**
     * 能源/原材料
     */
    NY_YCL,
    /**
     * 政府/非盈利机构
     */
    ZF_FYLJG,
    /**
     * 其它
     */
    QT;

    public static String convertNameByValue(Industry scale){
        String info="";
        if (scale== Industry.JSJ_HLW){
            info="计算机/互联网";
        }else if (scale== Industry.TX_DZ){
            info="通信/电子";
        }else if (scale== Industry.JR){
            info="金融";
        }else if (scale== Industry.MY_ZZ){
            info="贸易/制造";
        }else if (scale== Industry.YL){
            info="医疗";
        }else if (scale== Industry.GG_MT){
            info="广告/媒体";
        }else if (scale== Industry.FDC_JZ){
            info="房地产/建筑";
        }else if (scale== Industry.YX){
            info="游戏";
        }else if (scale== Industry.LY){
            info="旅游";
        }else if (scale== Industry.YLE){
            info="娱乐";
        }else if (scale== Industry.CY){
            info="餐饮";
        }else if (scale== Industry.XX_FWY){
            info="休闲/服务业";
        }else if (scale== Industry.WL_JT_HK){
            info="物流/交通/航空";
        }else if (scale== Industry.NY_YCL){
            info="能源/原材料";
        }else if (scale== Industry.ZF_FYLJG){
            info="政府/非盈利机构";
        }else if (scale== Industry.QT){
            info="其它";
        }
        return info;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Industry industry : Industry.values()){
            result.add(NameValue.create(industry.toString(), Industry.convertNameByValue(industry)));
        }
        return result;
    }
}
