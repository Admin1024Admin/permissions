package com.cx.web.interceptor;


import com.cx.pojo.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //从session获取对象
        User user =(User)actionInvocation.getInvocationContext().getSession().get("user");
        if(user==null){
            return "no_login";
        }
        //放行
        actionInvocation.invoke();
        return null;
    }
}
