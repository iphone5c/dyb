/**
 * 角色编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('DYB_COMMON.system.form.roleDetailForm', {
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
         * ｛String｝ 角色roleCode为空表示新增，否则就是修改
         */
        roleCode: ''
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
    title: '添加/编辑角色',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.roleCode)) {
            var result = Ext.appContext.invokeService("/role","/getRoleByCode", {roleCode: me.config.roleCode});
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
                {text:'排序',islabel: true},
                {
                    xtype:'textfield',name: 'sequence', bind: '{sequence}'
                },
               {},
                {text:'角色名称',islabel: true},
                {
                    xtype:'textfield',name: 'roleName', bind: '{roleName}'
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
                        me.insertOrUpdateRole(me.getForm());
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
     * 新增或者修改角色
     * @param form 提交表单数据
     */
    insertOrUpdateRole:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var roles = {
                roleCode:me.config.roleCode,
                sequence:info.sequence,
                roleName:info.roleName,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.roleCode)){
                result = Ext.appContext.invokeService('/role', '/createRole', roles);
            }else{
                result = Ext.appContext.invokeService('/role', '/updateRole', roles);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, roles);
                me.up('window').close();
            }
        }
    }
});