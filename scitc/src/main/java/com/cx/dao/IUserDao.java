package com.cx.dao;

import com.cx.pojo.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User>{
    /**
     * 通过用户名查询对象
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 分页条件搜索
     */
    public List<User> findAllWhere(Integer page,Integer pageSize,String keyword);

    /**
     * 带条件查询总数
     */
    public Long getCountWhere(String keyword);
}
