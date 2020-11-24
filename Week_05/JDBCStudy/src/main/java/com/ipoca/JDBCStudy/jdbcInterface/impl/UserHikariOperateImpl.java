package com.ipoca.JDBCStudy.jdbcInterface.impl;

import com.ipoca.JDBCStudy.POJO.User;
import com.ipoca.JDBCStudy.jdbcInterface.JDBCOperate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserHikariOperateImpl implements JDBCOperate<User> {
    private static HikariDataSource ds = new HikariDataSource(new HikariConfig("src/main/resources/hikari.properties"));


    private Connection getConnection(){
        Connection conn = null;
        try {
//            HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
//            HikariDataSource ds = new HikariDataSource(config);
            conn = ds.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public Boolean insertEntity(User obj) {
        Connection conn = getConnection();
        Statement stat = null;
        Boolean result = false;
        try {
            stat = conn.createStatement();
            String sql = "INSERT INTO `tb_jk_user` (`user_name`, `phone`, `password`) VALUES ('"+obj.getUserName()+"', '"+obj.getPhone()+"', '"+obj.getPassword()+"')";
            result = stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功插入数据："+obj);
        return result;
    }

    @Override
    public Boolean updateEntity(User obj) {
        Connection conn = getConnection();
        Statement stat = null;
        Boolean result = false;
        try {
            stat = conn.createStatement();
            String sql = "UPDATE `tb_user1` set id = '"+obj.getUserId()+"', username= '"+obj.getUserName()+"', phone = '"+obj.getPhone()+"' where id= '"+obj.getUserId()+"'";
            result = stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Boolean deleteEntity(String id) {
        Connection conn = getConnection();
        Statement stat = null;
        Boolean result = false;
        try {
            stat = conn.createStatement();
            String sql = "delete from tb_user1 where id ='"+id+"'";
            result = stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void batchDeleteEntity(String[] ids) {
        Connection conn = getConnection();
        String sql = "delete from tb_user1 where id = ? ";
        PreparedStatement ppst = null;
        try {
            conn.setAutoCommit(false);//开启事物，手动提交
            ppst = conn.prepareStatement(sql);
            for (String id : ids){
                ppst.setString(1,id);
                ppst.execute();
                ppst.clearParameters();
            }
            conn.commit();//提交事物
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public List<User> queryList(User obj) {
        List<User> resultList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "select id,username,phone from tb_user1 where 1 = 1";
            if (obj.getUserId() != null){
                sql += " and id = '" + obj.getUserId() + "' ";
            }
            if (obj.getPhone() != null){
                sql += " and phone = '" + obj.getPhone() + "' ";
            }
            if (obj.getUserName() != null){
                sql += " and username = '" + obj.getUserName()+"' ";
            }
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setUserId(rs.getString("id"));
                user.setUserName(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                resultList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

}
