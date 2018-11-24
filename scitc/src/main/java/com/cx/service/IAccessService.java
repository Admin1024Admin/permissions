package com.cx.service;

import com.cx.dto.BaseDto;
import com.cx.pojo.Access;
import com.cx.pojo.Menu;
import com.cx.utils.PageUtil;

import java.util.Map;

public interface IAccessService {
    /**
     * 获取所有菜单
     */
    BaseDto<Menu> findAllMenu();

    /**
     * 分页查询带条件
     */
    void accessListWhere(PageUtil<Access> pageUtil,Map<String,Object> parameters);

    /**
     * 获取父级菜单
     */
    BaseDto<Access> getParents();

    /**
     * 添加权限
     */
    BaseDto<Access> add(Access access);

    /**
     *  查询所有权限
     */
    BaseDto<Menu> getAccess();

    /**
     * 获取当前用于的权限
     */
    BaseDto<Access> getRoleAccess(Integer uid);

    /**
     * 根据id删除权限
     */
    BaseDto<Access> deleteById(Integer id);

    /**
     * 批量删除
     */
    BaseDto<Access> batchDelete(String[] ids);

    /**
     * 修改权限
     */
    BaseDto<Access> updateAccess(Access access);

    /**
     * 根据id查询
     */
    Access findById(Integer id);

}
