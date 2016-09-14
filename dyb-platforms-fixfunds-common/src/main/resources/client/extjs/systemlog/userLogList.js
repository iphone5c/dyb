/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.systemlog.userLogList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '管理员操作日志列表',
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
                        name: 'keyworld',
                        itemId: 'keyworld',
                        labelWidth: 50,
                        value:me.config.keyword,
                        emptyText:'日志编号'
//                        listeners: {
//                            specialkey: function(field, e){
//                                if (e.getKey() == e.ENTER) {
//                                    var val = me.down('#txtUserName').getValue();
//                                    var status = me.down('#status').getValue();
//                                    var endIssuestarttime = me.down('#endIssuestarttime').getValue();
//                                    me.reload('%'+ val + '%',status,endIssuestarttime,0);
//                                }
//                            }
//                        }
                    },
                    {
                        xtype: 'button', text: '查询', glyph: 0xf002, scope: me,
                        handler: function () {
//                            var val = me.down('#txtUserName').getValue();
//                            var status = me.down('#status').getValue();
//                            var endIssuestarttime = me.down('#endIssuestarttime').getValue();
//                            me.reload('%'+ val + '%',status,endIssuestarttime,0);
                        }
                    }
                ]
            },
            columns: [
                { header: '会员CODE',  dataIndex: 'userCode',width:153 },
                { header: '会员名称',  dataIndex: 'userName',width:150 },
                { header: 'IP地址', dataIndex: 'userLoginIp',width:120 },
                { header: '操作类型', dataIndex: 'operationType',width:180 },
                { header: '描述', dataIndex: 'description',width:180 },
                { header: '操作时间', dataIndex: 'createTime',width:140 },
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
            fields: [],
            proxy: {
                url: '/back/commons/userlog/getSystemParamsList',
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
    }

//    showDetailWin: function (roleCode) {
//        var win = Ext.appContext.openWindow("DYB_COMMON.system.form.roleDetailForm",{roleCode: roleCode}, {width: 300, height: 215});
//        win.innerView.on('DataChanged', function (source, param) {
//            this.reload();
//        }, this);
//    }
})