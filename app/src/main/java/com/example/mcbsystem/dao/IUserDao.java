package com.example.mcbsystem.dao;

import android.content.Context;

import com.example.mcbsystem.domain.User;

import java.util.List;

public interface IUserDao {

    //查找所有信息
    List<User> finAll();
    //添加信息
    void add(User user);
    //修改信息
    void update(User user);
    //删除信息
    void deleteByName(String name);
    //通过id查询信息
    User findByName(String name);
    //模糊查询
    List<User> findByNameMoHu(String str);
}
