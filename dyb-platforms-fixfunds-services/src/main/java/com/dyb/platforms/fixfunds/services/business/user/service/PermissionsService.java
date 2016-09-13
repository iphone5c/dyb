package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.user.dao.IPermissionsDao;
import com.dyb.platforms.fixfunds.services.business.user.entity.Permissions;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    /**
     * 根据code获取权限信息
     * @param permissionsCode 权限主键
     * @return 权限信息
     */
    @Override
    public Permissions getPermissionsByCode(String permissionsCode) {
        if (DybUtils.isEmptyOrNull(permissionsCode))
            throw new DybRuntimeException("查询权限时，permissionsCode不能为空或null");
        return permissionsDao.getObject(permissionsCode,true);
    }

    /**
     * 新增权限信息
     * @param permissions 权限对象
     * @return 权限对象
     */
    @Override
    public Permissions createPermissions(Permissions permissions) {
        if (permissions==null)
            throw new DybRuntimeException("新建权限时，权限对象不能为空或null");
        if(DybUtils.isEmptyOrNull(permissions.getPermissionsName()))
            throw new DybRuntimeException("新建权限时，权限名称不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsUrl()))
            throw new DybRuntimeException("新建权限时，权限访问地址不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getParentCode()))
            throw new DybRuntimeException("新建权限时，必须选择一项父级权限");
        Permissions parent=permissionsDao.getObject(permissions.getParentCode(),true);
        if (parent==null)
            throw new DybRuntimeException("新建权限时，找不到此父级菜单信息，parentCode："+permissions.getParentCode());
        permissions.setPermissionsCode(codeBuilder.getPermissionsCode());
        permissions.setCreateTime(new Date());
        permissions.setLeaf(true);
        int info=permissionsDao.insertObject(permissions);
        if (info>0&&parent.isLeaf()){
            parent.setLeaf(false);
            permissionsDao.updateObject(parent);
            return permissions;
        }else{
            return null;
        }
    }

    /**
     * 修改权限信息
     * @param permissions 权限对象
     * @return 权限对象
     */
    @Override
    public Permissions modifyPermissions(Permissions permissions) {
        if (permissions==null)
            throw new DybRuntimeException("修改权限时，权限对象不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsCode()))
            throw new DybRuntimeException("修改权限时，必须选择一项需要修改的数据");
        if(DybUtils.isEmptyOrNull(permissions.getPermissionsName()))
            throw new DybRuntimeException("修改权限时，权限名称不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getPermissionsUrl()))
            throw new DybRuntimeException("修改权限时，权限访问地址不能为空或null");
        if (DybUtils.isEmptyOrNull(permissions.getParentCode()))
            throw new DybRuntimeException("修改权限时，必须选择一项父级权限");
        Permissions temp=permissionsDao.getObject(permissions.getPermissionsCode(),true);
        if (temp==null)
            throw new DybRuntimeException("修改权限时，找不到此权限信息，code："+permissions.getPermissionsCode());
        if(!temp.getParentCode().equals(permissions.getParentCode()))
            throw new DybRuntimeException("修改权限时，不能修改父级权限");
        int info=permissionsDao.updateObject(permissions);
        return info>0?permissions:null;
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
