package com.cx.service;

import com.cx.dto.BaseDto;
import com.cx.pojo.User;
import com.cx.utils.PageUtil;

public interface IUserService {
    /**
     * 登录方法。
     * @return
     */
    BaseDto<User> login(User user);

    /**
     * 分页 管理员列表
     */
    void adminList(PageUtil<User> pageUtil);

    /**
     * 根据id删除数据
     */
    BaseDto<User> delUserById(Integer id);

    /**
     * 根据id查询
     */
    User findById(Integer id);

    /**
     * 更新User
     */
    BaseDto<User> update(User user);

    /**
     * 保存对象
     */
    BaseDto<User> sava(User user);

    /**
     * 批量删除
     */
    BaseDto<User> batchDelete(String[] ids);

    /**
     * 搜索
     */
    void adminListWhere(PageUtil<User> pageUtil,String keyword);
}
