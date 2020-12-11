package com.ipoca.JDBCStudy.jdbcInterface;

import java.util.List;

public interface JDBCOperate<T>{

    public Boolean insertEntity(T obj);

    public Boolean updateEntity(T obj);

    public Boolean deleteEntity(String id);

    public void batchDeleteEntity(String[] ids);

    public List<T> queryList(T obj);
}
