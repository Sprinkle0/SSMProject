package com.qcby.dao;

import com.qcby.entity.User;

import java.util.List;

public interface UserDao {
    //写接口
    public List<User> findAll();

    //需要和mapper.方法 保持一致
    public User findById(Integer id);//返回值是User类型
    //Mybatis给的返回类型  数据库不会给
    public int insert (User user);

    public int delete (Integer id);

    public int update (User user);
}