package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.user.dao.IRoleDao;
import com.dyb.platforms.fixfunds.services.business.user.entity.Role;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("roleService")
public class RoleService extends BaseService implements IRoleService {

    public Logger log = Logger.getLogger(RoleService.class);//日志

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     * 新增角色
     * @param role 角色对象
     * @return 角色对象
     */
    @Override
    public Role createRole(Role role) {
        if (role==null)
            throw new DybRuntimeException("新建角色时，角色对象不能为空或null");
        if(DybUtils.isEmptyOrNull(role.getRoleName()))
            throw new DybRuntimeException("新建角色时，角色名称不能为空或null");
        role.setRoleCode(codeBuilder.getRoleCode());
        role.setCreateTime(new Date());
        int info = roleDao.insertObject(role);
        return info>0?role:null;
    }

    /**
     * 修改角色
     * @param role 角色对象
     * @return 角色对象
     */
    @Override
    public Role modifyRole(Role role) {
        if (role==null)
            throw new DybRuntimeException("修改角色时，角色对象不能为空或null");
        if(DybUtils.isEmptyOrNull(role.getRoleCode()))
            throw new DybRuntimeException("修改角色时，角色code不能为空或null");
        if(DybUtils.isEmptyOrNull(role.getRoleName()))
            throw new DybRuntimeException("修改角色时，角色名称不能为空或null");
        int info = roleDao.updateObject(role);
        return info>0?role:null;
    }

    /**
     * 根据code查询角色信息
     * @param roleCode 角色code
     * @return 角色对象
     */
    @Override
    public Role getRoleByCode(String roleCode) {
        if(DybUtils.isEmptyOrNull(roleCode))
            throw new DybRuntimeException("查询角色信息时，角色code不能为空或null");
        return roleDao.getObject(roleCode,true);
    }

    /**
     *获取角色分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Role> getRolePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return roleDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }
}
