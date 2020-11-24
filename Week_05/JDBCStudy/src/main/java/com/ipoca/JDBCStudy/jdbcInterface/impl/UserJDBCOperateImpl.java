package com.ipoca.JDBCStudy.jdbcInterface.impl;

import com.ipoca.JDBCStudy.POJO.User;
import com.ipoca.JDBCStudy.jdbcInterface.JDBCOperate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCOperateImpl implements JDBCOperate<User> {


    private Connection getConnection(){
        Connection conn = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接对象
            String url = "jdbc:mysql://47.107.246.82:3306/ipoca";
            conn = DriverManager.getConnection(url, "ipoca", "ipoca123");
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
            String sql = "INSERT INTO `tb_jk_user` (`username`, `phone`, `password`,`create_date`,`update_date`) VALUES ('"+obj.getUserName()+"', '"+obj.getPhone()+"', '"+obj.getPassword()+", '"+obj.getCreateDate()+", '"+obj.getUpdateDate()+"')";
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
