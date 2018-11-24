package com.cx.dao;

import com.cx.pojo.Access;

import java.util.List;

public interface IAccessDao extends IBaseDao<Access>{
    /**
     * 查询所有菜单
     */
    List<Access> findAllMenu();

    /**
     * 获取父级菜单
     */
    List<Access> getParents();

    /**
     * 获取全部的权限
     */
    List<Access> getAccess();

}
