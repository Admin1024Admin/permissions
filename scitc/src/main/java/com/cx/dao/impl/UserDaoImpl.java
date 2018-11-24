package com.cx.dao.impl;

import com.cx.dao.IUserDao;
import com.cx.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public User findByUserName(String userName) {
        //获取sessiion
        Session session = getSession();
        //Criteria适用于条件查询
        Criteria criteria = session.createCriteria(User.class);
        //设置条件
        criteria.add(Restrictions.eq("userName",userName));
        //获取一条数据
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public User findById(Integer id) {
        Session session = getSession();
        User user = (User) session.get(User.class,id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllWhere(Integer page, Integer pageSize, String keyword) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        //设置条件
        criteria.add(Restrictions.like("userName","%"+keyword+"%"));
        //设置分页参数
        criteria.setFirstResult((page-1)*pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @Override
    public Long getCountWhere(String keyword) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        //查询总条数
        criteria.setProjection(Projections.count("id"));
        //设置条件
        criteria.add(Restrictions.like("userName","%"+keyword+"%"));
        return (Long) criteria.uniqueResult();
    }

    @Override
    public Boolean update(User user) {
        Session session = getSession();
        session.update(user);
        return true;
    }

    @Override
    public Long totalCount() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        //查询总条数
        criteria.setProjection(Projections.count("id"));
        Long count = (Long) criteria.uniqueResult();
        return count;

    }
}
