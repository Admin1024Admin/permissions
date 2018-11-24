package com.cx.dao.impl;

import com.cx.dao.IAccessDao;
import com.cx.pojo.Access;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessDaoImpl extends BaseDaoImpl<Access> implements IAccessDao {

    @Override
    public List<Access> findAllMenu() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Access.class);
        //添加条件
        criteria.add(Restrictions.eq("isMenu",1));
        List<Access> menus = criteria.list();
        return menus;
    }

    @Override
    public List<Access> getParents() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Access.class);
        criteria.add(Restrictions.eq("accessLevel",1));
        criteria.add(Restrictions.eq("accessParentId",0));
        return criteria.list();
    }

    @Override
    public List<Access> getAccess() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Access.class);
        return criteria.list();
    }
}
