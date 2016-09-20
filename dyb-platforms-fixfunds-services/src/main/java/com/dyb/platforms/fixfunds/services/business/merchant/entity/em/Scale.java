package com.dyb.platforms.fixfunds.services.business.merchant.entity.em;

import com.dyb.platforms.fixfunds.services.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业规模
 * Created by lenovo on 2016/9/20.
 */
public enum Scale {
    /**
     * 100人以下
     */
    A,
    /**
     * 100-500人
     */
    B,
    /**
     * 500-1000人
     */
    C,
    /**
     * 1000-10000人
     */
    D,
    /**
     * 10000人以上
     */
    E;

    public static String convertNameByValue(Scale scale){
        String info="";
        if (scale==Scale.A){
            info="100人以下";
        }else if (scale==Scale.B){
            info="100-500人";
        }else if (scale==Scale.C){
            info="500-1000人";
        }else if (scale==Scale.E){
            info="1000-10000人";
        }else {
            info="10000人以上";
        }
        return info;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Scale scale : Scale.values()){
            result.add(NameValue.create(scale.toString(),Scale.convertNameByValue(scale)));
        }
        return result;
    }
}
