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

    /**
     * 获取一个新的系统参数配置编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的系统参数配置编码
     */
    String getSystemParamsCode();

    /**
     * 获取一个新的权限编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的权限编码
     */
    String getPermissionsCode();

    /**
     * 获取一个新的角色编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的角色编码
     */
    String getRoleCode();

    /**
     * 获取一个新的用户日志编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户日志编码
     */
    String getUserLogCode();

}
