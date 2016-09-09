/**
 * Created by Administrator on 2016/3/1.
 */
Ext.Loader.setConfig({
    enabled: true,
    paths: {
        /*
         * 设置YCUI以及YCTrade命名空间对应的路径
         */
        YC_CORESYSTEM: "sys/",
        YC_CORESYSTEM_ACCOUNT:"_yc_resources/yc-coresystem-account/client/extjs/",
        YC_CORESYSTEM_COMMONS:"_yc_resources/yc-coresystem-commons/client/extjs/",
        YC_CORESYSTEM_MEMBER:"_yc_resources/yc-coresystem-member/client/extjs/",
        YC_CORESYSTEM_PRODUCT:"_yc_resources/yc-coresystem-product/client/extjs/"
    }
});