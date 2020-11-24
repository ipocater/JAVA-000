package com.ipoca.JDBCStudy;

import com.ipoca.JDBCStudy.POJO.User;
import com.ipoca.JDBCStudy.jdbcInterface.JDBCOperate;
import com.ipoca.JDBCStudy.jdbcInterface.impl.UserHikariOperateImpl;
import com.ipoca.JDBCStudy.jdbcInterface.impl.UserJDBCOperateImpl;
import com.zaxxer.hikari.HikariConfig;

import java.util.List;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        testHikariConfig();

        User queryUser = testHikariOperate();

        jdbcOperateTest(queryUser);

    }

    private static void jdbcOperateTest(User queryUser) {
        List<User> users;


        JDBCOperate<User> userJDBCOperate = new UserJDBCOperateImpl();

        users = userJDBCOperate.queryList(queryUser);
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
        insertUser.setUserId("8888");
        insertUser.setUserName("vvvvvvip");
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
        userJDBCOperate.deleteEntity(insertUser.getUserId());
        users = userJDBCOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }
    }

    private static User testHikariOperate() {
        JDBCOperate<User> userHikariOperate = new UserHikariOperateImpl();

        User queryUser = new User();
        List<User> users = userHikariOperate.queryList(queryUser);
        System.out.println("-------------------------------------------------------------------------");
        for (User user : users){
            System.out.println(user);
        }
        return queryUser;
    }

    private static void testHikariConfig() {
        HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
        System.out.println(config.getJdbcUrl());
        System.out.println(config.getConnectionTimeout());
        System.out.println(config.getMaximumPoolSize());
        System.out.println(config.getMinimumIdle());
        System.out.println(config.getUsername());
        System.out.println(config.getPassword());
    }
}
