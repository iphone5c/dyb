/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.salesman.salesmanList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '业务员列表',
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
                                me.disableSalesman(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '解除禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.removeDisableSalesman(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '重置登录密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.resetSalesmanPassword(list[0].data.accountCode);
                        }
                    },
                    {
                        xtype: 'button', text: '重置二级密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.resetSalesmanTradePassword(list[0].data.accountCode);
                        }
                    }
                ]
            },
            columns: [
                { header: '业务员编号',  dataIndex: 'accountCode',width:153 },
                { header: '账户名', dataIndex: 'accountName',width:120 },
                { header: '绑定手机号', dataIndex: 'accountPhone',width:120 },
                { header: '账户状态', dataIndex: 'accountStatus',width:120 },
                { header: '推荐人code', dataIndex: 'referrerCode',width:180 },
                { header: '注册时间', dataIndex: 'registrationTime',width:140 },
                { header: '真实姓名', dataIndex: 'realName',width:140 },
                { header: '证件类型', dataIndex: 'certificate',width:140 },
                { header: '证件号码', dataIndex: 'certificateNumber',width:140 },
                { header: '个人邮箱', dataIndex: 'salesmanEmail',width:140 },
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
                {name: 'realName', mapping: 'salesman.realName'},
                {name: 'certificate', mapping: 'salesman.certificate'},
                {name: 'certificateNumber', mapping: 'salesman.certificateNumber'},
                {name: 'salesmanEmail', mapping: 'salesman.salesmanEmail'}
            ],
            proxy: {
                url: '/back/commons/salesman/getSalesmanList',
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
     * 禁用业务员
     * @param accountCode 业务员code
     */
    disableSalesman:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/salesman","/disableSalesman", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param accountCode 业务员code
     */
    removeDisableSalesman:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/salesman","/removeDisableSalesman", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置登录密码
     * @param accountCode 业务员code
     */
    resetSalesmanPassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/salesman","/resetSalesmanPassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置二级密码
     * @param accountCode 业务员code
     */
    resetSalesmanTradePassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/salesman","/resetSalesmanTradePassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})