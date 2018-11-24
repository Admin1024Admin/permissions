package com.cx.dao.impl;

import com.cx.dao.IRoleDao;
import com.cx.pojo.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

    @Override
    public Role findById(Integer id) {
        Session session = getSession();
        Role role = (Role)session.get(Role.class,id);
        return role;
    }

    @Override
    public List<Role> findAll() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Role.class);
        List roles = criteria.list();
        return roles;
    }
}
