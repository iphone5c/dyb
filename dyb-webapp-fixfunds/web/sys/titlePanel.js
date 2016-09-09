/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB.titlePanel',{
    extend: 'Ext.panel.Panel',
    initComponent: function () {
        var me=this;
        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象
            html:'一点公益管理系统'
        });
        this.callParent(arguments);
    }
})