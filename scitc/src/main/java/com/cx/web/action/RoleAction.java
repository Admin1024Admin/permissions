package com.cx.web.action;

import com.cx.dto.BaseDto;
import com.cx.pojo.Role;
import com.cx.service.IRoleService;
import com.cx.utils.JsonResult;
import com.cx.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction implements ModelDriven<Role> {
    @Autowired
    private IRoleService roleService;
    /**
     * 对象注入
     */
    private Role role = new Role();

    /**
     * 跳转到角色列表
     * @return
     */
    public String list(){
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
        PageUtil<Role> pageUtil = new PageUtil<>(page,pageSize);
        //查询数据
        roleService.roleList(pageUtil,parameters);
        LOG.info("-------------->"+pageUtil.toString());
        //将数据发送到页面 json数据
        request.setAttribute("pageUtil",pageUtil);
        return SUCCESS;
    }

    /**
     * 跳转到添加页面
     * @return
     */
    public String goAdd(){
        return SUCCESS;
    }

    /**
     * 添加角色
     * @return
     */
    public void add(){
        BaseDto<Role> roleBaseDto = roleService.save(role);
        JsonResult.response(response,roleBaseDto.getCode(),roleBaseDto.getMsg(),null);
    }

    /**
     * 删除角色
     * @return
     */
    public void delete(){
        String id = request.getParameter("id");
        BaseDto<Role> roleBaseDto = roleService.delete(Integer.valueOf(id));
        JsonResult.response(response,roleBaseDto.getCode(),roleBaseDto.getMsg(),null);
    }

    /**
     * 批量删除
     * @return
     */
    public void deleteAll(){
        //获取前端ajax传递过来的数组。变量名后面一定加[],切记
        String[] ids = request.getParameterValues("ids[]");
        BaseDto<Role> roleBaseDto = roleService.batchDelete(ids);
        JsonResult.response(response,roleBaseDto.getCode(),roleBaseDto.getMsg(),null);
    }

    @Override
    public Role getModel() {
        return role;
    }

    /**
     * 跳转到修改页面
     */
    public String goUpdate(){
        String id = request.getParameter("id");
        Role uRole = roleService.findById(Integer.valueOf(id));
        request.setAttribute("data",uRole);
        return SUCCESS;
    }
    /**
     * 更新角色
     */
    public void update(){
        BaseDto<Role> update = roleService.update(role);
        JsonResult.response(response,update.getCode(),update.getMsg(),null);
    }

    /**
     * 跳转到权限分配页面
     */
    public String editAccessPage(){
        return SUCCESS;
    }

    /**
     * 更新角色权限
     */
    public void editAccess(){
        //获取当前角色的id和选中的权限ids
        String roleId = request.getParameter("roleId");
        //获取当前选中的所有的权限id
        String accessIds = request.getParameter("ids");
        LOG.info("当前角色的id为:"+roleId);
        LOG.info("当前权限的ids为:"+accessIds);
        //更新
        BaseDto<Role> roleBaseDto = roleService.editAccess(roleId, accessIds);
        //返回bjosn数据
        JsonResult.response(response,roleBaseDto.getCode(),roleBaseDto.getMsg(),null);
    }
}
