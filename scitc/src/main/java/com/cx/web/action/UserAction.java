package com.cx.web.action;

import com.cx.dto.BaseDto;
import com.cx.pojo.User;
import com.cx.service.IUserService;
import com.cx.utils.JsonResult;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class UserAction  extends BaseAction implements ModelDriven<User>{

    @Autowired
    private IUserService userService;

    private User user= new User();
    /**
     * 跳转到登录页面
     */
    public String loginPage(){
        return SUCCESS;
    }

    /**
     * 登录
     */
    public void login(){
        BaseDto<User> userBaseDto = userService.login(user);
        LOG.info("USER---------------->"+userBaseDto);
        //判断是否登录成功
        if(userBaseDto.getCode()==200){
            //将当前登录对象保存到session
            request.getSession().setAttribute("user",userBaseDto.getT());
        }
        //返回json数据
        JsonResult.response(response,userBaseDto.getCode(),userBaseDto.getMsg(),userBaseDto.getT());
    }

    /**
     * 退出登录
     * @return
     */
    public String logout(){
        //退出清除session
        request.getSession().removeAttribute("user");
        return "logout";
    }
    @Override
    public User getModel() {
        return user;
    }
}
