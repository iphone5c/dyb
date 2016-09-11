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
         * ｛String｝ systemParamsCode 系统参数配置主键，没有表示新增，有表示修改
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
    title: '新增/编辑系统参数配置信息',
    //bodyPadding: 10,
    border: false,
    bodyBorder: false,
    header:{hidden:true},

    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        me.parentdeptdeptCombo = this.createParentdeptCombo();
        var data = {};
        if (me.config.systemParamsCode != '') {
            var result = Ext.appContext.invokeService("/systemparams","/getSystemParamsByCode", {systemParamsCode: me.config.systemParamsCode});
            if(result.statusCode==1000){
                data=result.result;
            }else{
                Ext.Msg.alert('操作失败', result.errorMessage);
            }
        }
        var isEdit = me.config.userId;
        Ext.apply(me, {
            defaults: {
                viewModel: {data: data},
                xtype:'label'
            },
            layout:{
                type:'dyb-ex-detaillayout',
                columnWidths: [80, 205, 0],
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            items: [
                {text:'参数KEY',islabel: true},
                {
                    xtype:'textfield',name: 'systemParamsKey', bind: '{systemParamsKey}', maxLength: 50, width:195
                },
                {},
                {text:'参数值',islabel: true},
                {
                    xtype:'textfield',name: 'systemParamsValue', bind: '{systemParamsValue}', maxLength: 50, width:195
                },
                {},
                {text:'描述',islabel: true},
                {
                    xtype:'textfield',name: 'description', bind: '{description}', maxLength: 50, width:195
                },
                {}
            ],
            listeners: {
                afterrender: function (source, eOpts) {
                    me.parentdeptdeptCombo.setValue(data.deptId);
                }
            },

            buttons: [
                {
                    text: '下一个', scope: this, width: 70, glyph: 0xf046,
                    hidden: me.config.userId != '',
                    handler: function () {
                        var me = this;
                        me.addUser(me.getForm(), false);
                    }
                },
                {
                    text: '确定', scope: this, width: 70, glyph: 0xf00c,
                    handler: function () {
                        var me = this;
                        me.addUser(me.getForm(), true);
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

        me.setTitle(me.config.userId == '' ? '创建用户' : '修改用户信息');
    },


    //====方法定义=======================================================================
    addUser: function (form, isClose) {
        var me = this;
        var isEdit = me.config.userId;
        if (form.isValid()) {
            var param = {user: {
                //"@type":"",
                userName: isEdit?'':form.findField("userName").getValue(),
                fullName: form.findField("fullName").getValue(),
                deptId: this.down('#deptId').getValue(),
                description: form.findField("description").getValue()
            }};
            if(!isEdit){
                form.submit({
                    url: '/back/business/user/account/addUser',
                    waitTitle: '提示',
                    method: 'POST',
                    type:'ajax',
                    waitMsg: '正在处理数据,请稍候...',
                    success: function (form, action) {
                        Ext.Msg.alert('成功', "添加成功！");
                        me.onDataChangedEvent(me, param);
                        if (isClose) {
                            me.up('window').close();
                        }
                        else
                            form.reset();
                    },
                    failure: function (form, action) {
                        Ext.Msg.alert('失败', action.result.errorMessage);
                    }, scope: this
                });
            } else {
                form.submit({
                    url: '/back/business/user/account/modifyUser',
                    waitTitle: '提示',
                    method: 'POST',
                    type:'ajax',
                    waitMsg: '正在处理数据,请稍候...',
                    success: function (form, action) {
                        Ext.Msg.alert('成功', "修改成功！");
                        me.onDataChangedEvent(me, param);
                        if (isClose) {
                            me.up('window').close();
                        }
                        else
                            form.reset();
                    },
                    failure: function (form, action) {
                        Ext.Msg.alert('失败', action.result.errorMessage);
                    }, scope: this
                });
            }

        }
    },
    constructStore: function (result) {
        result.expanded = true;
        result.id = result.deptId;
        result.text = result.deptName;
        if(result.children){
            for (var i = 0; i < result.children.length; i++) {
                this.constructStore(result.children[i]);
            }
        }
    },
    createStore: function () {
        var m = Ext.appContext.invokeService("/back/business/publicController",
            "/deptList", {});
        this.constructStore(m.result[0]);
        var treeStore = Ext.create('Ext.data.TreeStore', {
            root: {
                text: '',
                expanded: true,
                children:m.result[0]
            }
        });
        return treeStore;
    },
    createParentdeptCombo: function () {
        var combox = Ext.create('Ext.ux.TreePicker', {
            name: 'deptName',
            allowBlank: false,
            blankText: '该字段不能为空',
            displayField: 'text',
            width:195,
            emptyText: '请选择...',
            animate: true,
            autoScroll: true,
            minPickerHeight: 200,
            store: this.createStore()
        });
        combox.on('select', function (view, record) {
            this.down('#deptId').setValue(record.data.id);
        }, this);
        return combox;
    }
});