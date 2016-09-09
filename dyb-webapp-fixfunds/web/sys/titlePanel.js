/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('YC_CORESYSTEM.titlePanel',{
    extend: 'Ext.panel.Panel',
    initComponent: function () {
        var me=this;
        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象
            html:'四川金融交易所——核心系统'
        });
        this.callParent(arguments);
    }
})