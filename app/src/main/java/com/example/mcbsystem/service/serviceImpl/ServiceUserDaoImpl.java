package com.example.mcbsystem.service.serviceImpl;

import android.content.Context;

import com.example.mcbsystem.dao.IUserDao;
import com.example.mcbsystem.dao.daoImpl.UserDaoImpl;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.service.IServiceUserDao;

import java.util.List;

public class ServiceUserDaoImpl implements IServiceUserDao {
    private IUserDao userDao;
    public ServiceUserDaoImpl(Context context){
        userDao = new UserDaoImpl(context);
    }
    @Override
    public List<User> finAll() {
        return userDao.finAll();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> findByNameMoHu(String str) {
        return userDao.findByNameMoHu(str);
    }

}
