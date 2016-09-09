package com.dyb.platforms.fixfunds.services.utils;

/**
 * Created by lenovo on 2016/9/8.
 */
public class DybConvertResult {
    private boolean success;
    private Object result;

    private DybConvertResult() {
    }

    /**
     * 创建一个转换成功的实例
     *
     * @param result 转换结果
     * @return 返回success=true的结果
     */
    public static DybConvertResult NewSuccess(Object result) {
        DybConvertResult obj = new DybConvertResult();
        obj.success = true;
        obj.result = result;
        return obj;
    }

    /**
     * 创建一个转换失败的实例
     *
     * @return 返回success=false的结果
     */
    public static DybConvertResult NewFail() {
        DybConvertResult obj = new DybConvertResult();
        obj.success = false;
        return obj;
    }

    /**
     * 如果success=true的情况下，获取转换结果
     *
     * @return 转换结果
     */
    public Object getResult() {
        return result;
    }

    /**
     * 是否转换成功
     *
     * @return 成功返回true, 否则返回false.
     */
    public boolean isSuccess() {
        return success;
    }
}
