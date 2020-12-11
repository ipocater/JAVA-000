package com.ipoca.JDBCStudy;

import com.ipoca.JDBCStudy.POJO.User;
import com.ipoca.JDBCStudy.jdbcInterface.JDBCOperate;
import com.ipoca.JDBCStudy.jdbcInterface.impl.UserJDBCOperateImpl;

import java.util.List;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        JDBCOperate<User> userJDBCOperate = new UserJDBCOperateImpl();

        User queryUser = new User();
        List<User> users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }

        String[] deleteIds = {"2","3"};
        userJDBCOperate.batchDeleteEntity(deleteIds);
        users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }

        User insertUser = new User();
        insertUser.setId("8888");
        insertUser.setUsername("vvvvvvip");
        insertUser.setPhone("18888888888");
        userJDBCOperate.insertEntity(insertUser);
        users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }
        insertUser.setPhone("1888888");
        userJDBCOperate.updateEntity(insertUser);
        users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }
        userJDBCOperate.deleteEntity(insertUser.getId());
        users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }

    }
}
