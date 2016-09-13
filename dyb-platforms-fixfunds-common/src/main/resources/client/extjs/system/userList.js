/**
 * Created by Administrator on 2016/2/26.
 */
Ext.define('DYB_COMMON.system.userList',{
    extend: 'Ext.grid.Panel',
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},

    // ====基类属性重写、属性定义==========================================================
    title: '管理员列表',
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
                        xtype: 'button', text: '新增',  scope: me,
                        handler: function () {
                            me.showDetailWin();
                        }
                    },
                    {
                        xtype: 'button', text: '修改',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.showDetailWin(list[0].data.userCode);
                        }
                    },
                    {
                        xtype: 'button', text: '禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.disableUser(list[0].data.userCode);
                        }
                    },
                    {
                        xtype: 'button', text: '解除禁用',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.removeDisableUser(list[0].data.userCode);
                        }
                    },
                    {
                        xtype: 'button', text: '修改密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.updateUserPassword(list[0].data.userCode);
                        }
                    },
                    {
                        xtype: 'button', text: '重置密码',  scope: me,
                        handler: function () {
                            var list = me.getSelection();
                            if (list.length != 1)
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                            else
                                me.resetUserPassword(list[0].data.userCode);
                        }
                    },
                    {
                        xtype: 'button', text: '修改角色',  scope: me,
                        handler: function () {
                            Ext.Msg.alert('操作成功',"修改成功");
                        }
                    }
                ]
            },
            columns: [
                { header: '用户编号',  dataIndex: 'userCode',width:153 },
                { header: '排序',  dataIndex: 'sequence',width:50 },
                { header: '登录名', dataIndex: 'userName',width:120 },
                { header: '登陆密码', dataIndex: 'userPassword',width:120 },
                { header: '状态', dataIndex: 'status',width:65 },
                { header: '描述', dataIndex: 'description',width:180 },
                { header: '注册时间', dataIndex: 'createTime',width:140 },
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
                url: '/user/getUserPageList',
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

    showDetailWin: function (userCode) {
        var heigth=Ext.exUtils.isEmpty(userCode)? 255:185;
        var win = Ext.appContext.openWindow("DYB_COMMON.system.form.userDetailForm",{userCode: userCode}, {width: 300, height: heigth});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    },

    /**
     * 禁用用户
     * @param userCode 用户code
     */
    disableUser:function(userCode){
        var result = Ext.appContext.invokeService("/user","/disableUser", {userCode: userCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 解除禁用
     * @param userCode 用户code
     */
    removeDisableUser:function(userCode){
        var result = Ext.appContext.invokeService("/user","/removeDisableUser", {userCode: userCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     * 重置密码
     * @param userCode 用户code
     */
    resetUserPassword:function(userCode){
        var result = Ext.appContext.invokeService("/user","/resetUserPassword", {userCode: userCode});
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            Ext.Msg.alert('成功', result.result);
            this.reload();
        }
    },

    /**
     *修改用户密码
     * @param userCode
     */
    updateUserPassword: function (userCode) {
        var win = Ext.appContext.openWindow("DYB_COMMON.system.form.updateUserPasswordForm",{userCode: userCode}, {width: 300, height: 185});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    }
})