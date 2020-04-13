package com.example.mcbsystem.dao.daoImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mcbsystem.dao.IUserDao;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.utils.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private MyDbHelper myDbHelper;
    private SQLiteDatabase myDatabase;
    private String[] property = new String[]{"id", "name", "age", "gender", "birthday", "address", "phone", "email"};

    public UserDaoImpl(Context context) {
        myDbHelper = new MyDbHelper(context);
        myDatabase = myDbHelper.getWritableDatabase();
    }
    //查找所有信息
    @Override
    public List<User> finAll() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = myDatabase.query(MyDbHelper.TABLE_NAME, property, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            user.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            user.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            list.add(user);
        }
        return list;
    }
    //添加信息
    @Override
    public void add(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        values.put("birthday", user.getBirthday());
        values.put("address", user.getAddress());
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        myDatabase.insert(MyDbHelper.TABLE_NAME, null, values);
    }
    //修改信息
    @Override
    public void update(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        values.put("birthday", user.getBirthday());
        values.put("address", user.getAddress());
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        myDatabase.update(MyDbHelper.TABLE_NAME, values, "name = ? ", new String[]{user.getName()});
    }

    @Override
    public void deleteByName(String name) {
        myDatabase.delete(MyDbHelper.TABLE_NAME, "name = ?", new String[]{name});
    }
    //根据姓名查找
    @Override
    public User findByName(String name) {
        User user = new User();
        Cursor cursor = myDatabase.query(MyDbHelper.TABLE_NAME, property, "name = ? ", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            user.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            user.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        }
        return user;
    }
    //模糊查询
    public List<User> findByNameMoHu(String str){
        List<User> list=new ArrayList<User>();
        String sql="select * from "+MyDbHelper.TABLE_NAME+" where name like '%" + str + "%'";
        Cursor cursor = myDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            user.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            user.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            list.add(user);
        }
        return list;
    }
}
