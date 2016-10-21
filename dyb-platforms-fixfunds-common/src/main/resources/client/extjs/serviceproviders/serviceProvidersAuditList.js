/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.serviceproviders.serviceProvidersAuditList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '服务商审核列表',
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
                        emptyText:'服务商编号、绑定手机号',
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
                { header: '服务商编号',  dataIndex: 'accountCode',width:153 },
                { header: '账户名', dataIndex: 'accountName',width:120 },
                { header: '绑定手机号', dataIndex: 'accountPhone',width:120 },
                { header: '账户状态', dataIndex: 'accountStatus',width:120 },
                { header: '推荐人code', dataIndex: 'referrerCode',width:180 },
                { header: '注册时间', dataIndex: 'registrationTime',width:140 },
                { header: '服务商名称', dataIndex: 'serviceProviderName',width:140 },
                { header: '邮箱地址', dataIndex: 'email',width:140 },
                { header: '服务商地址', dataIndex: 'address',width:140 },
                { header: '身份证号码', dataIndex: 'idCard',width:140 },
                { text: '操作',dataIndex: 'accountCode', width:170,
                    renderer:function(val){
                        var url='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').approvedServiceProviders(\'' + (val)  + '\')">'+'通过'+'</a>&nbsp; &nbsp;';
                        url+='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').cancelServiceProviders(\'' + (val)  + '\')">'+'不通过'+'</a>&nbsp; &nbsp;';
                        return url;
                    }
                },
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
                {name: 'serviceProviderName', mapping: 'serviceProviders.serviceProviderName'},
                {name: 'email', mapping: 'serviceProviders.email'},
                {name: 'address', mapping: 'serviceProviders.address'},
                {name: 'idCard', mapping: 'serviceProviders.idCard'}
            ],
            proxy: {
                url: '/back/commons/serviceProviders/getServiceProvidersAuditList',
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
     * 审核通过
     * @param serviceProvidersCode 服务商code
     */
    approvedServiceProviders:function(serviceProvidersCode){
        var result = Ext.appContext.invokeService("back/commons/serviceProviders","/approvedServiceProviders", {serviceProvidersCode: serviceProvidersCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 审核不通过
     * @param serviceProvidersCode 服务商code
     */
    cancelServiceProviders:function(serviceProvidersCode){
        var result = Ext.appContext.invokeService("back/commons/serviceProviders","/cancelServiceProviders", {serviceProvidersCode: serviceProvidersCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})