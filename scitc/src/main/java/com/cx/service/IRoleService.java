package com.cx.service;

import com.cx.dto.BaseDto;
import com.cx.pojo.Role;
import com.cx.utils.PageUtil;

import java.util.Map;

public interface IRoleService {
    /**
     * 查询全部
     */
    public BaseDto<Role> findAll();

    /**
     * 分页查询 带条件
     */
    public void roleList(PageUtil<Role> pageUtil,Map<String,Object> parameters);

    /**
     * 保存角色
     */
    public BaseDto<Role> save(Role role);

    /**
     * 删除角色
     */
    public BaseDto<Role> delete(Integer id);

    /**
     * 批量删除角色
     */
    public BaseDto<Role> batchDelete(String[] ids);

    /**
     * 根据id查询角色
     */
    public Role findById(Integer id);

    /**
     * 更新角色
     */
    public BaseDto<Role> update(Role role);
    /**
     * 更新角色权限
     */
    public BaseDto<Role> editAccess(String roleId,String accessIds);
}
