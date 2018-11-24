package com.cx.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class MainAction extends ActionSupport {

    /**
     * 后台首页
     * @return
     */
    public String index(){
        return SUCCESS;
    }

    /**
     * 欢迎界面
     * @return
     */
    public String welcome(){
        return "welcome";
    }
}
