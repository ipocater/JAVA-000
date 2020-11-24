package com.ipoca.JDBCStudy;

import com.ipoca.JDBCStudy.POJO.User;
import com.ipoca.JDBCStudy.jdbcInterface.JDBCOperate;
import com.ipoca.JDBCStudy.jdbcInterface.impl.UserHikariOperateImpl;


public class BatchInsertUser {
    public static void main(String[] args){
//        new Thread(){
//          @Override
//            public void run(){
//
//          }
//        };
        Long startTime = System.currentTimeMillis();
        for (int j = 0; j < 10; j++){
            new Thread(()->{
                User user = new User();
                JDBCOperate<User> userJDBCOperate = new UserHikariOperateImpl();
                for (int i = 0; i < 1000; i++){
                    user.setUserName("测试name" + (int)(Math.random()*100000000));
                    user.setPhone("18812345678");
                    user.setPassword("123456");
//                    user.setCreateDate(new Date());
//                    user.setUpdateDate(new Date());
                    userJDBCOperate.insertEntity(user);
                }
            }).start();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("程序共用时："+ (endTime-startTime)/1000);
    }
}
