/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.messengerbean.messengerBeanList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '信使豆列表',
    frame: false,
    border: false,
    header: false,
    columnLines:true,

    // ====初始化定义==========================================================
    initComponent: function () {
        var me=this;
        var store = me.createStore();

        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象
            store: store,
            tbar:{
                xtype: 'toolbar', scope: me,
                items:[
                    {
                        xtype: 'button', text: '禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.disableMerchant(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '解除禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.removeDisableMerchant(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '重置登录密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.resetMerchantPassword(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '重置二级密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.resetMerchantTradePassword(list[0].data.accountCode);
                        }
                    }
                ]
            },
            columns: [
                { header: '信使豆编号',  dataIndex: 'messengerBeanCode',width:153 },
                { header: '账户Code', dataIndex: 'accountCode',width:120 },
                { header: '绑定手机号', dataIndex: 'accountPhone',width:120 },
                { header: '会员昵称', dataIndex: 'accountName',width:120 },
                { header: '真实姓名', dataIndex: 'realName',width:180 },
                { header: '信使豆类型', dataIndex: 'messengerBeanType',width:140 },
                { header: '信使豆数量', dataIndex: 'messengerBean',width:140 },
                { header: '冻结数量', dataIndex: 'freeze',width:140 },
                { flex: 1 }
            ],
            dockedItems: [
                {
                    xtype: 'pagingtoolbar', dock: 'bottom', displayInfo: true,
                    store: store,
                    listeners: {
                        beforechange: function (source, pageNum) {
                            var me = this;
                            this.reload({pageIndex: pageNum - 1});
                            return false;
                        },
                        scope: me
                    }
                }
            ],
            height: 200,
            width: 400
        });
        this.callParent(arguments);
    },

    // ====基类方法定义==========================================================
    reload: function (params) {
        var me = this;
        Ext.exUtils.combine(me.store.proxy.extraParams, params);
        me.store.reload();
        me.store.currentPage = me.store.proxy.extraParams.pageIndex + 1;
    },

    createStore:function(){
        var store=Ext.create('Ext.data.Store', {
            autoLoad: true,
            pageSize:20,
            fields: [
                {name: 'accountName', mapping: 'accountName'},
                {name: 'accountPhone', mapping: 'accountPhone'},
                {name: 'realName', mapping: 'realName'},
                {name: 'messengerBeanCode', mapping: 'messengerBean.messengerBeanCode'},
                {name: 'accountCode', mapping: 'messengerBean.accountCode'},
                {name: 'messengerBean', mapping: 'messengerBean.messengerBean'},
                {name: 'messengerBeanType', mapping: 'messengerBean.messengerBeanType'},
                {name: 'freeze', mapping: 'messengerBean.freeze'}
            ],
            proxy: {
                url: '/back/commons/messengerBean/getMessengerBeanPageList',
                type: 'ajax',
                extraParams: {pageIndex:0,pageSize:20},
                reader: {
                    type: 'json',
                    rootProperty: 'result.list',
                    totalProperty: 'result.totalSize',
                    messageProperty: 'errorMessage',
                    successProperty: 'success'
                }
            }
        });
        return store;
    },

    /**
     * 禁用信使豆
     * @param accountCode 信使豆code
     */
    disableMerchant:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/merchant","/disableMerchant", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param accountCode 信使豆code
     */
    removeDisableMerchant:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/merchant","/removeDisableMerchant", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置登录密码
     * @param accountCode 信使豆code
     */
    resetMerchantPassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/merchant","/resetMerchantPassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置二级密码
     * @param accountCode 信使豆code
     */
    resetMerchantTradePassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/merchant","/resetMerchantTradePassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})