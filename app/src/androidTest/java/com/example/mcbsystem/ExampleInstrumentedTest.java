package com.example.mcbsystem;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mcbsystem.dao.IUserDao;
import com.example.mcbsystem.dao.daoImpl.UserDaoImpl;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.service.IServiceUserDao;
import com.example.mcbsystem.service.serviceImpl.ServiceUserDaoImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    IUserDao userDao = new UserDaoImpl(context);
    List<User> list;
    @Test
    //添加信息测试
    public void addTest() {

       // User user = new User("孟2达",22,"女","2000-05-21","北京市",1539024552,"zhangsan@qq.com");
        User user = new User();
        user.setName("abbbb");
        user.setAge(22);
        user.setPhone(12345678901l);
        user.setEmail("1qweqwe");
        user.setGender("男");
        user.setBirthday("123123");
        user.setAddress("asdasdas");
        new ServiceUserDaoImpl(context).add(user);
//        User user1 = new User("孟辰辰",22,"男","2000-05-21","北京市",1593932244,"zhangsan@qq.com");
//        userDao.add(user1);
//        User user2 = new User("盖伦",22,"男","2000-05-21","北京市",1503934232,"zhangsan@qq.com");
//        userDao.add(user2);

    }
    @Test
    //查找所有信息测试
    public void findAllTest(){
       list = userDao.finAll();
        for (User user1:list) {
            System.out.println(user1);
        }
    }
    @Test
    //修改信息测试
    public void updateTest(){
        User user = new User("孟达",55,"女","2009-05-21","北京市",1509022222,"zhangsan@qq.com");
        userDao.update(user);
    }
    @Test
    //删除信息测试
    public void deleteTest(){
        userDao.deleteByName("孟良辰");
        }

    //通过id查询测试
    @Test
    public void findByNameTest(){
        User name = userDao.findByName("孟达");
        System.out.println(name);
    }
    @Test
    public void findByNameMohuTest(){
        List<User> users = userDao.findByNameMoHu("孟");
        for (User user:users) {
            System.out.println(user);
        }
    }
}



