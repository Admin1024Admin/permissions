package com.cx.service.impl;

import com.cx.dao.IAccessDao;
import com.cx.dao.IRoleDao;
import com.cx.dto.BaseDto;
import com.cx.dto.enums.StatusEnum;
import com.cx.pojo.Access;
import com.cx.pojo.Role;
import com.cx.service.IRoleService;
import com.cx.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
//所有的异常回滚
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IAccessDao accessDao;

    @Override
    public BaseDto<Role> findAll() {
        List<Role> roles = roleDao.findAll();
        return new BaseDto<Role>(StatusEnum.GET_DATA_SUCCESS,roles);
    }

    @Override
    public void roleList(PageUtil<Role> pageUtil, Map<String, Object> parameters) {
        //获取分页数据
        List<Role> roles = roleDao.findAll(pageUtil.getNowPage(), pageUtil.getPageSize(),parameters);
        //获取总条数
        Long count = roleDao.getCountWhere(parameters);
        //将数据存入pageUtil
        //设置总页码
        pageUtil.setTotalCount(Integer.valueOf(count.toString()));
        //设置数据
        pageUtil.setContentList(roles);
    }

    @Override
    public BaseDto<Role> save(Role role) {
        if(role==null){
            return new BaseDto<Role>(StatusEnum.ADD_ERROR);
        }
        //默认不是超级管理员
        role.setIsSuper(0);
        roleDao.sava(role);
        return new BaseDto<Role>(StatusEnum.ADD_SUCCESS);
    }

    @Override
    public BaseDto<Role> delete(Integer id) {
        if(id==null||id<1){
            return new BaseDto<Role>(StatusEnum.DELETE_FAILURE);
        }
        Role role = roleDao.findById(id);
        if(role==null){
            return new BaseDto<Role>(StatusEnum.DELETE_FAILURE);
        }
        roleDao.delete(role);
        return new BaseDto<Role>(StatusEnum.DELETE_SUCCESS);
    }

    @Override
    public BaseDto<Role> batchDelete(String[] ids) {
        if(ids==null&&ids.length<0){
            return new BaseDto<Role>(StatusEnum.DATA_ERROE);
        }
        for(String id:ids){
            Role user = roleDao.findById(Integer.valueOf(id));
            roleDao.delete(user);
        }
        return new BaseDto<Role>(StatusEnum.DELETE_SUCCESS);
    }

    @Override
    public Role findById(Integer id) {
        if(id==null||id<1){
            return null;
        }
        return roleDao.findById(id);
    }

    @Override
    public BaseDto<Role> update(Role role) {
        if(role==null){
            return new BaseDto<Role>(StatusEnum.DATA_ERROE);
        }
        roleDao.update(role);
        return new BaseDto<Role>(StatusEnum.UPDATE_SUCCESS);
    }

    @Override
    public BaseDto<Role> editAccess(String roleId, String accessIds) {
        //检验数据
        if(roleId==null||accessIds==null||"".equals(roleId)||"".equals(accessIds)){
            return new BaseDto<Role>(StatusEnum.DATA_ERROE);
        }
        //根据角色id查询出当前角色
        Role role = findById(Integer.valueOf(roleId));
        //将当前对象的所有权限清空
        Set<Access> accessSet = new HashSet<>();
        role.setAccesses(accessSet);
        //更新当前用户
        update(role);
        //解析accessIds 前端传递过来是字符串逗号分割 1,2,3,4
        String[] accIds = accessIds.split(",");
        for(String accessId:accIds){
            //根据权限id查询出权限对象
            Access access = accessDao.findById(Integer.valueOf(accessId));
            //添加到角色对象
            role.getAccesses().add(access);
        }
        update(role);
        return new BaseDto<Role>(StatusEnum.UPDATE_SUCCESS);
    }
}
