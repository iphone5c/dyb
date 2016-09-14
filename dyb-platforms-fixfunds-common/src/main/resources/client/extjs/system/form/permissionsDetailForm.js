/**
 * 系统参数配置编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('DYB_COMMON.system.form.permissionsDetailForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'DYB.utils.DetailLayout'
    ],

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        /**
         * ｛String｝ 权限permissionsCode为空表示新增，否则就是修改
         */
        permissionsCode: '',

        /**
         * 新增时的必传字段，新权限的父级权限的permissionsCode
         */
        parentPermissionsCode:''
    },

    // ====事件定义========================================================================
    /**
     * 数据改变事件
     * @param source 事件源
     * @param args 事件参数【数据】
     */
    onDataChangedEvent: function (source, args) {
        var me = this;
        me.fireEvent('DataChanged', source, args);
    },

    // ====基类属性重写、属性定义==========================================================
    frame: false,
    title: '添加/编辑权限',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.permissionsCode)) {
            var result = Ext.appContext.invokeService("/back/commons/permissions","/getPermissionsByCode", {permissionsCode: me.config.permissionsCode});
            if(result.statusCode!=1000){
                Ext.Msg.alert('操作失败', result.errorMessage);
            }else{
                data=result.result;
            }
        }
        Ext.apply(me, {
            defaults: {
                viewModel: {data: data},
                xtype:'label'
            },
            layout:{
                type:'dyb-ex-detaillayout',
                columnWidths: [100, 205, 0],
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            items: [
                {text:'权限名称',islabel: true},
                {
                    xtype:'textfield',name: 'permissionsName', bind: '{permissionsName}'
                },
               {},
                {text:'访问地址',islabel: true},
                {
                    xtype:'textfield',name: 'permissionsUrl', bind: '{permissionsUrl}'
                },
                {},
                {text:'描述',islabel: true},
                {
                    xtype:'textfield',name: 'description', bind: '{description}'
                },
                {}

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdatePermissions(me.getForm());
                    }
                },
                {
                    text: '取消', width: 70, scope: this, glyph: 0xf00d,
                    handler: function () {
                        var me = this;
                        me.up('window').close();
                    }
                }
            ]
        });
        me.callParent(arguments);
    },


    //====方法定义=======================================================================

    /**
     * 新增或者修改权限
     * @param form 提交表单数据
     */
    insertOrUpdatePermissions:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var permissions = {
                permissionsCode:me.config.permissionsCode,
                parentCode:me.config.parentPermissionsCode,
                permissionsName:info.permissionsName,
                permissionsUrl:info.permissionsUrl,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.permissionsCode)){
                result = Ext.appContext.invokeService('/back/commons/permissions', '/createPermissions', permissions);
            }else{
                result = Ext.appContext.invokeService('/back/commons/permissions', '/updatePermissions', permissions);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, permissions);
                me.up('window').close();
            }
        }
    }
});