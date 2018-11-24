package com.cx.test;

import com.cx.pojo.User;
import com.cx.utils.MD5Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class MyTest {
    @Test
    public void test(){
        //1.加载配置文件
        Configuration config = new Configuration();
        //默认加载src下的hibernate.cfg.xml文件
        config.configure();
        //2.创建SessionFactory对象
        SessionFactory factory = config.buildSessionFactory();
        //3.创建session对象
        Session session = factory.openSession();
        //4.开启事务
        Transaction tr =  session.beginTransaction();
        try{
            //5.编写保存代码
            User user = new User();
            user.setUserName("小A");
            user.setUserPwd("123456");
            session.save(user);
            //6.提交事务
            tr.commit();
        }catch (Exception e){
            tr.rollback();
        }finally {
            //7.关闭资源
            session.close();
            factory.close();
        }

    }

    @Test
    public void md5(){
        System.out.println( MD5Util.MD55("123123"));
    }
}
