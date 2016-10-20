/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.withdrawal.withdrawalApplyList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '回购申请列表',
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
                        emptyText:'回购单号、回购人code',
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
                { header: '回购申请编号',  dataIndex: 'withdrawalCode',width:153 },
                { header: '回购类型', dataIndex: 'withdrawalType',width:120 },
                { header: '申请回购的数量', dataIndex: 'applyWithdrawalNum',width:120 },
                { header: '实际回购的数量', dataIndex: 'withdrawalNum',width:120 },
                { header: '代扣税', dataIndex: 'deductions',width:180 },
                { header: '手续费', dataIndex: 'poundage',width:140 },
                { header: '开户行', dataIndex: 'bankName',width:140 },
                { header: '开户支行', dataIndex: 'bankBranch',width:140 },
                { header: '银行账号', dataIndex: 'bankNum',width:140 },
                { header: '开户人姓名', dataIndex: 'accountName',width:140 },
                { header: '申请人ID', dataIndex: 'withdrawalAccount',width:140 },
                { header: '申请人姓名', dataIndex: 'applyAccountName',width:140 },
                { header: '申请状态', dataIndex: 'applyStatus',width:140 },
                { header: '申请时间', dataIndex: 'applyTime',width:140 },
                { text: '操作',dataIndex: 'withdrawalCode', width:170,
                    renderer:function(val){
                        var url='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').approvedWithdrawal(\'' + (val)  + '\')">'+'通过'+'</a>&nbsp; &nbsp;';
                        url+='<a href="javascript:;" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').cancelWithdrawal(\'' + (val)  + '\')">'+'不通过'+'</a>&nbsp; &nbsp;';
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
                {name: 'withdrawalCode', mapping: 'withdrawal.withdrawalCode'},
                {name: 'withdrawalType', mapping: 'withdrawal.withdrawalType'},
                {name: 'applyWithdrawalNum', mapping: 'withdrawal.applyWithdrawalNum'},
                {name: 'withdrawalNum', mapping: 'withdrawal.withdrawalNum'},
                {name: 'deductions', mapping: 'withdrawal.deductions'},
                {name: 'poundage', mapping: 'withdrawal.poundage'},
                {name: 'bankName', mapping: 'withdrawal.bankName'},
                {name: 'bankBranch', mapping: 'withdrawal.bankBranch'},
                {name: 'bankNum', mapping: 'withdrawal.bankNum'},
                {name: 'accountName', mapping: 'withdrawal.accountName'},
                {name: 'withdrawalAccount', mapping: 'withdrawal.withdrawalAccount'},
                {name: 'applyAccountName', mapping: 'applyAccountName'},
                {name: 'applyTime', mapping: 'withdrawal.applyTime'},
                {name: 'applyStatus', mapping: 'withdrawal.applyStatus'},
            ],
            proxy: {
                url: '/back/commons/withdrawal/getWithdrawalApply',
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
     * @param withdrawalCode 回购单code
     */
    approvedWithdrawal:function(withdrawalCode){
        var result = Ext.appContext.invokeService("back/commons/withdrawal","/approvedWithdrawal", {withdrawalCode: withdrawalCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 审核不通过
     * @param withdrawalCode 回购单code
     */
    cancelWithdrawal:function(withdrawalCode){
        var result = Ext.appContext.invokeService("back/commons/withdrawal","/cancelWithdrawal", {withdrawalCode: withdrawalCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    }
})