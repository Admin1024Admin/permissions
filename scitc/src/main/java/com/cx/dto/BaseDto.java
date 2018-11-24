package com.cx.dto;

import com.cx.dto.enums.StatusEnum;

import java.util.List;

/**
 * 返回数据处理
 *
 * @param <T>
 */
public class BaseDto<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 查询出来的单条数据
     */
    private T t;

    /**
     *  查询的列表数据
     */
    private List<T> dataList;


    /**
     *不用传输数据的构造器
     * @param statusEnum
     */
    public BaseDto(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
    }

    /**
     * 传递单条数据的构造器
     * @param statusEnum
     * @param t
     */
    public BaseDto(StatusEnum statusEnum, T t) {
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
        this.t = t;
    }

    /**
     * 传输列表的构造器
     * @param statusEnum
     * @param dataList
     */
    public BaseDto(StatusEnum statusEnum, List<T> dataList) {
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
        this.dataList = dataList;
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

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
