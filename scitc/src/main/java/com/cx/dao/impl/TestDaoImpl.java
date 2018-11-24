package com.cx.dao.impl;

import com.cx.dao.IUserDao;
import com.cx.pojo.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public class TestDaoImpl extends BaseDao{
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    public  List<User> findAll() {
//        Session session = getSession();
//        Query query = session.createSQLQuery("select * from User").addEntity(User.class);
//        List<User> users = query.list();
//        return users;
//    }
//
//    public User findById(int id) {
//        return this.getHibernateTemplate().get(User.class,id);
//    }
//
//}
