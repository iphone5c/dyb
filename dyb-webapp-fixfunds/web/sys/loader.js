/**
 * Created by Administrator on 2016/2/26.
 */
Ext.application({
   name:'DYBsys' ,
    launch:function(){
        Ext['appContext'] = Ext.create('DYB.utils.AppContext');
        Ext['exUtils'] = Ext.create('DYB.utils.ExUtils');

        var auth = Ext.appContext.invokeService('/back/commons/user', '/loginStatus', {});
        if(auth.statusCode!=1000){
            Ext.create('DYB.Login', {
                renderTo: Ext.getBody()
            });
        }else{
            Ext.create('DYB.application', {
                renderTo: Ext.getBody()
            });
        }
    }
});