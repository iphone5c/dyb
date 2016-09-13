package com.dyb.platforms.fixfunds.services.business.user.service;


import com.dyb.platforms.fixfunds.services.business.user.entity.Role;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface IRoleService {

    /**
     * 新增角色
     * @param role 角色对象
     * @return 角色对象
     */
    public Role createRole(Role role);

    /**
     * 修改角色
     * @param role 角色对象
     * @return 角色对象
     */
    public Role modifyRole(Role role);

    /**
     * 根据code查询角色信息
     * @param roleCode 角色code
     * @return 角色对象
     */
    public Role getRoleByCode(String roleCode);

    /**
     *获取角色分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Role> getRolePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);
}
