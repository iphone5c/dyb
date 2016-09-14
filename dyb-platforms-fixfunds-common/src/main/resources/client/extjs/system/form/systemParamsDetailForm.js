/**
 * 系统参数配置编辑视图
 * Created by Caven on 2015/2/12.
 */
Ext.define('DYB_COMMON.system.form.systemParamsDetailForm', {
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
         * ｛String｝ 系统参数配置systemParamsCode为空表示新增，否则就是修改
         */
        systemParamsCode: ''
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
    title: '添加/编辑系统参数配置',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        var data = {};
        if (!Ext.exUtils.isEmpty(me.config.systemParamsCode)) {
            var result = Ext.appContext.invokeService("/back/commons/systemparams","/getSystemParamsByCode", {systemParamsCode: me.config.systemParamsCode});
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
                {text:'参数KEY',islabel: true},
                {
                    xtype:'textfield',name: 'systemParamsKey', bind: '{systemParamsKey}',maxLength: 20, blankText:'角色名不许为空',maxLengthText: '最大长度是20',readOnly:Ext.exUtils.isEmpty(me.config.systemParamsCode)?false:true
                },
               {},
                {text:'参数值',islabel: true},
                {
                    xtype:'textfield',name: 'systemParamsValue', bind: '{systemParamsValue}',maxLength: 20, blankText:'角色名不许为空',maxLengthText: '最大长度是20'
                },
                {},
                {text:'描述',islabel: true},
                {
                    xtype:'textfield',name: 'description', bind: '{description}',maxLength: 20, blankText:'角色名不许为空',maxLengthText: '最大长度是20'
                },
                {}

            ],
            buttons: [
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.insertOrUpdateSystemParams(me.getForm());
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
     * 新增或者修改系统参数配置
     * @param form 提交表单数据
     */
    insertOrUpdateSystemParams:function(form){
        var me = this;
        if (form.isValid()) {
            var info = form.getFieldValues();
            var systemParams = {
                systemParamsCode:me.config.systemParamsCode,
                systemParamsKey:info.systemParamsKey,
                systemParamsValue:info.systemParamsValue,
                description:info.description
            }
            var result;
            if(Ext.exUtils.isEmpty(me.config.systemParamsCode)){
                result = Ext.appContext.invokeService('/back/commons/systemparams', '/createSystemParams', systemParams);
            }else{
                result = Ext.appContext.invokeService('/back/commons/systemparams', '/updateSystemParams', systemParams);
            }
            if (result.statusCode != 1000)
                Ext.Msg.alert('操作失败', result.errorMessage);
            else {
                Ext.Msg.alert('成功', "操作成功!");
                me.onDataChangedEvent(me, systemParams);
                me.up('window').close();
            }
        }
    }
});