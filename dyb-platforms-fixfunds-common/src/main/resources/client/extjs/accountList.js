/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.accountList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '用户列表',
    frame: false,
    border: false,
    header: false,

    initComponent: function () {
        var me=this;
        var store = Ext.create('Ext.data.Store', {
            autoLoad: true,
            pageSize:2,
            fields: [],
            proxy: {
                url: '/commons/getUserList',
                type: 'ajax',
                extraParams: {pageIndex:0,pageSize:2},
                reader: {
                    type: 'json',
                    rootProperty: 'result.list',
                    totalProperty: 'result.totalSize',
                    messageProperty: 'errorMessage',
                    successProperty: 'success'
                }
            }
        });

        Ext.applyIf(me, {                             //如果指定对象不存在相同的属性，将配置的属性拷贝到指定对象
            store: store,
            tbar:{
                xtype: 'toolbar', scope: me,
                items:[
                    {
                        xtype: 'button', text: '添加',  scope: me,
                        handler: function () {
                            var results={
                                user:{userCode:'123',userName:'123'},
                                userList:[
                                    {userCode:'123',userPassword:'123',userName:'123'},
                                    {userCode:'456',userPassword:'456',userName:'456'},
                                    {userCode:'789',userPassword:'789',userName:'789'}
                                ],
                                userCode:'userCode1'
                            }
                            var param={ params: Ext.util.JSON.encode(results)}
                            var result=Ext.appContext.invokeService('/account','/addAccount',param);
                            if(result.statusCode!=1000)
                                Ext.Msg.alert('操作失败',result.result);
                            else
                                Ext.Msg.alert('操作成功',"添加成功");
                        }
                    }
                ]
            },
            columns: [
                { header: 'userCode',  dataIndex: 'userCode' },
                { header: 'userName', dataIndex: 'userName' },
                { header: 'userPassword', dataIndex: 'userPassword' },
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
    reload: function (params) {
        var me = this;
        Ext.exUtils.combine(me.store.proxy.extraParams, params);
        me.store.reload();
        me.store.currentPage = me.store.proxy.extraParams.pageIndex + 1;
    }
})