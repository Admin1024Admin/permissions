package com.cx.service.impl;


import com.cx.dao.IRoleDao;
import com.cx.dao.IUserDao;
import com.cx.dto.BaseDto;
import com.cx.dto.enums.StatusEnum;
import com.cx.pojo.Role;
import com.cx.pojo.User;
import com.cx.service.IUserService;
import com.cx.utils.MD5Util;
import com.cx.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//所有的异常回滚
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Override
    public BaseDto<User> login(User user) {
        //从数据库查询数据
        User dUser = userDao.findByUserName(user.getUserName());
        //查询为空说明用户名不存在
        if(dUser==null){
            return new BaseDto<User>(StatusEnum.LOGIN_USER_NAME_IS_WROWS);
        }
        //判断密码
        if(!dUser.getUserPwd().equals(MD5Util.MD55(user.getUserPwd()))){
            return new BaseDto<User>(StatusEnum.LOGIN_USER_PSW_IS_WROWS);
        }
        //登录成功
        return new BaseDto<User>(StatusEnum.LOGIN_SUCCESS,dUser);
    }

    @Override
    public void adminList(PageUtil<User> pageUtil) {
        //获取分页数据
        List<User> users = userDao.findAll(pageUtil.getNowPage(), pageUtil.getPageSize());
        //获取总条数
        Long count = userDao.totalCount();
        //将数据存入pageUtil
        //设置总页码
        pageUtil.setTotalCount(Integer.valueOf(count.toString()));
        //设置数据
        pageUtil.setContentList(users);
    }

    @Override
    public BaseDto<User> delUserById(Integer id) {
        //验证数据
        if(id==null||id<0){
            return new BaseDto<User>(StatusEnum.DATA_ERROE);
        }
        //先查询出对象
        User user = userDao.findById(id);
        //在删除
        Boolean b = userDao.delete(user);
        if(b){
            return new BaseDto<User>(StatusEnum.DELETE_SUCCESS);
        }else{
            return new BaseDto<User>(StatusEnum.DELETE_FAILURE);
        }
    }

    @Override
    public User findById(Integer id) {
        if(id==null||id<1){
            return null;
        }
        return userDao.findById(id);
    }

    @Override
    public BaseDto<User> update(User user) {
        if(user==null||user.getId()<1){
            return new BaseDto<User>(StatusEnum.UPDATE_ERROR);
        }
        //先根据id查询出要修改的对象
        User dUser = userDao.findById(user.getId());
        dUser.setUserName(user.getUserName());
        dUser.setUserPhone(user.getUserPhone());
        //比较两个对象的角色id，看是否相等，更改角色id后，重新冲数据库查询获取角色对象设置
        if(user.getRoleId()!=dUser.getRoleId()){
            dUser.setRoleId(user.getRoleId());
            Role role = roleDao.findById(user.getRoleId());
            dUser.setRole(role);
        }
        System.out.println("更新的user----------+"+dUser);
        //更新保存对象
        Boolean b = userDao.update(dUser);
        if(b){
            return new BaseDto<User>(StatusEnum.UPDATE_SUCCESS);
        }
        return new BaseDto<User>(StatusEnum.UPDATE_ERROR);
    }

    /**
     * 保存用户
     */

    @Override
    public BaseDto<User> sava(User user) {
        //如果user为空或者角色id为空保存失败
        if(user==null||user.getRoleId()<1){
            return new BaseDto<User>(StatusEnum.ADD_ERROR);
        }
        //根据角色id查询出角色对象设置进去
        Role role = roleDao.findById(user.getRoleId());
        user.setRole(role);
        //密码加密
        user.setUserPwd(MD5Util.MD55(user.getUserPwd()));
        //保存用户
        userDao.sava(user);
        return new BaseDto<User>(StatusEnum.ADD_SUCCESS);
    }

    @Override
    public BaseDto<User> batchDelete(String[] ids) {
        if(ids==null&&ids.length<0){
            return new BaseDto<User>(StatusEnum.DATA_ERROE);
        }
        for(String id:ids){
            User user = userDao.findById(Integer.valueOf(id));
            userDao.delete(user);
        }
        return new BaseDto<User>(StatusEnum.DELETE_SUCCESS);
    }

    @Override
    public void adminListWhere(PageUtil<User> pageUtil,String keyword) {
        //获取分页数据
        List<User> users = userDao.findAllWhere(pageUtil.getNowPage(), pageUtil.getPageSize(),keyword);
        //根据条件查询获取总条数d
        Long count = userDao.getCountWhere(keyword);
        //将数据存入pageUtil
        //设置总页码
        pageUtil.setTotalCount(Integer.valueOf(count.toString()));
        //设置数据
        pageUtil.setContentList(users);
    }
}
