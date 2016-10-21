/**
 * Created by 魏源 on 2015/6/26 0026.
 */
Ext.define('DYB.Login', {
    extend: 'Ext.Viewport',
    requires: [],
    layout: 'center',
    cls:'login-page',

    initComponent: function () {
        var me = this;
        var html='<form>' +
            '<div class="form-group">' +
            '<input type="text" class="form-control" id="loginName" placeholder="用户名" style="width: 300px">' +
            '</div>' +
            '<div class="form-group">' +
            '<input type="password" class="form-control" id="password" placeholder="密码" style="width: 300px">' +
            '</div>' +
            '<input type="button" value="登录" class="btn btn-success" style="width: 120;margin-left: 20px;margin-right: 10px;margin-top:20px" onclick="javascript:Ext.getCmp(\'' + me.getId()  + '\').submit()">' +
            '<button type="reset" class="btn btn-warning" style="width: 120;margin-left: 10px;margin-top:20px">重置</a>' +
            '</form>';
        Ext.apply(me, {
            items: [
                {
                    xtype: 'panel', border: false, width: 800, height: 700,
                    bodyCls: 'loginBoxGg',
                    items: [
                        {
                            xytpe:'panel',
                            width: 650,
                            height: 100,
                            margin: '0 75 0 75',
                            bodyCls: 'loginBoxTitle'
                        },
                        {
                            xytpe:'panel',
                            width: 550,
                            height: 290,
                            bodyCls: 'loginBoxContext',
                            margin: '100 125 0 125',
                            items:[
                                {
                                    xtype:'panel',
                                    bodyStyle:'background-color: transparent !important;',
                                    margin:'110 0 0 125',
                                    html:html
                                }
                            ]
                        }
                    ]
                }

            ]
        });
        me.callParent(arguments);
    },


    gotoMainFrame: function () {
        this.destroy();
        var main = Ext.create('DYB.application', {
            renderTo: Ext.getBody()
        });
    },
    submit:function(){
        var loginName= document.getElementById('loginName').value;
        var password=document.getElementById('password').value;
        var param = {
            loginName : loginName,
            password: password
        };
        var result = Ext.appContext.invokeService('/back/commons/user', '/loginBack', param);
        if(result.statusCode!=1000){
            Ext.Msg.alert('操作失败', result.errorMessage);
        }else{
            this.gotoMainFrame();
        }
    }
});