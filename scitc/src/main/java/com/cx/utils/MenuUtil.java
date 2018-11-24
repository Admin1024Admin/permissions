package com.cx.utils;

import com.cx.pojo.Access;
import com.cx.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:Teacher黄
 * @date:Created at 2018/11/15
 */
public class MenuUtil {


    /**
     * 将权限转换成menu
     * @param accessList
     * @return
     */
    public static List<Menu> change(List<Access> accessList){
        // 需要准备一个list装menu
        List<Menu> menus = new ArrayList<>();

        // 遍历accessList
        // 第一次遍历只找父级
        for (Access access : accessList) {
            if(access.getAccessParentId() == 0 && access.getAccessLevel() == 1){
                Menu menu = new Menu();
                menu.setParent(access);
                menus.add(menu);
            }
        }
        // 第二次遍历添加子级
        for (Access access : accessList) {
            if(access.getAccessLevel() == 2){
                // 遍历menu
                for (Menu menu : menus) {
                    if(menu.getParent().getId() == access.getAccessParentId()){
                        // 找到了对应父级的子级列表
                        menu.getChild().add(access);
                    }
                }
            }
        }
        return menus;
    }

}
