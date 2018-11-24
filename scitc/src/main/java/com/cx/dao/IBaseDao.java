package com.cx.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
    /**
     * 添加
     */
    Boolean sava(T t);

    /**
     * 删除
     */
    Boolean delete(T t);

    /**
     * 根据id查询对象
     */
    T findById(Integer id);

    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 分页查询
     */
    public List<T> findAll(Integer page,Integer pageSize);

    /**
     * 修改
     */
    Boolean update(T t);

    /**
     * 查询所有记录条数
     */
    Long totalCount();

    /**
     * 根据条件查询所有记录
     */
    Long getCountWhere(Map<String,Object> parameters);

    /**
     * 带条件分页查询
     */
    public List<T> findAll(Integer page, Integer pageSize,Map<String,Object> parameters);
}
