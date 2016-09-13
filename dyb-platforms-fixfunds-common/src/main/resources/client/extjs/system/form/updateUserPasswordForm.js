/**
 * 用户编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('DYB_COMMON.system.form.updateUserPasswordForm', {
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
         * ｛String｝ 需要修改密码的用户code
         */
        userCode: ''
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
    title: '修改密码',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            defaults: {
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
                {text:'旧密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'oldPassword'
                },
                { },

                {text:'新密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'newPassword'
                },
                { },

                {text:'确认密码',islabel: true},
                {
                    xtype:'textfield',inputType:'password',name: 'confirmPassword'
                },
                { }

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.updateUserPassword(me.getForm());
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
     * 修改密码
     * @param form 提交表单数据
     */
    updateUserPassword:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var userpassword = {
                userCode:me.config.userCode,
                oldPassword:info.oldPassword,
                newPassword:info.newPassword,
                confirmPassword:info.confirmPassword
            }
            var result = Ext.appContext.invokeService('/user', '/updateUserPassword', userpassword);
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', result.result);
                me.onDataChangedEvent(me, userpassword);
                me.up('window').close();
            }
        }
    }
});