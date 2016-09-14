/**
 * 用户编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('DYB_COMMON.system.form.userDetailForm', {
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
         * ｛String｝ 用户userCode为空表示新增，否则就是修改
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
    title: '添加/编辑用户',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        var isHidden=false;
        if (!Ext.exUtils.isEmpty(me.config.userCode)) {
            isHidden=true;
            var result = Ext.appContext.invokeService("/back/commons/user","/getUserByCode", {userCode: me.config.userCode});
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
                {text:'登录名',islabel: true},
                {
                    xtype:'textfield',name: 'userName', bind: '{userName}'
                },
                {},
                {text:'登录密码',islabel: true,hidden:isHidden},
                {
                    xtype:'textfield',inputType:'password',name: 'userPassword',hidden:isHidden
                },
                { hidden:isHidden},
                {text:'确认密码',islabel: true,hidden:isHidden},
                {
                    xtype:'textfield',inputType:'password',name: 'confirmPassword',hidden:isHidden
                },
                {hidden:isHidden},
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
                        me.insertOrUpdateUser(me.getForm());
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
     * 新增或者修改用户
     * @param form 提交表单数据
     */
    insertOrUpdateUser:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var roles = {
                userCode:me.config.userCode,
                sequence:info.sequence,
                userName:info.userName,
                userPassword:info.userPassword,
                confirmPassword:info.confirmPassword,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.userCode)){
                result = Ext.appContext.invokeService('/back/commons/user', '/createUser', roles);
            }else{
                result = Ext.appContext.invokeService('/back/commons/user', '/updateUser', roles);
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