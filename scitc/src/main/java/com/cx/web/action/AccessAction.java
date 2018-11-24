package com.cx.web.action;

import com.cx.dto.BaseDto;
import com.cx.pojo.Access;
import com.cx.pojo.Menu;
import com.cx.pojo.User;
import com.cx.service.IAccessService;
import com.cx.service.IUserService;
import com.cx.utils.JsonResult;
import com.cx.utils.MenuUtil;
import com.cx.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@Scope("prototype")
public class AccessAction extends BaseAction implements ModelDriven<Access>{
    @Autowired
    private IAccessService accessService;
    @Autowired
    private IUserService userService;

    private Access access = new Access();
    /**
     * 获取菜单
     */
    public void getMenus(){
        //判断是不是管理员
        //是管理员返回所有的菜单
        //不是管理员
        //返回用户所属角色下的所有菜单
        //冲session中获取到用户
        User user =(User) request.getSession().getAttribute("user");
        //超级管理员
        if(user.getRole().getIsSuper()==1){
            BaseDto<Menu> menus = accessService.findAllMenu();
            JsonResult.response(response,menus.getCode(),menus.getMsg(),menus.getDataList());
        }else{
            // 普通管理员
            //数据库查询User对象
            //解决NoSession问题
            User dUser = userService.findById(user.getId());
            Set<Access> accesses = dUser.getRole().getAccesses();
            // 准备菜单的access
            List<Access> menuAccess = new ArrayList<>();
            // 遍历去除非菜单权限
            for (Access access1 : accesses) {
                if(access1.getIsMenu() == 1){
                    menuAccess.add(access1);
                }
            }
            // 再将权限转换成菜单
            List<Menu> menus = MenuUtil.change(menuAccess);
            // 直接json的返回
            JsonResult.response(response,200,"success",menus);
        }
    }
    /**
     *跳转到权限列表
     */
    public String access(){
        //页容量
        Integer pageSize = 5;
        //当前页
        String pageStr = request.getParameter("page");
        //获取所有参数条
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String,Object> parameters = new HashMap<>();
        for(String key:parameterMap.keySet()){
            if(!"page".equals(key)){
                if(parameterMap.get(key).length>0){
                    parameters.put(key,parameterMap.get(key)[0]);
                }
            }
        }
        Integer page  = pageStr==null?1:Integer.valueOf(pageStr);
        //实例化PageUtil
        PageUtil<Access> pageUtil = new PageUtil<>(page,pageSize);
        accessService.accessListWhere(pageUtil,parameters);
        LOG.info("-------------->"+pageUtil.toString());
        //将数据发送到页面 json数据
        request.setAttribute("pageUtil",pageUtil);
        return SUCCESS;
    }

    /**
     * 查询父级菜单
     */
    public void parents(){
        BaseDto<Access> parents = accessService.getParents();
        JsonResult.response(response,parents.getCode(),parents.getMsg(),parents.getDataList());
    }

    /**
     * 添加成功
     */
    public void add(){
        BaseDto<Access> add = accessService.add(access);
        JsonResult.response(response,add.getCode(),add.getMsg(),null);
    }

    /**
     * 获取角色的权限
     * @return
     */
    public void getAllAccess(){
        BaseDto<Menu> access = accessService.getAccess();
        JsonResult.response(response,access.getCode(),access.getMsg(),access.getDataList());
    }

    /**
     * 获取当前用户的角色
     * @return
     */
    public void getRoleAccess(){
        String roleId = request.getParameter("id");
        LOG.info("获取当前角色的roleId:---------->"+roleId);
        BaseDto<Access> roleAccess = accessService.getRoleAccess(Integer.valueOf(roleId));
        JsonResult.response(response,roleAccess.getCode(),roleAccess.getMsg(),roleAccess.getDataList());
    }

    /**
     * 根据id删除权限
     * @return
     */
    public void delAccess(){
        String id = request.getParameter("id");
        BaseDto<Access> accessBaseDto = accessService.deleteById(Integer.valueOf(id));
        JsonResult.response(response,accessBaseDto.getCode(),accessBaseDto.getMsg(),null);
    }

    /**
     * 批量删除
     */
    public void batchDelete(){
        //获取前端ajax传递过来的数组。变量名后面一定加[],切记
        String[] ids = request.getParameterValues("ids[]");
        BaseDto<Access> accessBaseDto = accessService.batchDelete(ids);
        JsonResult.response(response,accessBaseDto.getCode(),accessBaseDto.getMsg(),null);
    }

    /**
     * 跳转更新页面
     * @return
     */
    public String goUpdate(){
        String id = request.getParameter("id");
        Access access = accessService.findById(Integer.valueOf(id));
        request.setAttribute("access",access);
        return SUCCESS;
    }

    /**
     * 更新权限
     * @return
     */
    public void update(){
        BaseDto<Access> accessBaseDto = accessService.updateAccess(access);
        JsonResult.response(response,accessBaseDto.getCode(),accessBaseDto.getMsg(),null);
    }

    @Override
    public Access getModel() {
        return access;
    }
}
