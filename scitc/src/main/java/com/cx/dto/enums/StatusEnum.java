package com.cx.dto.enums;

/**
 * 用户登录的枚举
 */
public enum StatusEnum {
    LOGIN_USER_NAME_IS_WROWS(4001,"登录用户名错误"),
    LOGIN_USER_PSW_IS_WROWS(4002,"登录密码错误"),
    LOGIN_SUCCESS(200,"登录成功"),
    GET_DATA_SUCCESS(200,"获取成功"),
    DATA_ERROE(4003,"非法数据"),
    DELETE_SUCCESS(200,"删除成功"),
    DELETE_FAILURE(4004,"删除失败"),
    UPDATE_SUCCESS(200,"更新成功"),
    UPDATE_ERROR(4005,"更新失败"),
    ADD_SUCCESS(200,"添加成功"),
    ADD_ERROR(4006,"添加失败"),
    ;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
