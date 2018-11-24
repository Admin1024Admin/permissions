package com.cx.service.impl;

import com.cx.dao.IAccessDao;
import com.cx.dao.IRoleDao;
import com.cx.dao.IUserDao;
import com.cx.dto.BaseDto;
import com.cx.dto.enums.StatusEnum;
import com.cx.pojo.Access;
import com.cx.pojo.Menu;
import com.cx.pojo.Role;
import com.cx.service.IAccessService;
import com.cx.utils.MenuUtil;
import com.cx.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
//所有的异常回滚
@Transactional(rollbackFor = Exception.class)
public class AccessServiceImpl implements IAccessService {
    @Autowired
    private IAccessDao accessDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;


    @Override
    public BaseDto<Menu> findAllMenu() {
        List<Access> menus = accessDao.findAllMenu();
        List<Menu> changeMneus = MenuUtil.change(menus);
        return new BaseDto<Menu>(StatusEnum.GET_DATA_SUCCESS,changeMneus);
    }

    @Override
    public void accessListWhere(PageUtil<Access> pageUtil, Map<String, Object> parameters) {
        //获取分页数据
        List<Access> accessList = accessDao.findAll(pageUtil.getNowPage(), pageUtil.getPageSize(),parameters);
        //获取总条数
        Long count = accessDao.getCountWhere(parameters);
        //将数据存入pageUtil
        //设置总页码
        pageUtil.setTotalCount(Integer.valueOf(count.toString()));
        //设置数据
        pageUtil.setContentList(accessList);
    }

    @Override
    public BaseDto<Access> getParents() {
        List<Access> parents = accessDao.getParents();
        System.out.println("****************"+parents);
        return new BaseDto<Access>(StatusEnum.GET_DATA_SUCCESS,parents);
    }

    @Override
    public BaseDto<Access> add(Access access) {
        if(access==null){
            return new BaseDto<Access>(StatusEnum.DATA_ERROE);
        }
        accessDao.sava(access);
        return new BaseDto<Access>(StatusEnum.ADD_SUCCESS);
    }

    @Override
    public BaseDto<Menu> getAccess() {
        List<Access> access = accessDao.getAccess();
        List<Menu> changeMneus = MenuUtil.change(access);
        return new BaseDto<Menu>(StatusEnum.GET_DATA_SUCCESS,changeMneus);
    }

    @Override
    public BaseDto<Access> getRoleAccess(Integer roleId) {
        //判断数据是否合法
        if(roleId==null||roleId<1){
            return new BaseDto<Access>(StatusEnum.DATA_ERROE);
        }
        //根据角色Id关联查询出对应的权限
        Role role = roleDao.findById(roleId);
        //获取角色下的所有权限
        Set<Access> accesses = role.getAccesses();
        //将Set转换为List
        List<Access> accessList = new ArrayList<>(accesses);
        //返回数据
        return new BaseDto<Access>(StatusEnum.GET_DATA_SUCCESS,accessList);
    }

    @Override
    public BaseDto<Access> deleteById(Integer id) {
        //检验数据
        if(id==null||id<1){
            return new BaseDto<Access>(StatusEnum.DATA_ERROE);
        }
        //根据id查询出删除的权限对象
        Access access = accessDao.findById(id);
        accessDao.delete(access);
        return new BaseDto<Access>(StatusEnum.DELETE_SUCCESS);
    }

    @Override
    public BaseDto<Access> batchDelete(String[] ids) {
        //检验数据
        if(ids==null){
            return new BaseDto<Access>(StatusEnum.DATA_ERROE);
        }
        //遍历ids 删除权限
        for (String id:ids){
            deleteById(Integer.valueOf(id));
        }
        //删除成功
        return new BaseDto<Access>(StatusEnum.DELETE_SUCCESS);
    }

    @Override
    public BaseDto<Access> updateAccess(Access access) {
        //检验数据
        if(access==null||access.getId()<1){
            return new BaseDto<Access>(StatusEnum.DATA_ERROE);
        }
        //查询数据库对象
        Access dAccess = accessDao.findById(access.getId());
        //更新数据
        dAccess.setAccessParentId(access.getAccessParentId());
        dAccess.setAccessLevel(access.getAccessLevel());
        dAccess.setIsMenu(access.getIsMenu());
        dAccess.setAccessUrl(access.getAccessUrl());
        dAccess.setAccessName(access.getAccessName());
        //保存
        accessDao.update(dAccess);
        return new BaseDto<Access>(StatusEnum.UPDATE_SUCCESS);
    }

    @Override
    public Access findById(Integer id) {
        //检验数据
        if(id==null||id<1){
            return null;
        }
        return accessDao.findById(id);
    }
}
