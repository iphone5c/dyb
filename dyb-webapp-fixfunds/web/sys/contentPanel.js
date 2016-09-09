/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB.contentPanel',{
    extend: 'Ext.tab.Panel',
    initComponent: function () {
        var me=this;
        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象

        });
        this.callParent(arguments);
    },
    openModule: function(moduleId,config) {
        if(moduleId == ''){
            return;
        }
        /**
         * 第一次点击的时候是的所以正常往后面走因为之前还没有itemId
         * 第二次点击的时候 则变成已经定义的了  所以不会再次弹出来一个新的框框
         */

        var content = this.getComponent(moduleId);
        if(Ext.isDefined(content)) {
            this.setActiveTab(content);
            return;
        }
        var newContent = Ext.create(moduleId, config);
        this.add(newContent);
        this.setActiveTab(newContent);
    }
})