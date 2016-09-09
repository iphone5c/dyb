/**
 * Created by Administrator on 2016/2/26.
 */
Ext.application({
   name:'DYBsys' ,
    launch:function(){
        Ext['appContext'] = Ext.create('DYB.utils.AppContext');
        Ext.create('DYB.application', {
            renderTo: Ext.getBody()
        });
    }
});