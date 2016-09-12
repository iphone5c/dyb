package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.user.dao.IPermissionsDao;
import com.dyb.platforms.fixfunds.services.business.user.entity.Permissions;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("permissionsService")
public class PermissionsService extends BaseService implements IPermissionsService {

    public Logger log = Logger.getLogger(PermissionsService.class);//日志

    @Autowired
    private IPermissionsDao permissionsDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     * 获取所有权限
     * @return
     */
    @Override
    public Permissions getPermissions() {
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("parentCode","root");
        Permissions permissions=permissionsDao.queryList(queryParams,0,-1,true).get(0);
        getPermissionsTree(permissions);
        return permissions;
    }

    private void getPermissionsTree(Permissions permissions){
        if (permissions==null)
            return ;
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("parentCode",permissions.getPermissionsCode());
        List<Permissions> children=permissionsDao.queryList(queryParams,0,-1,true);
        if (children!=null&&children.size()>0){
            permissions.setChildren(children);
            for (Permissions temp:children){
                getPermissionsTree(temp);
            }
        }else {
            return;
        }
    }
}
