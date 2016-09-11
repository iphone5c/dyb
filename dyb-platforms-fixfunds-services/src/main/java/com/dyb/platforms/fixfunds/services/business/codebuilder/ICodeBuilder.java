package com.dyb.platforms.fixfunds.services.business.codebuilder;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
public interface ICodeBuilder {
    /**
     * 获取一个新的用户编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户编码
     */
    String getUserCode();

}
