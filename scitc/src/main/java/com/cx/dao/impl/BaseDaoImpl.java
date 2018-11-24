package com.cx.dao.impl;

import com.cx.dao.IBaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> implements IBaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 泛型的实际运行时的对象
     */
    protected Class<?> entityClass;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 确定泛型的类型
     * @param
     * @return
     */
    public BaseDaoImpl(){
        Class c = this.getClass();//获取泛型类
        Type t = c.getGenericSuperclass();// getGenericSuperclass()获得带有泛型的父类，Type是java所有类型的公共接口
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<?>) p[0]; // 截取第一个，就是泛型的真实类型
        }
    }

    @Override
    public Boolean sava(T t) {
        Session session = getSession();
        try{
            session.save(t);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean delete(T t) {
        Session session = getSession();
        try{
            session.delete(t);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public T findById(Integer id) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq("id",id));
        return (T)criteria.uniqueResult();
    }


    @Override
    public List<T> findAll() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        return criteria.list();
    }

    @Override
    public List<T> findAll(Integer page, Integer pageSize) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        //设置分页参数
        criteria.setFirstResult((page-1)*pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @Override
    public Boolean update(T t) {
        Session session = getSession();
        session.saveOrUpdate(t);
        return true;
    }

    @Override
    public Long totalCount() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        //查询总条数
        criteria.setProjection(Projections.count("id"));
        Long count = (Long) criteria.uniqueResult();
        return count;
    }

    @Override
    public Long getCountWhere(Map<String, Object> parameters) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        //查询总条数
        criteria.setProjection(Projections.count("id"));
        //设置条件
        if(parameters!=null){
            for(String key:parameters.keySet()){
                criteria.add(Restrictions.like(key,"%"+parameters.get(key)+"%"));
            }
        }
        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<T> findAll(Integer page, Integer pageSize, Map<String, Object> parameters) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        //设置分页参数
        criteria.setFirstResult((page-1)*pageSize);
        criteria.setMaxResults(pageSize);
        //设置条件
        if(parameters!=null){
            for(String key:parameters.keySet()){
                System.out.println("key------------->"+key+"--value-->"+"%"+parameters.get(key).toString()+"%");
                criteria.add(Restrictions.like(key,"%"+parameters.get(key)+"%"));
            }
        }
        return criteria.list();
    }
}
