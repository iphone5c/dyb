/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.commodity.commodityList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '商品列表',
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
                        emptyText:'商家ID、商品名称、商品编号',
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
                    },
                ]
            },
            columns: [
                { header: 'code',  dataIndex: 'commodityCode',width:153 },
                { header: '商品名称', dataIndex: 'name',width:120 },
                { header: '商品编号', dataIndex: 'commodityNum',width:120 },
                { header: '型号/规格', dataIndex: 'specifications',width:120 },
                { header: '单价', dataIndex: 'price',width:180 },
                { header: '创建时间', dataIndex: 'createTime',width:140 },
                { header: '商家ID', dataIndex: 'accountCode',width:140 },
                { header: '商家', dataIndex: 'merchantName',width:140 },
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
                {name: 'commodityCode', mapping: 'commodity.commodityCode'},
                {name: 'name', mapping: 'commodity.name'},
                {name: 'commodityNum', mapping: 'commodity.commodityNum'},
                {name: 'specifications', mapping: 'commodity.specifications'},
                {name: 'price', mapping: 'commodity.price'},
                {name: 'createTime', mapping: 'commodity.createTime'},
                {name: 'accountCode', mapping: 'commodity.accountCode'},
                {name: 'merchantName', mapping: 'account.merchant.merchantName'}
            ],
            proxy: {
                url: '/back/commons/commodity/getCommodityPageList',
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
     * 禁用商品
     * @param accountCode 商品code
     */
    disableMerchant:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/commodity","/disableMerchant", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param accountCode 商品code
     */
    removeDisableMerchant:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/commodity","/removeDisableMerchant", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置登录密码
     * @param accountCode 商品code
     */
    resetMerchantPassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/commodity","/resetMerchantPassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置二级密码
     * @param accountCode 商品code
     */
    resetMerchantTradePassword:function(accountCode){
        var result = Ext.appContext.invokeService("/back/commons/commodity","/resetMerchantTradePassword", {accountCode: accountCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})