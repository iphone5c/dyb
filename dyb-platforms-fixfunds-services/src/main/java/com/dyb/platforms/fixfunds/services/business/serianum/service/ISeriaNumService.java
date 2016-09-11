package com.dyb.platforms.fixfunds.services.business.serianum.service;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ISeriaNumService {
    /**
     * 根据指定的键获取一个新的序列号，序列从1开始
     *
     * @param keyName 序列号发生器的键
     * @return 序列号
     */
    int getNewSerialNumByInt(String keyName);

    /**
     * 根据指定的键获取一个字符串表示的新序列号，序列从1开始
     *
     * @param keyName 序列号发生器的键
     * @param len     返回符串序列号的长度，实际序列右对齐前面补0，如果指定的长度小于序列的实际位数，将抛出异常.
     * @return 序列号
     */
    String getNewSerialNumByString(String keyName, int len);

    /**
     * 清除指定的序列发生器，清除后序列将从1开始重新计算。
     *
     * @param keyName 序列号发生器的键
     */
    void clearSerial(String keyName);

}
