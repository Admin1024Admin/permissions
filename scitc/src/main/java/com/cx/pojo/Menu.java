package com.cx.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:Teacher黄
 * @date:Created at 2018/10/30
 */
public class Menu {

    /**
     * 父级
     */
    private Access parent;

    /**
     * 自己
     */
    private List<Access> child = new ArrayList<>();

    public Menu() {
    }

    public Access getParent() {
        return parent;
    }

    public void setParent(Access parent) {
        this.parent = parent;
    }

    public List<Access> getChild() {
        return child;
    }

    public void setChild(List<Access> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "parent=" + parent +
                ", child=" + child +
                '}';
    }
}
