/**
 * Created by Administrator on 2016/2/26.
 */
Ext.application({
   name:'YCsys' ,
    launch:function(){
        Ext['appContext'] = Ext.create('YC_CORESYSTEM.utils.AppContext');
        Ext.create('YC_CORESYSTEM.application', {
            renderTo: Ext.getBody()
        });
    }
});