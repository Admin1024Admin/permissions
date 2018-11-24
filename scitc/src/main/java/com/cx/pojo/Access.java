package com.cx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class Access {
    private int id;
    private String accessName;
    private String accessUrl;
    private int accessLevel;
    private int accessParentId;
    private int isMenu;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessParentId() {
        return accessParentId;
    }

    public void setAccessParentId(int accessParentId) {
        this.accessParentId = accessParentId;
    }

    public int getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Access access = (Access) o;

        if (id != access.id) return false;
        if (accessLevel != access.accessLevel) return false;
        if (accessParentId != access.accessParentId) return false;
        if (isMenu != access.isMenu) return false;
        if (accessName != null ? !accessName.equals(access.accessName) : access.accessName != null) return false;
        if (accessUrl != null ? !accessUrl.equals(access.accessUrl) : access.accessUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (accessName != null ? accessName.hashCode() : 0);
        result = 31 * result + (accessUrl != null ? accessUrl.hashCode() : 0);
        result = 31 * result + accessLevel;
        result = 31 * result + accessParentId;
        result = 31 * result + isMenu;
        return result;
    }

    @Override
    public String toString() {
        return "Access{" +
                "id=" + id +
                ", accessName='" + accessName + '\'' +
                ", accessUrl='" + accessUrl + '\'' +
                ", accessLevel=" + accessLevel +
                ", accessParentId=" + accessParentId +
                ", isMenu=" + isMenu +
                '}';
    }
}
