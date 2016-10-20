/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.order.messengerRegistrationApplyList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '信使消费登记申请列表',
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
                        xtype: 'textfield',
                        name: 'keyWord',
                        itemId: 'keyWord',
                        labelWidth: 80,
                        width:300,
                        fieldLabel: '查询关键字',
                        emptyText:'订单编号、信使ID、商家ID',
                        listeners: {
                            specialkey: function(field, e){
                                if (e.getKey() == e.ENTER) {
                                    var val = me.down('#keyWord').getValue();
                                    me.reload({  keyWord:val,pageIndex: 0} )
                                }
                            }
                        }
                    },
                    {
                        xtype: 'button', text: '查询', glyph: 0xf002, scope: me,
                        handler: function () {
                            var val = me.down('#keyWord').getValue();
                            me.reload({  keyWord:val,pageIndex: 0} )
                        }
                    }
                ]
            },
            columns: [
                { header: '订单编号',  dataIndex: 'orderCode',width:153 },
                { header: '信使ID', dataIndex: 'memberCode',width:120 },
                { header: '信使名字', dataIndex: 'memberName',width:120 },
                { header: '商家ID', dataIndex: 'merchantCode',width:120 },
                { header: '商家名字', dataIndex: 'merchantName',width:180 },
                { header: '交易时间', dataIndex: 'tradeTime',width:140 },
                { header: '交易总价', dataIndex: 'price',width:140 },
                { header: '订单状态', dataIndex: 'status',width:140 },
                { header: '激励模式', dataIndex: 'incentiveMode',width:140 },
                { header: '创建时间', dataIndex: 'createTime',width:140 },
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
                {name: 'orderCode', mapping: 'order.orderCode'},
                {name: 'memberCode', mapping: 'order.memberCode'},
                {name: 'memberName', mapping: 'member.member.realName'},
                {name: 'merchantCode', mapping: 'order.merchantCode'},
                {name: 'merchantName', mapping: 'merchant.merchant.merchantName'},
                {name: 'tradeTime', mapping: 'order.tradeTime'},
                {name: 'price', mapping: 'order.price'},
                {name: 'status', mapping: 'order.status'},
                {name: 'incentiveMode', mapping: 'order.incentiveMode'},
                {name: 'createTime', mapping: 'order.createTime'}
            ],
            proxy: {
                url: '/back/commons/order/getMessengerRegistrationApply',
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
     * 禁用信使消费登记申请
     * @param accountCode 信使消费登记申请code
     */
    disableMember:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/member","/disableMember", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param accountCode 信使消费登记申请code
     */
    removeDisableMember:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/member","/removeDisableMember", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置登录密码
     * @param accountCode 信使消费登记申请code
     */
    resetMemberPassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/member","/resetMemberPassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置二级密码
     * @param accountCode 信使消费登记申请code
     */
    resetMemberTradePassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/member","/resetMemberTradePassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})