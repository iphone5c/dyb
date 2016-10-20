/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.merchant.merchantAuditList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '商家审核列表',
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
                { header: '商家编号',  dataIndex: 'accountCode',width:153 },
                { header: '账户名', dataIndex: 'accountName',width:120 },
                { header: '绑定手机号', dataIndex: 'accountPhone',width:120 },
                { header: '账户状态', dataIndex: 'accountStatus',width:120 },
                { header: '推荐人code', dataIndex: 'referrerCode',width:180 },
                { header: '注册时间', dataIndex: 'registrationTime',width:140 },
                { header: '商家名称', dataIndex: 'merchantName',width:140 },
                { header: '激励模式', dataIndex: 'incentiveMode',width:140 },
                { header: '店铺名称', dataIndex: 'shopName',width:140 },
                { header: '商家地址', dataIndex: 'merchantAddress',width:140 },
                { header: '负责人姓名', dataIndex: 'principalName',width:140 },
                { header: '公司电话', dataIndex: 'countryPhone',width:140 },
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
                {name: 'accountCode', mapping: 'accountCode'},
                {name: 'accountName', mapping: 'accountName'},
                {name: 'accountPhone', mapping: 'accountPhone'},
                {name: 'accountStatus', mapping: 'accountStatus'},
                {name: 'referrerCode', mapping: 'referrerCode'},
                {name: 'registrationTime', mapping: 'registrationTime'},
                {name: 'merchantName', mapping: 'merchant.merchantName'},
                {name: 'incentiveMode', mapping: 'merchant.incentiveMode'},
                {name: 'shopName', mapping: 'merchant.shopName'},
                {name: 'merchantAddress', mapping: 'merchant.merchantAddress'},
                {name: 'principalName', mapping: 'merchant.principalName'},
                {name: 'countryPhone', mapping: 'merchant.countryPhone'}
            ],
            proxy: {
                url: '/back/commons/merchant/getMerchantAuditList',
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
     * 禁用商家
     * @param accountCode 商家code
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
     * @param accountCode 商家code
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
     * @param accountCode 商家code
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
     * @param accountCode 商家code
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