/**
 * Created by lenovo on 2016/9/12.
 */
Ext.define('DYB_COMMON.system.permissionsList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'Ext.data.TreeStore'
    ],
    viewConfig: {
        enableTextSelection: true
    },
    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {},
    // ====基类属性重写、属性定义==========================================================
    title: '权限管理',
    frame: false,
    border: false,
    header:false,
    useArrows: true,
    columnLines: true,
    rowLines: true,
    rootVisible: true,
    selModel: {
        selType: 'checkboxmodel'
    },
    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            store: me.createStore(),
            tbar: {
                xtype: 'toolbar', scope: me,
                items: [
                    {
                        xtype: 'button', text: '新增',  scope: me,
                        handler: function () {
                            var treeSelected=me.getSelection();
                            if(treeSelected.length!=1){
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                                return;
                            }
                            me.showDetailWin('',treeSelected[0].data.permissionsCode);
                        }
                    },
                    {
                        xtype: 'button', text: '修改',  scope: me,
                        handler: function () {
                            var treeSelected=me.getSelection();
                            if(treeSelected.length!=1){
                                Ext.Msg.alert('提示', '必须并且只能选中一行数据.');
                                return;
                            }
                            me.showDetailWin(treeSelected[0].data.permissionsCode,'');
                        }
                    },
                    {
                        xtype: 'button', text: '删除',  scope: me,
                        handler: function () {
                            Ext.Msg.alert('操作成功',"修改成功");
                        }
                    },
                    {
                        xtype: 'button', text: '全部合并', glyph: 0xf066, scope: me,
                        handler: function () {
                            this.collapseAll();
                        }
                    },
                    {
                        xtype: 'button', text: '合并展开', glyph: 0xf065, scope: me,
                        handler: function () {
                            this.expandAll();
                        }
                    }
                ]
            },
            columns: [
                {
                    xtype: 'treecolumn',
                    text: '名称',
                    width: 300,
                    sortable: true,
                    dataIndex: 'permissionsName'
                },
                { text: '访问地址', dataIndex: 'permissionsUrl',width: 300 },
                { text: '描述', dataIndex: 'description', flex: 1 }
            ],
            listeners: {
                itemdblclick: function (source, record, item, index, e, eOpts) {
//                    this.showDetailWin(record.data.rightName);
                },
                scope: me
            }
        });
        me.callParent(arguments);
        me.expandAll();
    },


    //====方法定义=======================================================================
    createStore: function () {
        var m = Ext.appContext.invokeService("/permissions","/getPermissionsList", {});
        this.setTreeExpanded(m.result);
        var treeStore = Ext.create('Ext.data.TreeStore', {
            root: m.result
        });
        return treeStore;
    },

    setTreeExpanded:function(result){
        result.expanded = true;
        if(Ext.exUtils.isEmpty(result.children)){
            result.children=[];
            return;
        }
        if(result.children.length<=0){
            return;
        }
        for(var i=0;i<result.children.length;i++){
            result.children[i].expanded=true;
            this.setTreeExpanded(result.children[i])
        }
    },

    reload: function () {
        this.mask('loading......');
        this.reconfigure(this.createStore());
        this.expandAll();
        this.unmask();
    },
    showDetailWin: function (permissionsCode,parentPermissionsCode) {
        var win = Ext.appContext.openWindow("DYB_COMMON.system.form.permissionsDetailForm",{permissionsCode: permissionsCode,parentPermissionsCode:parentPermissionsCode}, {width: 400, height: 210});
        win.innerView.on('DataChanged', function (source, param) {
            this.reload();
        }, this);
    }

});