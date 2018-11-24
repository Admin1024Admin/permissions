package com.cx.web.action;

import com.cx.dto.BaseDto;
import com.cx.pojo.Role;
import com.cx.pojo.User;
import com.cx.service.IRoleService;
import com.cx.service.IUserService;
import com.cx.utils.JsonResult;
import com.cx.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class AdminAction extends BaseAction implements ModelDriven<User> {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    private User user = new User();
    /**
     * 获取管理员列表
     */
    public String list(){
        //页容量
        Integer pageSize = 5;
        //当前页
        String pageStr = request.getParameter("page");
        Integer page  = pageStr==null?1:Integer.valueOf(pageStr);
        //实例化PageUtil
        PageUtil<User> pageUtil = new PageUtil<>(page,pageSize);
        //查询
        userService.adminList(pageUtil);
        LOG.info("-------------->"+pageUtil.toString());
        //将数据发送到页面 json数据
        request.setAttribute("pageUtil",pageUtil);
        return SUCCESS;
    }

    /**
     * 根据id删除数据
     */
    public void delete(){
        Integer id = user.getId();
        BaseDto<User> userBaseDto = userService.delUserById(id);
        JsonResult.response(response,userBaseDto.getCode(),userBaseDto.getMsg(),null);
    }

    /**
     * 批量删除
     */
    public void deleteAll(){
        //获取前端ajax传递过来的数组。变量名后面一定加[],切记
        String[] ids = request.getParameterValues("ids[]");
        BaseDto<User> userBaseDto = userService.batchDelete(ids);
        JsonResult.response(response,userBaseDto.getCode(),userBaseDto.getMsg(),null);
    }

    /**
     * 根据id查询跳转到修改页面
     * @return
     */
    public String goUpdate(){
        Integer id = user.getId();
        User u = userService.findById(id);
        BaseDto<Role> roles = roleService.findAll();
        //设置单条用户对象
        request.setAttribute("data",u);
        //设置角色列表
        request.setAttribute("roleData",roles.getDataList());
        return SUCCESS;
    }

    /**
     * 更新数据
     * @return
     */
    public void update(){
        LOG.info("--->"+user);
        BaseDto<User> userBaseDto = userService.update(user);
        JsonResult.response(response,userBaseDto.getCode(),userBaseDto.getMsg(),null);
    }
    @Override
    public User getModel() {
        return user;
    }

    /**
     * 跳转到添加页面
     */
    public String goAdd(){
        BaseDto<Role> roles = roleService.findAll();
        //设置角色列表
        request.setAttribute("data",roles.getDataList());
        return SUCCESS;
    }
    /**
     * 添加用户
     */
    public void add(){
        BaseDto<User> userBaseDto = userService.sava(user);
        JsonResult.response(response,userBaseDto.getCode(),userBaseDto.getMsg(),null);
    }

    /**
     * 关键字搜索
     */
    public String search(){
        //获取条件
        String keyword = request.getParameter("keyword");
        //将条件保存进request中
        request.setAttribute("keyword",keyword);
        //页容量
        Integer pageSize = 5;
        //当前页
        String pageStr = request.getParameter("page");
        Integer page  = pageStr==null?1:Integer.valueOf(pageStr);
        //实例化PageUtil
        PageUtil<User> pageUtil = new PageUtil<>(page,pageSize);
        //查询
        userService.adminListWhere(pageUtil,keyword);
        //将数据发送到页面 json数据
        request.setAttribute("pageUtil",pageUtil);
        return SUCCESS;
    }

}
