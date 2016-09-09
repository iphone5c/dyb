/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB.application',{
    extend:'Ext.container.Viewport',
    layout: 'border',
    initComponent:function(){
        //顶部
        var titlePanel=Ext.create('DYB.titlePanel',{
            title : "logo",
            region : "north",
            height : 100,
            style:'text-align:center',
            header : false,
            collapsible : true
        });
        //菜单栏(左边)
        var menuPanel=Ext.create('DYB.menuPanel',{
            title: '功能模块',
            region: 'west',
            width: 250,
            split: true,
            collapsible: true,
            border:false,
            autoScroll:false
        });
        //工作区(右边)
        var contentPanel=Ext.create('DYB.contentPanel',{
            region: 'center',
            '$panel-header-line-height':35,
            border:false
        });
        menuPanel.on('menuSelected',function(param){
            contentPanel.openModule(param.moduleId,{
                closable : true,
                /*
                 * 这里就是上面说的，用itemId来替代id
                 */
                itemId:param.moduleId,
                contentPanel:contentPanel
            })
        });
        var me=this;

        Ext.applyIf(me,{
            layout : "border",
            items:[
                titlePanel,
                menuPanel,
                contentPanel
            ]
        });
        me.callParent(arguments);
    }
});