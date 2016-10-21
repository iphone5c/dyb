/**
 * Created by Administrator on 2016/3/1.
 */
Ext.Loader.setConfig({
    enabled: true,
    paths: {
        /*
         * 设置YCUI以及YCTrade命名空间对应的路径
         */
        DYB: "sys/",
        DYB_COMMON:"_dyb_resources/dyb-platforms-fixfunds-common/client/extjs/",
        DYB_MEMBER:"_dyb_resources/dyb-platforms-fixfunds-member/client/extjs/",
        DYB_MERCHANT:"_dyb_resources/dyb-platforms-fixfunds-merchant/client/extjs/",
        DYB_SERVICEPROVIDERS:"_dyb_resources/dyb-platforms-fixfunds-serviceproviders/client/extjs/"
    }
});