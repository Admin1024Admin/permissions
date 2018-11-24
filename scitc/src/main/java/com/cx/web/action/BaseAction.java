package com.cx.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseAction extends ActionSupport {

    /**
     * 获取request
     */
    protected HttpServletRequest request = ServletActionContext.getRequest();
    /**
     * 获取response
     */
    protected HttpServletResponse response = ServletActionContext.getResponse();
}
