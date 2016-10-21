/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.conversion.conversionApplyList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '转换申请列表',
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
                        emptyText:'转换申请编号、申请人ID',
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
                { header: '转换申请编号',  dataIndex: 'conversionCode',width:153 },
                { header: '转换类型', dataIndex: 'conversionType',width:120 },
                { header: '申请转换的数量', dataIndex: 'applyConversionNum',width:120 },
                { header: '实际转换的数量', dataIndex: 'conversionNum',width:120 },
                { header: '代扣税', dataIndex: 'deductions',width:180 },
                { header: '申请人ID', dataIndex: 'conversionAccount',width:140 },
                { header: '申请人姓名', dataIndex: 'applyAccountName',width:140 },
                { header: '申请状态', dataIndex: 'applyStatus',width:140 },
                { header: '申请时间', dataIndex: 'applyTime',width:140 },
                { text: '操作',dataIndex: 'conversionCode', width:170,
                    renderer:function(val){
                        var url='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').approvedConversion(\'' + (val)  + '\')">'+'通过'+'</a>&nbsp; &nbsp;';
                        url+='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').cancelConversion(\'' + (val)  + '\')">'+'不通过'+'</a>&nbsp; &nbsp;';
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
                {name: 'conversionCode', mapping: 'conversion.conversionCode'},
                {name: 'conversionType', mapping: 'conversion.conversionType'},
                {name: 'applyConversionNum', mapping: 'conversion.applyConversionNum'},
                {name: 'conversionNum', mapping: 'conversion.conversionNum'},
                {name: 'deductions', mapping: 'conversion.deductions'},
                {name: 'conversionAccount', mapping: 'conversion.conversionAccount'},
                {name: 'applyAccountName', mapping: 'applyAccountName'},
                {name: 'applyTime', mapping: 'conversion.applyTime'},
                {name: 'applyStatus', mapping: 'conversion.applyStatus'}
            ],
            proxy: {
                url: '/back/commons/conversion/getConversionApply',
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
     * 禁用转换申请
     * @param accountCode 转换申请code
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
     * 审核通过
     * @param conversionCode 转换申请单code
     */
    approvedConversion:function(conversionCode){
        var result = Ext.appContext.invokeService("back/commons/conversion","/approvedConversion", {conversionCode: conversionCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 审核不通过
     * @param conversionCode 转换申请单code
     */
    cancelConversion:function(conversionCode){
        var result = Ext.appContext.invokeService("back/commons/conversion","/cancelConversion", {conversionCode: conversionCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})