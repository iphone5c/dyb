/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB.menuPanel', {
    extend: 'Ext.tree.Panel',
    rootVisible:false,  // 显示root节点
    requires: [],
    initComponent: function () {
        /*
         * 代码注意逻辑分离，不要揉到一堆
         * 一个函数内的代码行数尽量精简，同时逻辑功能尽量独立
         */
//        var store = Ext.create('Ext.data.TreeStore', {
//            root: {
//                expanded: true,
//                children: [
//                    { text: "系统管理", expanded: true, children: [
//                        { text: "用户管理",module:'YC_CORESYSTEM_ACCOUNT.accountList',url:'user/getUserList', leaf: true },
//                        { text: "其它", leaf: true}
//                    ] },
//                    { text: "其它", leaf: true }
//                ]
//            }
//        });
//        var treeStroe=this.createStore();
        var me=this;
        Ext.applyIf(me, {
            title: 'Simple Tree',
            width: 200,
            height: 150,
            store: me.createStore(),
            rootVisible: false,
            listeners: {
                itemclick: function (myTree, record) {
                    if (record.data.module != '' && record.data.url != '') {
                        /*
                         * 触发一个自定义事件
                         * 这个事件在myApp.js中
                         */
                        me.fireEvent('menuSelected', {
                            moduleId: record.data.module
                        });
                    }
                },
                scope: this
            }

        });
        this.callParent(arguments);
    },

    createStore: function () {
        var treeStore=Ext.appContext.invokeService('/commons','/getMenuList',{});
        if(treeStore.statusCode!=1000){
            Ext.Msg.alert('操作失败',treeStore.result);
            return;
        }
        var store = Ext.create('Ext.data.TreeStore', {
            root: {
                expanded: true,
                children: treeStore.result
            }
        });
        return store;
    }
});