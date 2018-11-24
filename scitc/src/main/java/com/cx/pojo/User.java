package com.cx.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{
    private int id;
    private String userName;
    private long userPhone;
    private String userPwd;
    private Timestamp userAddTime;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    private int roleId;
    private Role role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Timestamp getUserAddTime() {
        return userAddTime;
    }

    public void setUserAddTime(Timestamp userAddTime) {
        this.userAddTime = userAddTime;
    }

    /**
     * A类中，有个属性：List b， A与B的关系为 OneToMany；在B类中，
     * 有属性A a,引用到A中的字段id，并作为外键。hibernate查询结果正常，
     * 可以看到返回的A对象中，有b参数值，但在json转换的时候就出现了无限递归的情况。
     * 个人分析，应该是json在序列化A中的b属性的时候，找到了B类，然后序列化B类，而B类中有a属性
     * ，因此，为了序列化a属性，json又得去序列化A类，如此递归反复，造成该问题。
     * @return
     */
    @JsonBackReference
    public Role getRole() {
        return role;
    }
    @JsonBackReference
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone=" + userPhone +
                ", userPwd='" + userPwd + '\'' +
                ", userAddTime=" + userAddTime +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
